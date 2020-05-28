package com.example.myweartherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {
    public static final String CITY_NAME = "SelectedCityName";
    public static final String MORE_INFO_FLAG = "IsMoreInfoFlagSelected";
    TextView msk, spb, ekb, krd, kld, tlt, vdk;
    EditText customCity;
    Button okBtn;
    CheckBox moreInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        initViews();
    }

    private void initViews() {
        msk = findViewById(R.id.tv_city_msk);
        spb = findViewById(R.id.tv_city_spb);
        ekb = findViewById(R.id.tv_city_ekb);
        krd = findViewById(R.id.tv_city_krd);
        kld = findViewById(R.id.tv_city_kld);
        tlt = findViewById(R.id.tv_city_tlt);
        vdk = findViewById(R.id.tv_city_vdk);
        customCity = findViewById(R.id.et_pick_city);
        okBtn = findViewById(R.id.btn_pick_city);
        moreInfo = findViewById(R.id.cb_more_info);

        View.OnClickListener lsnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ((TextView)v).getText().toString();
                customCity.setText(text);
            }
        };

        msk.setOnClickListener(lsnr);
        spb.setOnClickListener(lsnr);
        ekb.setOnClickListener(lsnr);
        krd.setOnClickListener(lsnr);
        kld.setOnClickListener(lsnr);
        tlt.setOnClickListener(lsnr);
        vdk.setOnClickListener(lsnr);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity.this, MainActivity.class);
                intent.putExtra(CITY_NAME, customCity.getText().toString());
                intent.putExtra(MORE_INFO_FLAG, moreInfo.isChecked());
                startActivity(intent);
            }
        });
    }

}
