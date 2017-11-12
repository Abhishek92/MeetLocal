package com.android.meetlocal.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by hp pc on 12-11-2017.
 */

public abstract class BaseFragment extends Fragment {
    private Context mContext;
    private ProgressDialog mProgressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    public FirebaseUser getCurrentUser(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if(null != firebaseAuth)
            return firebaseAuth.getCurrentUser();
        else return null;
    }

    protected void showProgressDialog(String msg){
        if(null != mProgressDialog && !mProgressDialog.isShowing()) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage(msg);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
    }

    protected void hideProgressDialog(){
        if(null != mProgressDialog && mProgressDialog.isShowing()) {
           mProgressDialog.cancel();
        }
    }

    public void pushFragment(int container, Fragment fragment, String tag){
        if(null != getFragmentManager()){
            getFragmentManager().beginTransaction().replace(container, fragment, tag).commit();
        }
    }



    public Context getContext(){
        return mContext;
    }
}
