package com.example.myweartherapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweartherapp.DataPack;
import com.example.myweartherapp.R;
import com.example.myweartherapp.RVClickListener;
import com.example.myweartherapp.adapters.WeatherRVAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class LocationFragment extends Fragment implements RVClickListener {
    public static final String CURRENT_LOCATION_KEY = "CurrentLocation";

    private RecyclerView recyclerView;
    private ArrayList<String> locationList;

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
        initList();
        initDataPack();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.rv_city_list);
    }

    private void initList() {
        locationList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.arr_city_list)));
        LinearLayoutManager mgr = new LinearLayoutManager(getContext());
        WeatherRVAdapter adapter = new WeatherRVAdapter(locationList, this);

        recyclerView.setLayoutManager(mgr);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(View v, int pos) {

        Intent intent = new Intent();
        intent.putExtra(CURRENT_LOCATION_KEY, locationList.get(pos));

        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    private void initDataPack() {
        if (getArguments() != null) {
            arg = (DataPack) getArguments().getSerializable("index");
        }
    }
}
