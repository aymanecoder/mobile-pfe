package com.example.mobile_pfe.GroupActivity;

public class User {

        private String firstName;
        private int imageId;
        private int id; // Sportif's ID
        public User(String name, int imageId, int id) {
            this.firstName = name;
            this.imageId = imageId;
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }
        public int getId() {
            return id;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }
    }


