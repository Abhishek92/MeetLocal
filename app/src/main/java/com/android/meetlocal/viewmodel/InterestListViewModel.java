package com.android.meetlocal.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.android.meetlocal.database.Interest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by hp pc on 13-11-2017.
 */

public class InterestListViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Interest>> selectedInterestList = new MutableLiveData<List<Interest>>();
    private DatabaseReference mDatabase;
    private MutableLiveData<List<Interest>> mutableInterestList;

    public InterestListViewModel(@NonNull Application application) {
        super(application);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<Interest>> getSelectedInterestList() {
        return selectedInterestList;
    }

    public void setSelectedInterestList(List<Interest> interestList) {
        selectedInterestList.setValue(interestList);
    }

    public LiveData<List<Interest>> getAllInterestList() {
        if (null == mutableInterestList) {
            mutableInterestList = new MutableLiveData<>();
            getInterestList();
        }
        return mutableInterestList;
    }

    private void getInterestList() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Interest>> genericTypeIndicator = new GenericTypeIndicator<List<Interest>>() {
                };
                List<Interest> interestList = dataSnapshot.child("interests").getValue(genericTypeIndicator);
                mutableInterestList.setValue(interestList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addListenerForSingleValueEvent(postListener);
    }
}
