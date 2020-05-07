package com.example.amiiboapp;
//test by Jordan
public class ExampleItem {
    private String mImageUrl;
    private String mAmiiboName;
    private String mOtherInfo;
    //private String key_id;
    //private String mfavStatus;

    public ExampleItem(String imageUrl, String amiiboName, String otherInfo){ //String favStatus, String key_id
        mImageUrl = imageUrl;
        mAmiiboName = amiiboName;
        mOtherInfo = otherInfo;
       // mfavStatus = favStatus;
       // this.key_id = key_id;
    }

    public String getmImageUrl(){
        return mImageUrl;
    }

    public  String getmAmiiboName(){
        return mAmiiboName;
    }

    public String getmOtherInfo(){
        return mOtherInfo;
    }

//    public String getmfavStatus(){
//        return mfavStatus;
//    }
//    public String getKey_id() {
//        return key_id;
//    }
//
//    public void setKey_id(String key_id) {
//        this.key_id = key_id;
//    }

//    public void setMfavStatus(String s){
//        this.mfavStatus = mfavStatus;
//    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public void setmAmiiboName(String mAmiiboName) {
        this.mAmiiboName = mAmiiboName;
    }

    public void setmOtherInfo(String mOtherInfo) {
        this.mOtherInfo = mOtherInfo;
    }
}
