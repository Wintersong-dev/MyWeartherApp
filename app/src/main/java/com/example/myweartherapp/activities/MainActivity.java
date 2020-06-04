package com.example.myweartherapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.myweartherapp.DataPack;
import com.example.myweartherapp.R;

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

