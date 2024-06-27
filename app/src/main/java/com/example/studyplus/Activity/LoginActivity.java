package com.example.studyplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyplus.R;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginButton, signupButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.btn_login);
        signupButton = findViewById(R.id.signup_button);
        dbHelper = new DBHelper(this);

        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateInput(email, password)) {
                if (dbHelper.checkUser(email, password)) {
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    // Pass the email to the ApplicationActivity
                    Intent intent = new Intent(LoginActivity.this, ApplicationActivity.class);
                    intent.putExtra("USER_EMAIL", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
            }
        });

        signupButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private boolean validateInput(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }
}
