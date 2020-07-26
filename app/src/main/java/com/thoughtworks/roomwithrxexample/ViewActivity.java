package com.thoughtworks.roomwithrxexample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        RecyclerView recycleView = findViewById(R.id.recycle_view_container);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        MyAdapter myAdapter = new MyAdapter(getDataSet());

        recycleView.setLayoutManager(linearLayoutManager);
        recycleView.setAdapter(myAdapter);
    }

    private ArrayList<Person> getDataSet() {
        ArrayList<Person> personSet = new ArrayList<>();
        personSet.add(new Person("AAA", 18, 0));
        personSet.add(new Person("BBB", 18, 0));
        personSet.add(new Person("CCC", 18, 0));
        personSet.add(new Person("DDD", 18, 0));
        personSet.add(new Person("EEE", 18, 0));
        return personSet;
    }
}
