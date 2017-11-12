package com.android.meetlocal.imageloader;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by admin23 on 4/7/17.
 */

public class AppImageLoader implements ImageLoader {
    private static AppImageLoader appImageLoader;

    private AppImageLoader() {

    }

    public static AppImageLoader getInstance() {
        if (null == appImageLoader) {
            appImageLoader = new AppImageLoader();
        }
        return appImageLoader;
    }

    @Override
    public void loadImage(FragmentActivity activity, String uri, ImageView imageView) {
        RequestOptions requestOption = new RequestOptions();
        requestOption.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(activity).asBitmap().apply(requestOption)
                .load(uri).into(imageView);
    }

    @Override
    public void loadImage(Fragment fragment, Uri uri, ImageView imageView, BitmapTransformation bitmapTransformation) {
        RequestOptions requestOption = new RequestOptions();
        requestOption.transform(bitmapTransformation);
        requestOption.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(fragment).asBitmap().apply(requestOption)
                .load(uri).into(imageView);
    }

    @Override
    public void loadImage(FragmentActivity activity, byte[] data, ImageView imageView) {
        RequestOptions requestOption = new RequestOptions();
        requestOption.diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(activity).asBitmap().apply(requestOption)
                .load(data).into(imageView);
    }

    @Override
    public void clearCache(Context context, View view) {
        Glide.with(context).clear(view);
    }

    @Override
    public void loadImage(FragmentActivity activity, String uri, ImageView imageView, int placeHolderDrawable) {
        RequestOptions requestOption = new RequestOptions();
        requestOption.error(placeHolderDrawable);
        requestOption.placeholder(placeHolderDrawable);
        requestOption.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(activity).asBitmap().apply(requestOption)
                .load(uri).into(imageView);
    }

    @Override
    public void loadImage(FragmentActivity activity, byte[] byteArr, ImageView imageView, int errorDrawable) {
        RequestOptions requestOption = new RequestOptions();
        requestOption.error(errorDrawable);
        requestOption.placeholder(errorDrawable);
        requestOption.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(activity).asBitmap().apply(requestOption)
                .load(byteArr).into(imageView);
    }

    @Override
    public void loadImage(Context activity, String uri, ImageView imageView) {
        RequestOptions requestOption = new RequestOptions();
        requestOption.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(activity).asBitmap().apply(requestOption)
                .load(uri).into(imageView);
    }

    @Override
    public void loadImage(Context activity, byte[] data, ImageView imageView) {
        RequestOptions requestOption = new RequestOptions();
        requestOption.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(activity).asBitmap().apply(requestOption)
                .load(data).into(imageView);
    }

    @Override
    public void loadImage(Context activity, String uri, ImageView imageView, int placeHolderDrawable) {
        RequestOptions requestOption = new RequestOptions();
        requestOption.error(placeHolderDrawable);
        requestOption.placeholder(placeHolderDrawable);
        requestOption.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(activity).asBitmap().apply(requestOption)
                .load(uri).into(imageView);
    }

    @Override
    public void loadImage(Context activity, byte[] byteArr, ImageView imageView, int errorDrawable) {
        RequestOptions requestOption = new RequestOptions();
        requestOption.error(errorDrawable);
        requestOption.placeholder(errorDrawable);
        requestOption.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(activity).asBitmap().apply(requestOption)
                .load(byteArr).into(imageView);
    }
}
