package com.example.myweartherapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweartherapp.R;
import com.example.myweartherapp.RVClickListener;
import com.example.myweartherapp.adapters.WeatherRVAdapter;
import com.example.myweartherapp.fragments.WeatherDetailsFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HistoryActivity extends AppCompatActivity implements RVClickListener {
    private TextView title;
    private RecyclerView rv;
    private WeatherRVAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initViews();

        initData();

        initList();
    }

    private void initData() {
        String text = "История погоды в г." + getIntent().getStringExtra(WeatherDetailsFragment.CITY_NAME_PASSED_TO_HISTORY);
        title.setText(text);
    }

    private synchronized void initList() {
        ArrayList<String> dateList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.arr_date_history)));

        for (int i = 0; i < dateList.size(); i++) {
            Random random = new Random();
            StringBuilder builder = new StringBuilder();

            builder.append(dateList.get(i));
            builder.append(": +");
            builder.append(15 + random.nextInt(10));
            builder.append("C, ");

            switch (random.nextInt(4)) {
                case 0:
                    builder.append("Без осадков");
                    break;
                case 1:
                    builder.append("Пасмурно");
                    break;
                case 2:
                    builder.append("Небольшой дождь");
                    break;
                case 3:
                    builder.append("Дождь");
                    break;
                case 4:
                    builder.append("Гроза");
                    break;
            }
            dateList.remove(i);
            dateList.add(i, builder.toString());
        }

        LinearLayoutManager mgr = new LinearLayoutManager(getBaseContext());
        adapter = new WeatherRVAdapter(dateList, this);

        rv.setLayoutManager(mgr);
        rv.setAdapter(adapter);
    }

    private void initViews() {
        title = findViewById(R.id.tv_history_title);
        rv = findViewById(R.id.rv_history_data);
    }

    @Override
    public void onItemClicked(View v, int pos) {

    }
}
