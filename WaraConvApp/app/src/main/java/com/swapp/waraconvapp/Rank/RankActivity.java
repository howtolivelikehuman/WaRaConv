package com.swapp.waraconvapp.Rank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.swapp.waraconvapp.DB.DataBase;
import com.swapp.waraconvapp.DB.DatabaseHelper;
import com.swapp.waraconvapp.DB.DetailInfo;
import com.swapp.waraconvapp.Input.Input;
import com.swapp.waraconvapp.Input.SearchActivity;
import com.swapp.waraconvapp.R;

import java.util.ArrayList;

public class RankActivity extends AppCompatActivity {

    RankAdapter adapter;
    DataBase db;
    Input input;
    RecyclerView recyclerViewRank;
    ArrayList<String> area;
    int ratio;
    int rentalmax;
    int rentalmin;

    ArrayList<Integer> areacode;
    ArrayList<DetailInfo> rankItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        input = getIntent().getParcelableExtra("input");
        area = input.getArea();
        ratio = input.getRatio();
        rentalmax = input.getRentalmax();
        rentalmin = input.getRentalmin();

        db = new DataBase(new DatabaseHelper(getApplicationContext()));
        //이름 -> code
        areacode = db.findCode(area);
        //랭크 가져오기
        rankItems = db.findRank(rentalmax, rentalmin, areacode, ratio);

        //1개도 없음
        if(rankItems == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("결과");
            builder.setMessage("조건에 맞는 지역을 발견하지 못하였습니다.\n다시 시도해 주십시오");
            builder.setNeutralButton("다시하기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);
                    finish();
                }

            });
            builder.show();
        }else{
            recyclerViewRank=findViewById(R.id.recyclerviewRank);
            LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
            recyclerViewRank.setLayoutManager(layoutManager);
            adapter=new RankAdapter();

            adapter.setItems(rankItems);

            recyclerViewRank.setAdapter(adapter);
        }
    }
}