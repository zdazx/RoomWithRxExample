package com.thoughtworks.roomwithrxexample;

import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

public class ViewActivity extends AppCompatActivity {
    private static ArrayList<Person> person = new ArrayList<>();

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
        PersonDao personDao = MyApplication.getInstance().getPersonDao();

        Observable.just(new Object())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        person = (ArrayList<Person>) personDao.getPersons();
                    }
                });

        SystemClock.sleep(100);
        return person;
    }
}
