package com.example.ignac.mlcandidate;

import android.app.Application;
import android.content.Context;


public class ApplicationContext extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return sContext;
    }
}
