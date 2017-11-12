package com.android.meetlocal.imageloader;

import android.graphics.Bitmap;

/**
 * Created by admin23 on 4/7/17.
 */

public class ImageDisplayOption {


    /**
     * Image won't be scaled
     */
    public static final String IMAGE_SCALE_NONE = "NONE";

    /**
     * Image will be scaled down only if image size is greater than maximum acceptable texture size.
     */
    public static final String IMAGE_SCALE_NONE_SAFE = "NONE_SAFE";

    /**
     * Image will scaled-down exactly to target size (scaled width or height or both will be equal to target size; depends on ImageView's
     * scale type).
     */
    public static final String IMAGE_SCALE_EXACTLY = "EXACTLY";

    /**
     * Image will scaled exactly to target size (scaled width or height or both will be equal to target size; depends on ImageView's
     * scale type).
     */
    public static final String IMAGE_SCALE_EXACTLY_STRETCHED = "EXACTLY_STRETCHED";

    private int imageResOnLoading;
    private int imageResForEmptyUri;
    private int imageResOnFail;
    private boolean resetViewBeforeLoading;
    private int delayBeforeLoading;
    private boolean considerExifParams;
    private String scaleType;
    private Bitmap.Config bitmapConfig;

    private ImageDisplayOption(ImageDisplayOption.Builder builder) {
        this.imageResOnLoading = builder.imageResOnLoading;
        this.imageResForEmptyUri = builder.imageResForEmptyUri;
        this.imageResOnFail = builder.imageResOnFail;
        this.resetViewBeforeLoading = builder.resetViewBeforeLoading;
        this.delayBeforeLoading = builder.delayBeforeLoading;
        this.considerExifParams = builder.considerExifParams;
        this.scaleType = builder.scaleType;
        this.bitmapConfig = builder.bitmapConfig;
    }

    public int getImageResOnLoading() {
        return imageResOnLoading;
    }

    public int getImageResForEmptyUri() {
        return imageResForEmptyUri;
    }

    public int getImageResOnFail() {
        return imageResOnFail;
    }

    public boolean isResetViewBeforeLoading() {
        return resetViewBeforeLoading;
    }

    public int getDelayBeforeLoading() {
        return delayBeforeLoading;
    }

    public boolean isConsiderExifParams() {
        return considerExifParams;
    }

    public String getScaleType() {
        return scaleType;
    }

    public Bitmap.Config getBitmapConfig() {
        return bitmapConfig;
    }

    public static class Builder {
        private int imageResOnLoading;
        private int imageResForEmptyUri;
        private int imageResOnFail;
        private boolean resetViewBeforeLoading;
        private int delayBeforeLoading;
        private boolean considerExifParams;
        private String scaleType = IMAGE_SCALE_NONE;
        private Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;

        public Builder withImageResOnLoading(int imageResOnLoading) {
            this.imageResOnLoading = imageResOnLoading;
            return this;
        }

        public Builder withImageResForEmptyUri(int imageResForEmptyUri) {
            this.imageResForEmptyUri = imageResForEmptyUri;
            return this;
        }

        public Builder withImageResOnFail(int imageResOnFail) {
            this.imageResOnFail = imageResOnFail;
            return this;
        }

        public Builder withResetViewBeforeLoading(boolean resetViewBeforeLoading) {
            this.resetViewBeforeLoading = resetViewBeforeLoading;
            return this;
        }

        public Builder withDelayBeforeLoading(int delayBeforeLoading) {
            this.delayBeforeLoading = delayBeforeLoading;
            return this;
        }

        public Builder withConsiderExifParams(boolean considerExifParams) {
            this.considerExifParams = considerExifParams;
            return this;
        }

        public Builder withScaleType(String scaleType) {
            this.scaleType = scaleType;
            return this;
        }

        public Builder withBitmapConfig(Bitmap.Config config) {
            this.bitmapConfig = config;
            return this;
        }

        public ImageDisplayOption build() {
            return new ImageDisplayOption(this);
        }
    }
}
