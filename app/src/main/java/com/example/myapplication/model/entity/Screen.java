package com.example.myapplication.model.entity;

public class Screen {
    private Integer id;
    private Integer num;
    private Cinema cinema;

    public Screen(){

    }
    public Screen(Integer id, Integer num, Cinema cinema) {
        this.id = id;
        this.num = num;
        this.cinema = cinema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}
