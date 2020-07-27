package com.thoughtworks.roomwithrxexample;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PersonDao {
    @Insert
    Single<Long> createPerson(Person person);

    @Query("SELECT * FROM person")
    Single<List<Person>> getPersons();
}
