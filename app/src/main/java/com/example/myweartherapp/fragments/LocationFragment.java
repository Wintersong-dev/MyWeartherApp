package com.example.myweartherapp.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweartherapp.DataPack;
import com.example.myweartherapp.R;
import com.example.myweartherapp.RVClickListener;
import com.example.myweartherapp.adapters.WeatherRVAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;

public class LocationFragment extends Fragment implements RVClickListener {
    private static final String LOG_TAG = "WEATHER_DEBUG";
    public static final String ARGUMENT_DATA_PACK = "DataPack";
    TextInputEditText tiet;
    private RecyclerView recyclerView;
    private ArrayList<String> locationList;
    WeatherRVAdapter adapter;

    private DataPack arg;

    public static LocationFragment create(DataPack container) {
        LocationFragment fragment = new LocationFragment();    // создание

        // Передача параметра
        Bundle args = new Bundle();
        args.putSerializable("index", container);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initDataPack();
        initList();

        setOnAddLocationClickListener();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnAddLocationClickListener() {
        tiet.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                processClick();
                v.setText("");
                return false;
            }
        });

        tiet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (tiet.getRight() - tiet.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        processClick();

                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void processClick() {
        String text = tiet.getText().toString();
        if (!text.equals("")) {
            addLocationToRecyclerView(text);
        }
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.rv_city_list);
        tiet = view.findViewById(R.id.et_location_add);
    }

    private void initList() {
        locationList = arg.getLocations();
        LinearLayoutManager mgr = new LinearLayoutManager(getContext());
        adapter = new WeatherRVAdapter(locationList, this);

        recyclerView.setLayoutManager(mgr);
        recyclerView.setAdapter(adapter);

        adapter.setCurrentPosition(getLocationPosition());
        adapter.processSelection();
    }

    @Override
    public void onItemClicked(View v, int pos) {

        arg.setLocationPosition(pos);
        Intent intent = new Intent();
        intent.putExtra(ARGUMENT_DATA_PACK, arg);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    private void initDataPack() {
        if (getArguments() != null) {
            arg = (DataPack) getArguments().getSerializable("index");
        }
    }

    private int getLocationPosition() {
        return arg.getLocationPosition();
    }

    private void addLocationToRecyclerView(String text) {
        arg.addLocation(text);
        adapter.notifyItemInserted(arg.getLocationCount());
    }
}
