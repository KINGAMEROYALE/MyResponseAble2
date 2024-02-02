package com.example.myresponseable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText passwordET, emailET;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordET = findViewById(R.id.password);
        emailET = findViewById(R.id.email);
        auth = FirebaseAuth.getInstance();
        //auth.createUserWithEmailAndPassword();
        //auth.signInWithEmailAndPassword();

    }
}