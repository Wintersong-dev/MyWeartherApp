package com.example.myweartherapp;

import java.io.Serializable;

public class DataPack implements Serializable {
    private int position = 0;
    private String cityName = "";

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
