package com.android.meetlocal.activity;

import android.os.Bundle;

import com.android.meetlocal.R;
import com.android.meetlocal.fragment.LoginFragment;
import com.android.meetlocal.fragment.WelcomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(null == getCurrentUser()) {
            pushFragment(R.id.sign_up_container, LoginFragment.getInstance(), LoginFragment.TAG);
        }
        else{
            pushFragment(R.id.sign_up_container, WelcomeFragment.getInstance(), WelcomeFragment.TAG);
        }
    }
}
