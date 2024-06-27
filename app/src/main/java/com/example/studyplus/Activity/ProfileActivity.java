package com.example.studyplus.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.studyplus.R;
import com.example.studyplus.User;

public class ProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private TextView usernameTextView, emailTextView, dobTextView, genderTextView, bioTextView;
    private TextView schoolTextView, majorTextView, yearOfStudyTextView;
    private ImageView profilePictureImageView;
    private Button editProfileButton;
    private DBHelper dbHelper;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePictureImageView = findViewById(R.id.selectedImage);
        usernameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.email);
        dobTextView = findViewById(R.id.birthdate);
        genderTextView = findViewById(R.id.gender);
        bioTextView = findViewById(R.id.bio);
        schoolTextView = findViewById(R.id.school);
        majorTextView = findViewById(R.id.major);
        yearOfStudyTextView = findViewById(R.id.yearOfStudy);
        editProfileButton = findViewById(R.id.edit_profile_button);

        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("USER_EMAIL");

        if (userEmail == null || userEmail.isEmpty()) {
            Toast.makeText(this, "Error: User email is null or empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("ProfileActivity", "User email: " + userEmail);

        loadUserProfile();

        editProfileButton.setOnClickListener(view -> {
            Intent editProfileIntent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            editProfileIntent.putExtra("userEmail", userEmail);
            startActivityForResult(editProfileIntent, 1);
        });

        profilePictureImageView.setOnClickListener(view -> openImagePicker());
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            loadUserProfile();
        } else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            Log.d("ProfileActivity", "Image URI: " + imageUri.toString());
            loadProfilePicture(imageUri);

            dbHelper.updateUserData(
                    usernameTextView.getText().toString(),
                    emailTextView.getText().toString(),
                    dobTextView.getText().toString(),
                    genderTextView.getText().toString(),
                    bioTextView.getText().toString(),
                    schoolTextView.getText().toString(),
                    majorTextView.getText().toString(),
                    yearOfStudyTextView.getText().toString(),
                    imageUri.toString()
            );
            Log.d("ProfileActivity", "Profile picture URI updated in database: " + imageUri.toString());
        }
    }

    private void loadUserProfile() {
        User user = dbHelper.getUserByEmail(userEmail);

        if (user != null) {
            usernameTextView.setText(user.getUsername());
            emailTextView.setText(user.getEmail());
            dobTextView.setText(user.getDob());
            genderTextView.setText(user.getGender());
            bioTextView.setText(user.getBio());
            schoolTextView.setText(user.getSchool());
            majorTextView.setText(user.getMajor());
            yearOfStudyTextView.setText(user.getYearOfStudy());

            if (user.getProfilePictureUri() != null) {
                loadProfilePicture(Uri.parse(user.getProfilePictureUri()));
            }
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadProfilePicture(Uri uri) {
        Log.d("ProfileActivity", "Loading profile picture with URI: " + uri.toString());
        Glide.with(this)
                .load(uri)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(profilePictureImageView);
    }
}
