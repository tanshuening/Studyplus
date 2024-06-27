package com.example.studyplus.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.studyplus.User;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userDatabase.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_BIRTHDATE = "birthDate";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_BIO = "bio";
    private static final String COLUMN_SCHOOL = "school";
    private static final String COLUMN_MAJOR = "major";
    private static final String COLUMN_YEAR_OF_STUDY = "yearOfStudy";
    private static final String COLUMN_PROFILE_PICTURE_URI = "profilePictureUri";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_BIRTHDATE + " TEXT,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_BIO + " TEXT,"
                + COLUMN_SCHOOL + " TEXT,"
                + COLUMN_MAJOR + " TEXT,"
                + COLUMN_YEAR_OF_STUDY + " TEXT,"
                + COLUMN_PROFILE_PICTURE_URI + " TEXT"
                + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COLUMN_PROFILE_PICTURE_URI + " TEXT");
        }
    }

    public void insertUserData(String name, String email, String password, String birthDate, String gender, String bio, String school, String major, String yearOfStudy, String profilePictureUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_BIRTHDATE, birthDate);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_BIO, bio);
        values.put(COLUMN_SCHOOL, school);
        values.put(COLUMN_MAJOR, major);
        values.put(COLUMN_YEAR_OF_STUDY, yearOfStudy);
        values.put(COLUMN_PROFILE_PICTURE_URI, profilePictureUri);

        long newRowId = db.insert(TABLE_USERS, null, values);
        if (newRowId == -1) {
            Log.e("DBHelper", "Failed to insert user data");
        } else {
            Log.d("DBHelper", "User data inserted successfully with row ID: " + newRowId);
        }
        db.close();
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;
    }

    public User getUserByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_NAME,
                COLUMN_EMAIL,
                COLUMN_BIRTHDATE,
                COLUMN_GENDER,
                COLUMN_BIO,
                COLUMN_SCHOOL,
                COLUMN_MAJOR,
                COLUMN_YEAR_OF_STUDY,
                COLUMN_PROFILE_PICTURE_URI
        };
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            String username = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String userEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            String dob = cursor.getString(cursor.getColumnIndex(COLUMN_BIRTHDATE));
            String gender = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER));
            String bio = cursor.getString(cursor.getColumnIndex(COLUMN_BIO));
            String school = cursor.getString(cursor.getColumnIndex(COLUMN_SCHOOL));
            String major = cursor.getString(cursor.getColumnIndex(COLUMN_MAJOR));
            String yearOfStudy = cursor.getString(cursor.getColumnIndex(COLUMN_YEAR_OF_STUDY));
            String profilePictureUri = cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_PICTURE_URI));
            user = new User(username, userEmail, dob, gender, bio, school, major, yearOfStudy, profilePictureUri);
            cursor.close();
        }
        db.close();
        return user;
    }

    public void updateUserData(String name, String email, String birthDate, String gender, String bio, String school, String major, String yearOfStudy, String profilePictureUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_BIRTHDATE, birthDate);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_BIO, bio);
        values.put(COLUMN_SCHOOL, school);
        values.put(COLUMN_MAJOR, major);
        values.put(COLUMN_YEAR_OF_STUDY, yearOfStudy);
        values.put(COLUMN_PROFILE_PICTURE_URI, profilePictureUri);

        int rowsAffected = db.update(TABLE_USERS, values, COLUMN_EMAIL + " = ?", new String[]{email});
        if (rowsAffected == 0) {
            Log.e("DBHelper", "Failed to update user data");
        } else {
            Log.d("DBHelper", "User data updated successfully");
        }
        db.close();
    }
}
