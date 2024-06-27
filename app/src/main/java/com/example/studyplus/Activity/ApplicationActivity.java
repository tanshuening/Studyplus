package com.example.studyplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.studyplus.Activity.Quiz.QuizActivity;
import com.example.studyplus.R;
import com.example.studyplus.User;

public class ApplicationActivity extends AppCompatActivity {
    private String userEmail;
    private TextView nameTextView;
    private static final String TAG = "ApplicationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        Log.d(TAG, "onCreate: ApplicationActivity started");

        nameTextView = findViewById(R.id.name);

        // Retrieve the user email from the intent
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("USER_EMAIL");

        DBHelper dbHelper = new DBHelper(this);
        User user = dbHelper.getUserByEmail(userEmail);
        dbHelper.close();

        // Set up the profile button
        ImageView profileButton = findViewById(R.id.profile);
        profileButton.setOnClickListener(view -> {
            Log.d(TAG, "onClick: Profile button clicked");
            Intent profileIntent = new Intent(ApplicationActivity.this, ProfileActivity.class);
            profileIntent.putExtra("USER_EMAIL", userEmail);
            startActivity(profileIntent);
        });

        // Set up the settings button
        ImageView settingsButton = findViewById(R.id.Settings);
        settingsButton.setOnClickListener(view -> {
            Log.d(TAG, "onClick: Settings button clicked");
            Intent settingsIntent = new Intent(ApplicationActivity.this, SettingsActivity.class);
            settingsIntent.putExtra("userEmail", userEmail);
            startActivity(settingsIntent);
        });

        // Set up the quiz button
        findViewById(R.id.btn4).setOnClickListener(view -> {
            Log.d(TAG, "onClick: Quiz button clicked");
            Intent quizIntent = new Intent(ApplicationActivity.this, QuizActivity.class);
            startActivity(quizIntent);
        });

        // Set up the backend development button
        ConstraintLayout btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(view -> {
            Log.d(TAG, "onClick: Backend button clicked");
            Intent backendIntent = new Intent(ApplicationActivity.this, BackendActivity.class);
            startActivity(backendIntent);
        });

        // Set up the frontend development button
        ConstraintLayout btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(view -> {
            Log.d(TAG, "onClick: Frontend button clicked");
            Intent frontendIntent = new Intent(ApplicationActivity.this, FrontendActivity.class);
            startActivity(frontendIntent);
        });

        // Set the retrieved username to the TextView
        if (user != null) {
            nameTextView.setText(user.getUsername());
        }
    }

    // Method to navigate to CoursesListActivity
    public void goToCoursesList(View view) {
        Log.d(TAG, "goToCoursesList: Courses list button clicked");
        Intent intent = new Intent(this, DatabaseActivity.class);
        startActivity(intent);
    }
}
