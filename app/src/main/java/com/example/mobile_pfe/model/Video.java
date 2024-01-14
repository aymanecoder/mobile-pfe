package com.example.mobile_pfe.model;

public class Video {
    String title;
    String duration;
    String views;
    String level;
    int image ;

    public Video(String title, String duration, String views, String level, int image) {
        this.title = title;
        this.duration = duration;
        this.views = views;
        this.level = level;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
