package com.example.myresponseable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class SuccessActivity extends AppCompatActivity {
    Button logbackBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        String email = getIntent().getStringExtra("email");
        TextView textView = findViewById(R.id.text);
        textView.setText(email);
        logbackBT = findViewById(R.id.logback);
        logbackBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {tocprofile();}
        });
    }

    private void tocprofile() {
        Intent intent = new Intent(SuccessActivity.this, CreateProfileActivity.class);
        startActivity(intent);
    }

}