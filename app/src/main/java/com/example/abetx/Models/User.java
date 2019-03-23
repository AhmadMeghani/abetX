package com.example.abetx.Models;

public class User {
    private String user_name;
    private String user_id;
    private String photoURL;
    private String description;

    public User(String user_name, String user_id, String photoURL, String description) {
        this.user_name = user_name;
        this.user_id = user_id;
        this.photoURL = photoURL;
        this.description = description;
    }

    public User() {

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_name='" + user_name + '\'' +
                ", user_id='" + user_id + '\'' +
                ", photoURL='" + photoURL + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
