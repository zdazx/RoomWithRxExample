package com.thoughtworks.roomwithrxexample;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert
    long createPerson(Person person);

    @Query("SELECT * FROM person")
    List<Person> getPersons();
}
