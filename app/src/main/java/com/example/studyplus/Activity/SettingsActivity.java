package com.example.studyplus.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studyplus.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize views
        Switch pushNotificationsSwitch = findViewById(R.id.push_notifications);
        Switch emailNotificationsSwitch = findViewById(R.id.email_notifications);
        Switch inAppNotificationsSwitch = findViewById(R.id.in_app_notifications);
        Button logoutButton = findViewById(R.id.logout_button);
        ImageView goBackButton = findViewById(R.id.go_back_button);

        // Set listeners for switches
        pushNotificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle Push Notifications switch change
            if (isChecked) {
                Toast.makeText(SettingsActivity.this, "Push Notifications Enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SettingsActivity.this, "Push Notifications Disabled", Toast.LENGTH_SHORT).show();
            }
        });

        emailNotificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle Email Notifications switch change
            if (isChecked) {
                Toast.makeText(SettingsActivity.this, "Email Notifications Enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SettingsActivity.this, "Email Notifications Disabled", Toast.LENGTH_SHORT).show();
            }
        });

        inAppNotificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle In-app Notifications switch change
            if (isChecked) {
                Toast.makeText(SettingsActivity.this, "In-app Notifications Enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SettingsActivity.this, "In-app Notifications Disabled", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for logout button
        logoutButton.setOnClickListener(v -> {
            // Handle Logout
            SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            // Navigate to LoginActivity
            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

            Toast.makeText(SettingsActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
        });

        // Set click listener for go back button
        goBackButton.setOnClickListener(v -> finish());
    }
}
