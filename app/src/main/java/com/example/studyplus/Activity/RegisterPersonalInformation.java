package com.example.studyplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyplus.R;

public class RegisterPersonalInformation extends AppCompatActivity {
    private EditText birthDateEditText, genderEditText, bioEditText;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_personal_information);

        birthDateEditText = findViewById(R.id.birthDate);
        genderEditText = findViewById(R.id.gender);
        bioEditText = findViewById(R.id.bio);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterPersonalInformation.this, RegisterEducationInformation.class);
            intent.putExtra("name", getIntent().getStringExtra("name"));
            intent.putExtra("email", getIntent().getStringExtra("email"));
            intent.putExtra("password", getIntent().getStringExtra("password"));
            intent.putExtra("birthDate", birthDateEditText.getText().toString());
            intent.putExtra("gender", genderEditText.getText().toString());
            intent.putExtra("bio", bioEditText.getText().toString());
            startActivity(intent);
        });
    }
}