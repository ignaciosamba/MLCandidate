package com.example.ignac.mlcandidate.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.example.ignac.mlcandidate.R;

public class Activity_Splash extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_activity);

        
    }
}
