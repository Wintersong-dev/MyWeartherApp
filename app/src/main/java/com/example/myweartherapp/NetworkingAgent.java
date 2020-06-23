package com.example.myweartherapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkingAgent {
    private static final String LOG_TAG = "WEATHER_DEBUG";
    public static final String KEY = "x-api-key";
    public static final String KEY_VAL = "6e5f126c527ef2f6ff14ffe3e6555f03";
    public static final String URL_STRING = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=ru";

    private static WeatherData data = new WeatherData();

    public static JSONObject getJSONData(String city) {
        try {
            URL url = new URL(String.format(URL_STRING, city));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty(KEY, KEY_VAL);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder rawData = new StringBuilder(1024);
            String temp;

            while ((temp = reader.readLine()) != null) {
                rawData.append(temp).append("\n");
            }

            reader.close();

            JSONObject object = new JSONObject(rawData.toString());
            if (object.getInt("cod") == 200) {
                Log.d(LOG_TAG, "Request code: " + city);
                return object;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static WeatherData getRemoteData(final String location) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final JSONObject object = NetworkingAgent.getJSONData(location);

                try {
                    fillWeatherData(object);
                } catch (JSONException ignore) {}
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return data;
    }

    private static void fillWeatherData(JSONObject input) throws JSONException {
        if (input == null) {
            data.setErrCode("Ошибка получения данных по сети!!");
            return;
        }
        JSONObject details = input.getJSONArray("weather").getJSONObject(0);
        JSONObject main = input.getJSONObject("main");

        if (input.getInt("cod") != 200) {
            data.setErrCode("Сетевая ошибка: " + input.getInt("cod"));
            data.setIcon(R.drawable.no_data);
            data.setHumidity("");
            data.setPressure("");
            data.setTemp(0);
            data.setFallout("");
            return;
        } else {
            data.setErrCode("");
        }

        data.setHumidity(main.getString("humidity"));
        data.setPressure(main.getString("pressure"));
        data.setTemp(main.getDouble("temp"));
        data.setFallout(details.getString("description"));
        switch(details.getString("icon").substring(0, 2)) {
            case "01":
                data.setIcon(R.drawable.sunny);
                break;
            case "02":
                data.setIcon(R.drawable.few_clouds);
                break;
            case "03":
                data.setIcon(R.drawable.scattered_clouds);
                break;
            case "04":
                data.setIcon(R.drawable.broken_clouds);
                break;
            case "09":
                data.setIcon(R.drawable.shower_rain);
                break;
            case "10":
                data.setIcon(R.drawable.rain);
                break;
            case "11":
                data.setIcon(R.drawable.thunderstorm);
                break;
            case "13":
                data.setIcon(R.drawable.snow);
                break;
            case "50":
                data.setIcon(R.drawable.mist);
                break;
            default:
                data.setIcon(R.drawable.no_data);
                break;
        }
        Log.d(LOG_TAG, "---> "+ data.getIcon());
    }
}
