package com.example.myweartherapp.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweartherapp.DataPack;
import com.example.myweartherapp.R;
import com.example.myweartherapp.fragments.DatesFragment;
import com.example.myweartherapp.fragments.WeatherDetailsFragment;

public class WeatherDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        // Ничего не делаем, если ориентация вдруг стала горизонтальной
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        // Восстанавливаем данные, при изменении ориентации
        if (savedInstanceState == null) {
            DataPack dp = (DataPack) getIntent().getExtras().get(DatesFragment.DATA_PACK_PASSED_TO_ACTIVITY_KEY);
            WeatherDetailsFragment fragment = WeatherDetailsFragment.create(dp);

            getSupportFragmentManager().beginTransaction().replace(R.id.weather_details, fragment).commit();
        }
    }
}
