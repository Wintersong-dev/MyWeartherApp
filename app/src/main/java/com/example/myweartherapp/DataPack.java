package com.example.myweartherapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class DataPack implements Serializable {
    ArrayList<String> locations;
    private int datePosition = 0;
    private int locationPosition = 0;

    public int getDatePosition() {
        return datePosition;
    }

    public void setDatePosition(int position) {
        this.datePosition = position;
    }

    public int getLocationPosition() {
        return locationPosition;
    }

    public void setLocationPosition(int position) {
        this.locationPosition = position;
    }

    public String getLocation(int index) {
        return locations.get(index);
    }

    public String getLocation() {
        return locations.get(locationPosition);
    }

    public void initLocations(String[] basicArray) {
         locations = new ArrayList<>(Arrays.asList(basicArray));
    }

    public void addLocation(String newLocation) {
        locations.add(newLocation);
    }

    public void removeLocation(int index) {
        locations.remove(index);
        if (locationPosition >= index) {
            locationPosition--;
        }
    }

    public void removeLocation(String value) {
        int index = locations.indexOf(value);

        removeLocation(index);
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public int getLocationCount() {
        return locations.size();
    }
}
