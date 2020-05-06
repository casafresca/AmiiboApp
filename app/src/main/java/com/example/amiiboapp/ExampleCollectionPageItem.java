package com.example.amiiboapp;

public class ExampleCollectionPageItem {

    private String mImageUrl;
    private String mAmiiboName;
    private String mOtherInfo;
    private String mRelease;
    private String mPrice;

    public ExampleCollectionPageItem(String imageUrl, String amiiboName, String otherInfo, String release, String price) {
        this.mImageUrl = imageUrl;
        this.mAmiiboName = amiiboName;
        this.mOtherInfo = otherInfo;

        this.mRelease = release;
        this.mPrice = price;
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

    public String getmRelease(){return mRelease;}
    public String getmPrice(){return mPrice;}
}
