package com.android.meetlocal.fragment;


import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.meetlocal.R;
import com.android.meetlocal.imageloader.AppImageLoader;
import com.android.meetlocal.util.CircleTransformation;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends BaseFragment {

    public static final String TAG = WelcomeFragment.class.getSimpleName();
    private TextView mWelcomeText;
    private ImageView mUserProfile;
    private Button mPickInterestBtn;
    private static  final int PLACE_PICKER_REQUEST = 1;

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
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWelcomeText = view.findViewById(R.id.welcome_txt);
        mUserProfile = view.findViewById(R.id.user_image);
        mPickInterestBtn = view.findViewById(R.id.pick_interest_btn);

        mPickInterestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterestListDialogFragment.newInstance(40).show(getFragmentManager(), "items");
            }
        });

        setUserProfile();
    }

    private void setUserProfile(){
        FirebaseUser firebaseUser = getCurrentUser();
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
                Place place = PlacePicker.getPlace(getContext(), data);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
