package com.example.myapplication.model.entity;

import java.util.Date;

public class Movie {
    private Integer id;
    private String title;
    private String blurb;
    private String certificate;
    private String director;
    private String actors;
    private Date showtime;
    private Integer duration;
    private String type;
    private String language;

    public Movie (){

    }
    public Movie(Integer id, String title, String blurb, String certificate, String director, String lead_actors, Date showtime, Integer duration, String type, String language) {
        this.id = id;
        this.title = title;
        this.blurb = blurb;
        this.certificate = certificate;
        this.director = director;
        this.actors = lead_actors;
        this.showtime = showtime;
        this.duration = duration;
        this.type = type;
        this.language = language;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public Date getShowtime() {
        return showtime;
    }

    public void setShowtime(Date showtime) {
        this.showtime = showtime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

