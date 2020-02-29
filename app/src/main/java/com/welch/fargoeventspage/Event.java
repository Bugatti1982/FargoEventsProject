package com.welch.fargoeventspage;


public class Event {
    private int id;
    private String title;
    private String image_url;
    private String start_date_time;
    private String end_date_time;
    private String location;
    private Boolean featured;

    public Boolean getFeatured() { return featured; }
    public int getId() {
        return id;
    }
    public String getImage_url() { return image_url; }
    public String getTitle() {
        return title;
    }
    public String getStart_date_time() {
        return start_date_time;
    }
    public String getEnd_date_time() {
        return end_date_time;
    }
    public String getLocation() {
        return location;
    }
}
