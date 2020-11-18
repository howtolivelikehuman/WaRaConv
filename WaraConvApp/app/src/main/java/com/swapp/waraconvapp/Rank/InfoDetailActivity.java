package com.swapp.waraconvapp.Rank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toolbar;

import com.swapp.waraconvapp.DB.DetailInfo;
import com.swapp.waraconvapp.R;

public class InfoDetailActivity extends AppCompatActivity {

    TextView tvToolbar;
    DetailInfo detailInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_detail);

        Intent intent = getIntent();
        detailInfo = intent.getParcelableExtra("detailinfo");

        Log.d("세부정보", detailInfo.getName() + " " + detailInfo.getCode());
        Log.d("세부정보", detailInfo.getParentName());

        tvToolbar = findViewById(R.id.textName);
        tvToolbar.setText(detailInfo.getName());

    }
}