package com.example.myresponseable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    Button loginBT, registerBT;
    private TextInputEditText passwordET, emailET;
    private FirebaseAuth auth;

    String emailPattern = "[a-zA-z0-9.-_]+@[a-z]+\\.+[a-z]+";

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.please_wait));

        auth = FirebaseAuth.getInstance();

        passwordET = findViewById(R.id.password);
        emailET = findViewById(R.id.email);
        loginBT = findViewById(R.id.logbutton);
        registerBT = findViewById(R.id.register);

        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        if (isValidInput()) {
            String email = emailET.getText().toString();
            String pass = passwordET.getText().toString();
            progressDialog.show();
            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.putExtra("email", "register");
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void login(){
        if (isValidInput()) {
            progressDialog.show();
            String email = emailET.getText().toString();
            String pass = passwordET.getText().toString();

            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(SignUpActivity.this, CreateProfileActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private boolean isValidInput() {
        String email = emailET.getText().toString();
        String pass = passwordET.getText().toString();

        if ((TextUtils.isEmpty(email))) {
            Toast.makeText(SignUpActivity.this, "Enter the email: ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(SignUpActivity.this, "Enter the password: ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!email.matches(emailPattern)) {
            emailET.setError("Give Proper Email Address: ");
            return false;
        } else if (passwordET.length() < 6) {
            passwordET.setError("More than six characters: ");
            Toast.makeText(SignUpActivity.this, "Password needs to be longer than six characters. ", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}