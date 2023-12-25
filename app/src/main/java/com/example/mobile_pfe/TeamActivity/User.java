package com.example.mobile_pfe.TeamActivity;

public class User {

        private String firstName;
        private int imageId;

        public User(String firstName, int imageId) {
            this.firstName = firstName;
            this.imageId = imageId;
        }

        public String getFirstName() {
            return firstName;
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


