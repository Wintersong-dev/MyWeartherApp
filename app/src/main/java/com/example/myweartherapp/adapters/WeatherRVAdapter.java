package com.example.myweartherapp.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweartherapp.R;
import com.example.myweartherapp.RVClickListener;

import java.util.ArrayList;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {
    private ArrayList<String> data;
    private RVClickListener lsnr;
    private int currentPosition = -1;
    private RecyclerView rv;

    public WeatherRVAdapter(ArrayList<String> data, RVClickListener lsnr) {
        this.data = data;
        this.lsnr = lsnr;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        rv = recyclerView;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        processSelection();
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setItemText(holder, position);

    }

    private void setItemText(@NonNull ViewHolder holder, int position) {
        String text = data.get(position);
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.rv_item_value);
            textView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            setCurrentPosition(getLayoutPosition());
            processSelection();
            lsnr.onItemClicked(v, currentPosition);

        }
    }

    public void processSelection() {
        if (rv != null) {
            if (currentPosition == -1) {
                highlight(0);
            } else {
                for (int i = 0; i < rv.getChildCount(); i++) {
                    highlight(i);
                }
            }
        }
    }

    private void highlight(int position) {
        rv.getChildAt(position).setBackgroundColor((position == currentPosition || (currentPosition == -1 && position == 0)) ? Color.MAGENTA : Color.TRANSPARENT);
    }
}
