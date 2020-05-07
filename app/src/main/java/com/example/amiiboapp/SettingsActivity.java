package com.example.amiiboapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    private Switch mSwitch;
    private SharedPrefTheme sharedPrefTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPrefTheme = new SharedPrefTheme(this);
        if(sharedPrefTheme.loadNightModeState() == true){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Dark Theme
        mSwitch = (Switch) findViewById(R.id.switch_darkTheme);
        if(sharedPrefTheme.loadNightModeState() == true){
            mSwitch.setChecked(true);
        }
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPrefTheme.setNightModeState(true);
                    restartApp();
                }
                else {
                    sharedPrefTheme.setNightModeState(false);
                    restartApp();
                }
            }
        });
    }

    public void restartApp(){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
        finish();
    }
}
