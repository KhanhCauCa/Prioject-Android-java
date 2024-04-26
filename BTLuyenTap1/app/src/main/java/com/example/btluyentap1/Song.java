package com.example.btluyentap1;

public class Song {

    private int id;
    private String name;
    private String singer;
    private Double timer;

    public Song(String name, String singer, Double timer) {

        this.name = name;
        this.singer = singer;
        this.timer = timer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Double getTimer() {
        return timer;
    }

    public void setTimer(Double timer) {
        this.timer = timer;
    }
}
