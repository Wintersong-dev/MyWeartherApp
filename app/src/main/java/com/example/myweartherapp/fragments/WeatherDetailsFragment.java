package com.example.myweartherapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myweartherapp.DataPack;
import com.example.myweartherapp.R;
import com.example.myweartherapp.activities.HistoryActivity;
import com.example.myweartherapp.activities.LocationActivity;
import com.example.myweartherapp.activities.MainActivity;

public class WeatherDetailsFragment extends Fragment {
    public final static String CITY_NAME_PASSED_TO_HISTORY = "CityNamePassedToHistory";

    private TextView dateTextView;
    private TextView locationTextView;
    private int currentPosition;
    private String location;
    private DataPack arg = null;

    public static WeatherDetailsFragment create(DataPack container) {
        WeatherDetailsFragment fragment = new WeatherDetailsFragment();    // создание

        // Передача параметра
        Bundle args = new Bundle();
        args.putSerializable("index", container);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Собираем необходимые данные
        initData();

        // Находим вьюхи
        initViews(view);

        // Обновляем данные в изменяемых полях
        alterData();

        // Добавляем обработку перехода на активити выбора города
        setOnLocationChangeClickListener(view);

        // Добавляем обработку перехода на активити истории погоды в выбранном городе
        setOnHistoryIconClickListener(view);
    }

    private void initData() {
        initDataPack();
        if (arg != null) {
            currentPosition = arg.getPosition();
            location = arg.getCityName();
        }
    }

    private void alterData() {
        String[] arr = getResources().getStringArray(R.array.arr_date_list);
        dateTextView.setText(arr[currentPosition]);
        locationTextView.setText(location);
    }

    private void setOnLocationChangeClickListener(View view) {
        view.findViewById(R.id.iv_city_info).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), LocationActivity.class);
            intent.putExtra(DatesFragment.DATA_PACK_PASSED_TO_ACTIVITY_KEY, arg);
            startActivityForResult(intent, 1);
        }
    });
    }

    private void setOnHistoryIconClickListener(View view) {
        view.findViewById(R.id.iv_history_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                intent.putExtra(CITY_NAME_PASSED_TO_HISTORY, locationTextView.getText());
                startActivity(intent);
            }
        });
    }

    private void initDataPack() {
        if (getArguments() != null) {
            arg = (DataPack) getArguments().getSerializable("index");
        } else {
            arg = MainActivity.getDataPack();
        }
    }

    private void initViews(View view) {
        dateTextView = view.findViewById(R.id.tv_datetime);
        locationTextView = view.findViewById(R.id.tv_location);
    }

    public int getIndex() {
        initDataPack();
        return arg == null ? 0 : arg.getPosition();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initDataPack();
        arg.setCityName(data.getStringExtra(LocationFragment.CURRENT_LOCATION_KEY));
        locationTextView.setText(arg.getCityName());
    }
}
