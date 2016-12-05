package com.example.b50i7d.tbcapp;

/**
 * Created by Dell on 7/14/2016.
 */
public class NewsMessage {
    private String username;
    private String message;
    private String date;
    private String courses;

    public NewsMessage()
    {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCourses(){return courses; }

    public void setCourses(String courses){this.courses = courses;}
}
