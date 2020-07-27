package com.thoughtworks.roomwithrxexample;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication application;
    private static LocalDataSource localDataSource;

    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        localDataSource = new LocalDataSource();
    }

    public LocalDataSource getLocalDataSource() {
        return localDataSource;
    }
}
