package com.vvv.manool.warehouse;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by tb_dvl on 29.11.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this,new Crashlytics());
    }
}
