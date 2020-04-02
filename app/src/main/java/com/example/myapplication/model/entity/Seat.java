package com.example.myapplication.model.entity;

public class Seat {
    private Integer id;
    private Integer row;
    private Integer col;
    private Screen screen;
    public Seat(){

    }
    public Seat(Integer id, Integer row, Integer col, Screen screen) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.screen = screen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
