package com.example.myweartherapp;

import java.io.Serializable;

public final class DataManager implements Serializable {
    private static DataManager instance = null;
    public String location;
    public String temperature;
    public String pressure;
    public String humidity;
    public String fallout;

    public static DataManager get() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private DataManager() {}
}
