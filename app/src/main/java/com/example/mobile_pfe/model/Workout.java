package com.example.mobile_pfe.model;

public class Workout {
    String name;
    String description;
    String  loaded_time;

    public Workout(String name, String description, String loaded_time) {
        this.name = name;
        this.description = description;
        this.loaded_time = loaded_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoaded_time() {
        return loaded_time;
    }

    public void setLoaded_time(String loaded_time) {
        this.loaded_time = loaded_time;
    }
}
