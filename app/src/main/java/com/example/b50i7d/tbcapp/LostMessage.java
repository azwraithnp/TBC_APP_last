package com.example.b50i7d.tbcapp;

/**
 * Created by Dell on 7/14/2016.
 */
public class LostMessage {
    private String username;
    private String message;
    private String dates;
    private String courses;
    private String url;

    public LostMessage()
    {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return dates;
    }

    public void setDate(String date) {
        this.dates = date;
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
