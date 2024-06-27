package com.example.studyplus.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyplus.R;
import com.example.studyplus.User;

public class EditProfileActivity extends AppCompatActivity {
    private EditText editUsername, editEmail, editDob, editGender, editBio, editSchool, editMajor, editYearOfStudy;
    private Button saveButton;
    private DBHelper dbHelper;
    private String userEmail;
    private String existingProfilePictureUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editUsername = findViewById(R.id.edit_username);
        editEmail = findViewById(R.id.edit_email);
        editDob = findViewById(R.id.edit_dob);
        editGender = findViewById(R.id.edit_gender);
        editBio = findViewById(R.id.edit_bio);
        editSchool = findViewById(R.id.edit_school);
        editMajor = findViewById(R.id.edit_major);
        editYearOfStudy = findViewById(R.id.edit_year_of_study);
        saveButton = findViewById(R.id.saveButton);
        dbHelper = new DBHelper(this);

        userEmail = getIntent().getStringExtra("userEmail");

        if (!TextUtils.isEmpty(userEmail)) {
            populateUserData(userEmail);
        } else {
            Toast.makeText(this, "User email is null or empty", Toast.LENGTH_SHORT).show();
        }

        saveButton.setOnClickListener(view -> updateUserData());
    }

    private void populateUserData(String email) {
        User user = dbHelper.getUserByEmail(email);
        if (user != null) {
            editUsername.setText(user.getUsername());
            editEmail.setText(user.getEmail());
            editDob.setText(user.getDob());
            editGender.setText(user.getGender());
            editBio.setText(user.getBio());
            editSchool.setText(user.getSchool());
            editMajor.setText(user.getMajor());
            editYearOfStudy.setText(user.getYearOfStudy());
            existingProfilePictureUri = user.getProfilePictureUri(); // Retrieve existing profile picture URI
        } else {
            Toast.makeText(this, "Failed to retrieve user data", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUserData() {
        String username = editUsername.getText().toString();
        String email = editEmail.getText().toString();
        String dob = editDob.getText().toString();
        String gender = editGender.getText().toString();
        String bio = editBio.getText().toString();
        String school = editSchool.getText().toString();
        String major = editMajor.getText().toString();
        String yearOfStudy = editYearOfStudy.getText().toString();

        if (!username.isEmpty() && !email.isEmpty()) {
            dbHelper.updateUserData(username, email, dob, gender, bio, school, major, yearOfStudy, existingProfilePictureUri);
            Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();

            // Set result and finish the activity
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Please fill in all the required fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackButtonClick(View view) {
        setResult(RESULT_OK);
        finish();
    }
}
