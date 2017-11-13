package com.android.meetlocal.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.android.meetlocal.database.DatabaseResponse;
import com.android.meetlocal.database.Message;
import com.android.meetlocal.database.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hp pc on 13-11-2017.
 */

public class WelcomeViewModel extends AndroidViewModel {
    private DatabaseReference mDatabase;
    private MutableLiveData<DatabaseResponse> databaseResponse = new MutableLiveData<>();

    public WelcomeViewModel(@NonNull Application application) {
        super(application);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<DatabaseResponse> getDatabaseResponse() {
        return databaseResponse;
    }

    public void postUserData(User user) {
        mDatabase.child("users").setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                DatabaseResponse response = new DatabaseResponse();
                if (null == databaseError) {
                    response.setId(0);
                    response.setMessage(Message.SUCCESS);
                    databaseResponse.setValue(response);
                } else {
                    response.setMessage(Message.FAILURE);
                    response.setDatabaseError(databaseError);
                    databaseResponse.setValue(response);
                }
            }
        });
    }
}
