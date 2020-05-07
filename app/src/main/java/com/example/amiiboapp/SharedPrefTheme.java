package com.example.amiiboapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefTheme {
    SharedPreferences mSharedPref;

    public SharedPrefTheme(Context context){
        mSharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }

    // Saving Night Mode State
    public void setNightModeState(Boolean state){
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    // Load Night Mode State
    public Boolean loadNightModeState(){
        Boolean state = mSharedPref.getBoolean("NightMode", false);
        return state;
    }

}
