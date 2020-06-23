package com.example.myweartherapp.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweartherapp.DataPack;
import com.example.myweartherapp.R;
import com.example.myweartherapp.RVClickListener;
import com.example.myweartherapp.activities.MainActivity;
import com.example.myweartherapp.activities.WeatherDetailsActivity;
import com.example.myweartherapp.adapters.WeatherRVAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class DatesFragment extends Fragment implements RVClickListener {
    private static final String LOG_TAG = "WEATHER_DEBUG";
    private static final String CURRENT_DATE_KEY = "CurrentDate";
    private static final String CURRENT_LOCATION_KEY = "CurrentLocation";
    public static final String DATA_PACK_PASSED_TO_ACTIVITY_KEY = "DataPackPassedToActivity";

    private RecyclerView recyclerView;

    private WeatherRVAdapter adapter = null;
    private boolean isOrientationLandscape;
    private int datePosition = 0;
    private int locationPosition = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dates, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Находим вьюхи
        initViews(view);

        // Готовим RecyclerView
        initList();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isOrientationLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            datePosition = savedInstanceState.getInt(CURRENT_DATE_KEY, 0);
            locationPosition = savedInstanceState.getInt(CURRENT_LOCATION_KEY, 0);

            if (adapter != null) {
                adapter.setCurrentPosition(datePosition);
                adapter.processSelection();
            }
        }

        showWeatherDetails(true);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_DATE_KEY, datePosition);
        outState.putInt(CURRENT_LOCATION_KEY, locationPosition);
        super.onSaveInstanceState(outState);
    }

    private void showWeatherDetails(boolean onlyLandscape) {

        // При горизонтальной ориентации, загоняем нужный фрагмент в правую часть экрана
        if (isOrientationLandscape) {
            WeatherDetailsFragment infoFragment = (WeatherDetailsFragment) Objects.requireNonNull(getFragmentManager()).findFragmentById(R.id.weather_details);

            // Перегружаем инфо, если оно еще не выведено или отличается от того, что мы хотим увидеть
            if (infoFragment == null || infoFragment.getIndex() != datePosition) {
                infoFragment = WeatherDetailsFragment.create(getDataPack());

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.weather_details, infoFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                ft.addToBackStack("SomeKey");
                ft.commit();

            }

        // При вертикальной ориентации, запускаем фрагмент в новой активити
        } else {
            if (!onlyLandscape) {
                Intent intent = new Intent();
                intent.setClass(Objects.requireNonNull(getActivity()), WeatherDetailsActivity.class);

                intent.putExtra(DATA_PACK_PASSED_TO_ACTIVITY_KEY, getDataPack());
                startActivity(intent);
            }
        }

    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.rv_date_list);
    }

    private void initList() {
        ArrayList<String> dateList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.arr_date_list)));
        LinearLayoutManager mgr = new LinearLayoutManager(getContext());
        adapter = new WeatherRVAdapter(dateList, this);

        recyclerView.setLayoutManager(mgr);
        recyclerView.setAdapter(adapter);
     }

    private DataPack getDataPack() {
        DataPack pack;
        if (getArguments() != null) {
            pack = (DataPack) getArguments().getSerializable("index");
        } else {
            pack = MainActivity.getDataPack();
            pack.initLocations(getResources().getStringArray(R.array.arr_city_list));
        }
        pack.setDatePosition(datePosition);

        return pack;
    }

    @Override
    public void onItemClicked(View v, int pos) {
        datePosition = pos;
        showWeatherDetails(false);
    }
}
