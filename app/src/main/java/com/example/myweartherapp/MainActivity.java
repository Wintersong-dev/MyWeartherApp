package com.example.myweartherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private final String LOG_CAT = "WeatherAppDebugger";
    private TextView location, datetime, temperature, pressure, humidity, fallout;
    private ImageView cityInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        applyDataFromIntent();
    }

    private void applyDataFromIntent() {
        Intent intent = getIntent();
        String newLocation = intent.getStringExtra(LocationActivity.CITY_NAME);
        boolean showMore = intent.getBooleanExtra(LocationActivity.MORE_INFO_FLAG, false);

        if (newLocation != null) {
            location.setText(newLocation);
        }
        pressure.setVisibility(showMore ? View.VISIBLE : View.INVISIBLE);
    }

    private void initViews() {
        location = findViewById(R.id.tv_location);
        datetime = findViewById(R.id.tv_datetime);
        datetime.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(new Date()));
        temperature = findViewById(R.id.tv_degrees);
        pressure = findViewById(R.id.tv_pressure);
        humidity = findViewById(R.id.tv_humidity);
        fallout = findViewById(R.id.tv_fallout);

        cityInfo = findViewById(R.id.iv_city_info);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });
        cityInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://ru.wikipedia.org/wiki/" + location.getText().toString();
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_CAT, "onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_CAT, "onRestart()");

        location.setText(DataManager.get().location);
        temperature.setText(DataManager.get().temperature);
        pressure.setText(DataManager.get().pressure);
        humidity.setText(DataManager.get().humidity);
        fallout.setText(DataManager.get().fallout);
        datetime.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(new Date()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_CAT, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_CAT, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_CAT, "onStop()");

        DataManager.get().location = location.getText().toString();
        DataManager.get().temperature = temperature.getText().toString();
        DataManager.get().pressure = pressure.getText().toString();
        DataManager.get().humidity = humidity.getText().toString();
        DataManager.get().fallout = fallout.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_CAT, "onDestroy()");
    }
}
