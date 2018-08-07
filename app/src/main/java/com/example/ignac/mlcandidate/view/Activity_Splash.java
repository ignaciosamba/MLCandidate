package com.example.ignac.mlcandidate.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.ignac.mlcandidate.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This Splash Activity is only used to display a nice screen before the user can interact with the app.
 */
public class Activity_Splash extends AppCompatActivity {

    /**
     * Time that the splash will be displayed.
     */
    private static int TIME_TO_SPLASH = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_activity);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent;
                mainIntent= new Intent().setClass(Activity_Splash.this,
                        MainActivity.class);
                startActivity(mainIntent);
                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, TIME_TO_SPLASH);
    }

}
