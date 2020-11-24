package com.swapp.waraconvapp.Rank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.swapp.waraconvapp.DB.DataBase;
import com.swapp.waraconvapp.DB.DatabaseHelper;
import com.swapp.waraconvapp.DB.DetailInfo;
import com.swapp.waraconvapp.Input.ViewPagerAdapter;
import com.swapp.waraconvapp.R;

public class InfoDetailActivity extends AppCompatActivity {

    TextView tvToolbar;
    DetailInfo detailInfo;
    TabLayout tabsInforDetail;
    Toolbar toolbarInforDetail;

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    InforDetailFragment1 inforDetailFragment1;
    InforDetailFragment2 inforDetailFragment2;
    InforDetailFragment3 inforDetailFragment3;
    InforDetailFragment4 inforDetailFragment4;


    int code;
    int[][] rent;
    int[] convnum=new int[3];
    float[][] survive = new float[3][3];
    int[][] profit = new int[3][3];


    //0 = male, 1 = female
    int[] gender;
    int[] generation;
    int[] live_work;
    int[] house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_detail);

        Intent intent = getIntent();
        detailInfo = intent.getParcelableExtra("detailinfo");
        code = detailInfo.getCode();

        Log.d("세부정보", detailInfo.getName() + " " + detailInfo.getCode());
        Log.d("세부정보", detailInfo.getParentName() + " " + detailInfo.getRanknum());
        Log.d("세부정보",detailInfo.getTotalscore() + " " + detailInfo.getProfitscore() + " " + detailInfo.getStablescore());


        //데이터 받아오기
        DataBase db = new DataBase(new DatabaseHelper(getApplicationContext()));

        gender = db.findGender(code);
        generation = db.findGeneration(code);
        house = db.findHouse(code);
        live_work = db.findLiveWork(code);
        rent = db.findRent3(code, detailInfo.getParent(), convnum);
        db.findProfit_Survive(code,detailInfo.getParent(),profit,survive);


        //툴바 설정
        tvToolbar = findViewById(R.id.textName);
        tvToolbar.setText("세부 정보");
        toolbarInforDetail=findViewById(R.id.toolbarInforDetail);
        setSupportActionBar(toolbarInforDetail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        //탭 레이아웃 + 뷰페이저
        tabsInforDetail=findViewById(R.id.tabsInforDetail);
        tabsInforDetail.addTab((tabsInforDetail.newTab().setText("기본 정보")));
        tabsInforDetail.addTab((tabsInforDetail.newTab().setText("예측 데이터")));
        tabsInforDetail.addTab((tabsInforDetail.newTab().setText("인구 I")));
        tabsInforDetail.addTab((tabsInforDetail.newTab().setText("인구 II")));

        inforDetailFragment1=new InforDetailFragment1();
        inforDetailFragment2 = new InforDetailFragment2();
        inforDetailFragment3 =new InforDetailFragment3();
        inforDetailFragment4 =new InforDetailFragment4();

        //프래그먼트 번들 전달
        Bundle bundle[] = new Bundle[4];
        for(int i=0; i<bundle.length;i++){
            bundle[i] = new Bundle();
        }

        //1
        bundle[0].putParcelable("detailInfo", detailInfo);
        bundle[0].putIntArray("rent0", rent[0]);
        bundle[0].putIntArray("rent1", rent[1]);
        bundle[0].putIntArray("rent2", rent[2]);
        bundle[0].putIntArray("convnum",convnum);

        //2
        bundle[1].putInt("code", code);
        bundle[1].putParcelable("detailInfo", detailInfo);
        bundle[1].putIntArray("profit0", profit[0]);
        bundle[1].putIntArray("profit1", profit[1]);
        bundle[1].putIntArray("profit2", profit[2]);
        bundle[1].putFloatArray("survive0", survive[0]);
        bundle[1].putFloatArray("survive1", survive[1]);
        bundle[1].putFloatArray("survive2", survive[2]);

        //3
        bundle[2].putInt("code", code);
        bundle[2].putIntArray("gender",gender);
        bundle[2].putIntArray("generation",generation);

        //4
        bundle[3].putInt("code", code);
        bundle[3].putIntArray("house",house);
        bundle[3].putIntArray("live_work",live_work);

        inforDetailFragment1.setArguments(bundle[0]);
        inforDetailFragment2.setArguments(bundle[1]);
        inforDetailFragment3.setArguments(bundle[2]);
        inforDetailFragment4.setArguments(bundle[3]);

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(inforDetailFragment1);
        viewPagerAdapter.addFragment(inforDetailFragment2);
        viewPagerAdapter.addFragment(inforDetailFragment3);
        viewPagerAdapter.addFragment(inforDetailFragment4);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabsInforDetail));

        tabsInforDetail.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}