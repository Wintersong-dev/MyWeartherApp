package com.example.myweartherapp;

import android.util.Log;

// Тут хранятся детали погоды, полученные из Интернета
public final class WeatherData {
    private double temp;
    private String fallout;
    private String humidity;
    private String pressure;
    private int icon;
    private String errCode;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getFallout() {
        return fallout;
    }

    public void setFallout(String fallout) {
        this.fallout = fallout;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
        Log.d("WEATHER_DEBUG", this.icon + "/" + icon);
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
