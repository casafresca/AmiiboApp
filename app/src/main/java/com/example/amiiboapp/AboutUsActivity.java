package com.example.amiiboapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

public class AboutUsActivity extends AppCompatActivity {
    private SharedPrefTheme sharedPrefTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Light vs Dark Mode
        sharedPrefTheme = new SharedPrefTheme(this);
        if(sharedPrefTheme.loadNightModeState() == true){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }
}
