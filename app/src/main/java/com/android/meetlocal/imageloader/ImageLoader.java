package com.android.meetlocal.imageloader;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by admin23 on 4/7/17.
 */

public interface ImageLoader {

    void loadImage(FragmentActivity activity, String uri, ImageView imageView);
    void loadImage(Fragment fragment, Uri uri, ImageView imageView, BitmapTransformation bitmapTransformation);

    void loadImage(FragmentActivity activity, byte[] data, ImageView imageView);

    void loadImage(FragmentActivity activity, String uri, ImageView imageView, int placeHolderDrawable);

    void loadImage(FragmentActivity activity, byte[] byteArr, ImageView imageView, int errorDrawable);


    void loadImage(Context activity, String uri, ImageView imageView);

    void loadImage(Context activity, byte[] data, ImageView imageView);

    void loadImage(Context activity, String uri, ImageView imageView, int placeHolderDrawable);

    void loadImage(Context activity, byte[] byteArr, ImageView imageView, int errorDrawable);

    void clearCache(Context context, View view);
}
