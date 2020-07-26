package com.thoughtworks.roomwithrxexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goSubmitBtn = findViewById(R.id.go_submit_btn);
        Button goViewBtn = findViewById(R.id.go_view_btn);
        goSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSubmitActivity();
            }
        });
        goViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewActivity();
            }
        });
    }

    private void openViewActivity() {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    private void openSubmitActivity() {
        Intent intent = new Intent(this, SubmitActivity.class);
        startActivity(intent);
    }
}