package com.android.meetlocal.imageloader;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by admin23 on 4/7/17.
 */

public interface ImageLoadingListener {
    void onLoadingStarted(String uri, View view);

    void onLoadingFailed(String uri, View view, Throwable failureReason);

    void onLoadingComplete(String uri, View view, Bitmap bitmap);

    void onLoadingCancelled(String uri, View view);
}
