package com.example.mobile_pfe.model;

import com.example.mobile_pfe.Network.RetrofitInstance;

public class Video {

    private Integer id;
    private String titre;
    private String videoName;
    private String description;
    private int numberOfTeam;
    private String addedDate;
    private String urlVideo;

    public Video(Integer id, String titre, String videoName, String description, int numberOfTeam, String addedDate, String urlVideo) {
        this.id = id;
        this.titre = titre;
        this.videoName = videoName;
        this.description = description;
        this.numberOfTeam = numberOfTeam;
        this.addedDate = addedDate;
        this.urlVideo = urlVideo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfTeam() {
        return numberOfTeam;
    }

    public void setNumberOfTeam(int numberOfTeam) {
        this.numberOfTeam = numberOfTeam;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getUrlVideo() {
        if (urlVideo != null) {
            return urlVideo.replace("http://localhost:9000/", RetrofitInstance.BASE_URL);
        } else {
            return null;
        }
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }
}
