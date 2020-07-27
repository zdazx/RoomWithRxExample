package com.thoughtworks.roomwithrxexample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
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

        personDao.getPersons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Person>>() {
                    private Disposable disposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(List<Person> people) {
                        if (disposable.isDisposed()) {
                            return;
                        }
                        person = (ArrayList<Person>) people;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

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
