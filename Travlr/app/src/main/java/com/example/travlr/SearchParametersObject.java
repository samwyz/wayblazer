package com.example.travlr;

/**
 * Created by samwyz on 7/9/16.
 */
public class SearchParametersObject {
    String mLocation;
    String mStartDate;
    String mEndDate;

    public SearchParametersObject(String mLocation, String mStartDate, String mEndDate) {
        this.mLocation = mLocation;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }
}
