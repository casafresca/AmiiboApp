package com.example.amiiboapp;
//test by Jordan
public class ExampleItem {
    private String mImageUrl;
    private String mAmiiboName;
    private String mOtherInfo;

    public ExampleItem(String imageUrl, String amiiboName, String otherInfo){
        mImageUrl = imageUrl;
        mAmiiboName = amiiboName;
        mOtherInfo = otherInfo;
    }

    public String getmImageUrl(){
        return mImageUrl;
    }

    public String getmAmiiboName(){
        return mAmiiboName;
    }

    public String getmOtherInfo(){
        return mOtherInfo;
    }
}
