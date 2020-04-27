package com.example.myapplication.model.entity;

import java.util.Date;

public class Ticket {
    private Integer id;
    private Date create_time;
    private String type;
    private float price;

    private Cinema_Admin cinema_admin;

    private Seat seat;

    private Screening screening;
    public Ticket(){

    }

    public Ticket(Integer id, Date create_time, String type, float price,Cinema_Admin cinema_admin, Seat seat, Screening screening) {
        this.id = id;
        this.create_time = create_time;
        this.type = type;
        this.price = price;
        this.cinema_admin = cinema_admin;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Cinema_Admin getCinema_Admin() {
        return cinema_admin;
    }

    public void setCinema_Admin(User user) {
        this.cinema_admin = cinema_admin;
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
