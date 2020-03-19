package com.example.myapplication.model.entity;

import java.util.Date;

public class Screening {
    private Integer id;
    private Date start_time;
    private Date end_time;

    private Screen screen;
    private Movie movie;
    public Screening() {

    }
    public Screening(Integer id, Date start_time, Date end_time, Screen screen, Movie movie) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.screen = screen;
        this.movie = movie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
