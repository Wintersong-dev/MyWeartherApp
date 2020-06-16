package com.example.myweartherapp.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myweartherapp.DataPack;
import com.example.myweartherapp.R;
import com.example.myweartherapp.fragments.DatesFragment;
import com.example.myweartherapp.fragments.LocationFragment;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        DataPack dp = (DataPack) getIntent().getSerializableExtra(DatesFragment.DATA_PACK_PASSED_TO_ACTIVITY_KEY);
        Log.d("WEATHER_DEBUG", "-> " + dp.getDatePosition());
        LocationFragment lf = LocationFragment.create(dp);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.locations, lf);
        ft.commit();
    }
}
