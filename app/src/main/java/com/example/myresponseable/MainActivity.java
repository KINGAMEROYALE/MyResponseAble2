package com.example.myresponseable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        String email = getIntent().getStringExtra("email");
        TextView textView = findViewById(R.id.text);
        textView.setText(email);
    }
}