package com.example.myapplication.model.entity;

import java.util.Date;

public class Ticket {
    private Integer id;
    private Date create_time;

    private User user;

    private Seat seat;

    private Screening screening;
    public Ticket(){

    }
    public Ticket(Integer id, Date create_time, User user, Seat seat, Screening screening) {
        this.id = id;
        this.create_time = create_time;
        this.user = user;
        this.seat = seat;
        this.screening = screening;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }
}
