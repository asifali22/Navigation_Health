package com.thenewboston.mynavigation;

import android.app.Application;
import android.content.Context;

/**
 * Created by monster on 26/1/16.
 */
public class MyApplication extends Application{

    private static MyApplication  sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
