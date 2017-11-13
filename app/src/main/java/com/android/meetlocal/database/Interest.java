package com.android.meetlocal.database;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

/**
 * Created by hp pc on 13-11-2017.
 */

public class Interest implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Interest> CREATOR = new Parcelable.Creator<Interest>() {
        @Override
        public Interest createFromParcel(Parcel in) {
            return new Interest(in);
        }

        @Override
        public Interest[] newArray(int size) {
            return new Interest[size];
        }
    };
    private String id;
    private String type;
    private String url;
    @Exclude
    private boolean selceted;

    public Interest() {

    }

    protected Interest(Parcel in) {
        id = in.readString();
        type = in.readString();
        url = in.readString();
        selceted = in.readByte() != 0x00;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSelceted() {
        return selceted;
    }

    public void setSelceted(boolean selceted) {
        this.selceted = selceted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
        dest.writeString(url);
        dest.writeByte((byte) (selceted ? 0x01 : 0x00));
    }
}