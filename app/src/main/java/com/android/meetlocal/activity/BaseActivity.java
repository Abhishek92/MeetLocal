package com.android.meetlocal.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by hp pc on 12-11-2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public FirebaseUser getCurrentUser(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if(null != firebaseAuth)
            return firebaseAuth.getCurrentUser();
        else return null;
    }

    public void pushFragment(int container, Fragment fragment, String tag){
        if(null != getSupportFragmentManager()){
            getSupportFragmentManager().beginTransaction().replace(container, fragment, tag).commit();
        }
    }
}
