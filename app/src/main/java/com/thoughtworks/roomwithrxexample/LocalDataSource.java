package com.thoughtworks.roomwithrxexample;

import androidx.room.Room;

import java.util.List;

import io.reactivex.Single;

public class LocalDataSource {
    private PersonDao personDao;

    public LocalDataSource() {
        String DATABASE_NAME = "app_db";
        AppDatabase appDatabase = Room
                .databaseBuilder(MyApplication.getInstance(), AppDatabase.class, DATABASE_NAME)
                .build();
        personDao = appDatabase.personDao();
    }

    public Single<Long> createPerson(Person person) {
        return personDao.createPerson(person);
    }

    public Single<List<Person>> getPersons() {
        return personDao.getPersons();
    }
}
