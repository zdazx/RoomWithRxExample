package com.thoughtworks.roomwithrxexample;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Person {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public int age;

    @ColumnInfo
    public int gender;

    public Person(int id, String name, int age, int gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Ignore
    public Person(String name, int age, int gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }
}
