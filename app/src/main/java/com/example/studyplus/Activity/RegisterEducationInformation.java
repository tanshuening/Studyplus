package com.example.studyplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studyplus.R;

public class RegisterEducationInformation extends AppCompatActivity {
    private EditText schoolEditText, majorEditText, yearOfStudyEditText;
    private Button saveButton;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_education_information);

        schoolEditText = findViewById(R.id.school);
        majorEditText = findViewById(R.id.major);
        yearOfStudyEditText = findViewById(R.id.yearOfStudy);
        saveButton = findViewById(R.id.saveButton);

        dbHelper = new DBHelper(this);

        saveButton.setOnClickListener(view -> {
            String name = getIntent().getStringExtra("name");
            String email = getIntent().getStringExtra("email");
            String password = getIntent().getStringExtra("password");
            String birthDate = getIntent().getStringExtra("birthDate");
            String gender = getIntent().getStringExtra("gender");
            String bio = getIntent().getStringExtra("bio");
            String school = schoolEditText.getText().toString();
            String major = majorEditText.getText().toString();
            String yearOfStudy = yearOfStudyEditText.getText().toString();

            dbHelper.insertUserData(name, email, password, birthDate, gender, bio, school, major, yearOfStudy, null);

            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(RegisterEducationInformation.this, LoginActivity.class));
        });
    }
}
