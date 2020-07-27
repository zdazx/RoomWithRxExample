package com.thoughtworks.roomwithrxexample;

import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ViewActivity extends AppCompatActivity {
    private static ArrayList<Person> person = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

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
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Object>() {

                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onNext(Object o) {
                        if (disposable.isDisposed()) {
                            return;
                        }
                        person = (ArrayList<Person>) personDao.getPersons();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        SystemClock.sleep(100);
        return person;
    }

    @Override
    protected void onDestroy() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
        super.onDestroy();
    }
}
