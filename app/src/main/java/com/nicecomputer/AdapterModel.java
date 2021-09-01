package com.nicecomputer;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AdapterModel {
    @SerializedName("Name")
    private String mName;
    @SerializedName("Course")
    private String mCourse;
    @SerializedName("Address")
    private String mAddress;
    @SerializedName("Number")
    private String mNumber;
    @SerializedName("Fees")
    private String mFees;


    public AdapterModel( String Name, String Course, String Address, String Number, String Fees ) {
        this.mName = Name;
        this.mCourse = Course;
        this.mAddress = Address;
        this.mNumber = Number;
        this.mFees = Fees;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public String getmName() {
        return mName;
    }

    public void setmName( String mName ) {
        this.mName = mName;
    }

    public String getmCourse() {
        return mCourse;
    }

    public void setmCourse( String mCourse ) {
        this.mCourse = mCourse;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress( String mAddress ) {
        this.mAddress = mAddress;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber( String mNumber ) {
        this.mNumber = mNumber;
    }

    public String getmFees() {
        return mFees;
    }

    public void setmFees( String mFees ) {
        this.mFees = mFees;
    }

}
