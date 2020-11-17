package com.swapp.waraconvapp.Input;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.swapp.waraconvapp.R;

public class RankActivity extends AppCompatActivity {

    RankAdapter adapter;

    RecyclerView recyclerViewRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        recyclerViewRank=findViewById(R.id.recyclerviewRank);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerViewRank.setLayoutManager(layoutManager);
        adapter=new RankAdapter();

        adapter.addItem(new Rank(0,1,14, "행정동1"));
        adapter.addItem(new Rank(0,2,16, "행정동2"));
        adapter.addItem(new Rank(0,3,18, "행정동3"));

        recyclerViewRank.setAdapter(adapter);


    }
}