package com.thoughtworks.roomwithrxexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

public class SubmitActivity extends AppCompatActivity {
    private EditText nameView;
    private EditText ageView;
    private EditText genderView;
    public static boolean isSubmitted = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumbit);

        Button submitBtn = findViewById(R.id.submit_btn);
        nameView = findViewById(R.id.name);
        ageView = findViewById(R.id.age);
        genderView = findViewById(R.id.gender);
        submitBtn.setOnClickListener(view -> submitPerson());
    }

    private void submitPerson() {
        String name = nameView.getText().toString();
        String age = ageView.getText().toString();
        String gender = genderView.getText().toString();

        if (isInvalid(name, age, gender)) {
            return;
        }

        Person person = new Person(name, Integer.parseInt(age), Integer.parseInt(gender));
        PersonDao personDao = MyApplication.getInstance().getPersonDao();

        Observable.create((Observable.OnSubscribe<Person>) subscriber -> subscriber.onNext(person))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Person>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Person person) {
                        long personId = personDao.createPerson(person);
                        if (Objects.nonNull(personId)) {
                            isSubmitted = true;
                        }
                    }
                });

        SystemClock.sleep(100);
        String toastInfo = isSubmitted ? "Success" : "Failed";
        Toast.makeText(getApplicationContext(), toastInfo, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean isInvalid(String name, String age, String gender) {
        return isInvalidName(name) || isInvalidAge(age) || isInvalidGender(gender);
    }

    private boolean isInvalidGender(String gender) {
        Pattern genderPattern = Pattern.compile("[0|1]");
        Matcher genderMatcher = genderPattern.matcher(gender);
        if (!genderMatcher.matches()) {
            Toast.makeText(getApplicationContext(), "Gender Invalid", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private boolean isInvalidAge(String age) {
        Pattern agePattern = Pattern.compile("[0-9]{1,2}");
        Matcher ageMatcher = agePattern.matcher(age);
        if (!ageMatcher.matches()) {
            Toast.makeText(getApplicationContext(), "Age Invalid", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private boolean isInvalidName(String name) {
        if (Objects.isNull(name) || name.length() > 8 || name.length() == 0) {
            Toast.makeText(getApplicationContext(), "Name Invalid", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
