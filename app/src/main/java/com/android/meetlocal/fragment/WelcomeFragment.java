package com.android.meetlocal.fragment;


import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.meetlocal.R;
import com.android.meetlocal.activity.HomeActivity;
import com.android.meetlocal.database.DatabaseResponse;
import com.android.meetlocal.database.Interest;
import com.android.meetlocal.database.User;
import com.android.meetlocal.imageloader.AppImageLoader;
import com.android.meetlocal.util.CircleTransformation;
import com.android.meetlocal.util.Util;
import com.android.meetlocal.viewmodel.InterestListViewModel;
import com.android.meetlocal.viewmodel.WelcomeViewModel;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends BaseFragment {

    public static final String TAG = WelcomeFragment.class.getSimpleName();
    private static final int PLACE_PICKER_REQUEST = 1;
    private TextView mWelcomeText;
    private ImageView mUserProfile;
    private TextView mAddress;
    private ArrayList<Interest> interestList = new ArrayList<>();
    private List<Interest> selectedInterestList = new ArrayList<>();
    private FirebaseUser firebaseUser;
    private Place place;
    private WelcomeViewModel welcomeViewModel;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    public static WelcomeFragment getInstance(){
        return new WelcomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.welcome_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.next){
            saveUserData();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveUserData() {
        User user = new User();
        if (null != firebaseUser) {
            user.setId(firebaseUser.getUid());
            user.setName(firebaseUser.getDisplayName());
            user.setEmail(firebaseUser.getEmail());
            user.setPhoneNumber(firebaseUser.getPhoneNumber());
            if (null != firebaseUser.getPhotoUrl()) {
                user.setPhotoUrl(firebaseUser.getPhotoUrl().toString());
            }
        }
        if (null != place) {
            user.setAddress(place.getAddress().toString());
            user.setLatitude(place.getLatLng().latitude);
            user.setLongitude(place.getLatLng().longitude);
        }

        if (Util.listNotNull(selectedInterestList)) {
            user.setInterestList(selectedInterestList);
        }

        welcomeViewModel.postUserData(user);
        welcomeViewModel.getDatabaseResponse().observe(this, new Observer<DatabaseResponse>() {
            @Override
            public void onChanged(@Nullable DatabaseResponse databaseResponse) {
                if (null != databaseResponse) {
                    if (null == databaseResponse.getDatabaseError()) {
                        getActivity().startActivity(new Intent(getContext(), HomeActivity.class));
                    } else {
                        Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWelcomeText = view.findViewById(R.id.welcome_txt);
        mUserProfile = view.findViewById(R.id.user_image);
        mAddress = view.findViewById(R.id.location_txt);
        Button mPickInterestBtn = view.findViewById(R.id.pick_interest_btn);
        RelativeLayout relativeLayout = view.findViewById(R.id.select_location_rl);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        mPickInterestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterestListDialogFragment.newInstance(interestList).show(getFragmentManager(), "items");
            }
        });

        welcomeViewModel = ViewModelProviders.of(this).get(WelcomeViewModel.class);
        getAllInterestList();
        setUserProfile();
    }

    private void getAllInterestList() {
        InterestListViewModel interestListViewModel = ViewModelProviders.of(getActivity()).get(InterestListViewModel.class);
        interestListViewModel.getAllInterestList().observe(this, new Observer<List<Interest>>() {
            @Override
            public void onChanged(@Nullable List<Interest> interests) {
                if (Util.listNotNull(interests)) {
                    interestList = new ArrayList<>(interests);
                }
            }
        });

        interestListViewModel.getSelectedInterestList().observe(getActivity(), new Observer<List<Interest>>() {
            @Override
            public void onChanged(@Nullable List<Interest> interestList) {
                if (Util.listNotNull(interestList)) {
                    selectedInterestList = new ArrayList<>(interestList);
                }
            }
        });
    }

    private void setUserProfile(){
        firebaseUser = getCurrentUser();
        if(null != firebaseUser) {
            mWelcomeText.setText(String.format(getString(R.string.welcome_to_meetlocal), firebaseUser.getDisplayName()));
            AppImageLoader.getInstance().loadImage(this, firebaseUser.getPhotoUrl(), mUserProfile, new CircleTransformation());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                place = PlacePicker.getPlace(getContext(), data);
                mAddress.setText(place.getAddress());
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
