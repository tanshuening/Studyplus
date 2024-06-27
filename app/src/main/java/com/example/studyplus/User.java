package com.example.studyplus;

public class User {
    private String username;
    private String email;
    private String birthdate;
    private String gender;
    private String bio;
    private String school;
    private String major;
    private String yearOfStudy;
    private String profilePictureUri;

    // Constructor
    public User(String username, String email, String birthdate, String gender, String bio, String school, String major, String yearOfStudy, String profilePictureUri) {
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
        this.bio = bio;
        this.school = school;
        this.major = major;
        this.yearOfStudy = yearOfStudy;
        this.profilePictureUri = profilePictureUri;
    }

    // Getter methods
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getDob() { return birthdate; }
    public String getGender() { return gender; }
    public String getBio() { return bio; }
    public String getSchool() { return school; }
    public String getMajor() { return major; }
    public String getYearOfStudy() { return yearOfStudy; }
    public String getProfilePictureUri() { return profilePictureUri; }

    // Setter methods
    public void setProfilePictureUri(String profilePictureUri) {
        this.profilePictureUri = profilePictureUri;
    }
}
