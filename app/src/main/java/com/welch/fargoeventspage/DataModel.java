package com.welch.fargoeventspage;

public class DataModel {

    String title;
    String location;
    String image_url;
    // still need to add entries for start and finish time

    public DataModel(String title, String location, String image_url) {
        this.title = title;
        this.location = location;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getImage_url() {
        return image_url;
    }
}
