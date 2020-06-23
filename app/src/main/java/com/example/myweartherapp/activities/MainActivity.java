package com.example.myweartherapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.myweartherapp.DataPack;
import com.example.myweartherapp.R;
import com.example.myweartherapp.fragments.DatesFragment;
import com.example.myweartherapp.fragments.LocationFragment;
import com.example.myweartherapp.fragments.WeatherDetailsFragment;

public final class MainActivity extends AppCompatActivity {
    private static DataPack dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static DataPack getDataPack() {
        if (dp == null) {
            dp = new DataPack();
        }

        return dp;
    }

}

