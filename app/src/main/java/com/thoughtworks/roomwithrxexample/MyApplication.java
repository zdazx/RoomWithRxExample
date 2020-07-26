package com.thoughtworks.roomwithrxexample;

import android.app.Application;

import androidx.room.Room;

public class MyApplication extends Application {
    private PersonDao personDao;
    private static MyApplication application;

    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        final String DATABASE_NAME = "app_db";
        AppDatabase appDatabase = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                .build();
        personDao = appDatabase.personDao();
    }

    public PersonDao getPersonDao() {
        return personDao;
    }
}
