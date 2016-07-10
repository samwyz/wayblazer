package com.example.travlr;

/**
 * Created by samwyz on 7/9/16.
 */
public class Place {
    String mName;
    String mImageReference;
    String mAddress;

    public Place(String mName, String mImageUrl, String mAddress) {
        this.mName = mName;
        this.mImageReference = mImageUrl;
        this.mAddress = mAddress;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageReference() {
        return mImageReference;
    }

    public void setmImageReference(String mImageReference) {
        this.mImageReference = mImageReference;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }
}