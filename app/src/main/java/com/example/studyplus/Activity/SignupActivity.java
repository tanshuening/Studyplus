package com.example.studyplus.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyplus.R;

public class SignupActivity extends AppCompatActivity {
    private EditText nameEditText, emailEditText, passwordEditText, rePasswordEditText;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        rePasswordEditText = findViewById(R.id.re_password);
        signupButton = findViewById(R.id.btn_signup);

        signupButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, RegisterPersonalInformation.class);
            intent.putExtra("name", nameEditText.getText().toString());
            intent.putExtra("email", emailEditText.getText().toString());
            intent.putExtra("password", passwordEditText.getText().toString());
            startActivity(intent);
        });
    }
}

/*
    private void handleSignup() {
        String nameText = name.getText().toString();
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        String rePasswordText = re_password.getText().toString();

        if (TextUtils.isEmpty(nameText) || TextUtils.isEmpty(emailText) ||
                TextUtils.isEmpty(passwordText) || TextUtils.isEmpty(rePasswordText)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordText.equals(rePasswordText)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = dbHelper.getUserByEmail(emailText);
        if (cursor.getCount() > 0) {
            Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = dbHelper.addUser(nameText, emailText, passwordText);
        if (result > 0) {
            Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Signup failed", Toast.LENGTH_SHORT).show();
        }
        if (result > 0) {
            Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("userEmail", emailText);
            startActivity(intent);
            finish();
        }

    }
}*/
