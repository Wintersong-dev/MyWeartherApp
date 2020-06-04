package com.example.myweartherapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myweartherapp.DataPack;
import com.example.myweartherapp.R;
import com.example.myweartherapp.fragments.DatesFragment;
import com.example.myweartherapp.fragments.LocationFragment;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        if (savedInstanceState == null) {
            DataPack dp = (DataPack) getIntent().getSerializableExtra(DatesFragment.DATA_PACK_PASSED_TO_ACTIVITY_KEY);
            LocationFragment fragment = LocationFragment.create(dp);

            getSupportFragmentManager().beginTransaction().replace(R.id.location_container, fragment).commit();
        }
    }
}
