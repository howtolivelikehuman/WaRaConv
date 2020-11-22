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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_detail);

        Intent intent = getIntent();
        detailInfo = intent.getParcelableExtra("detailinfo");

        Log.d("세부정보", detailInfo.getName() + " " + detailInfo.getCode());
        Log.d("세부정보", detailInfo.getParentName() + " " + detailInfo.getRanknum());
        Log.d("세부정보",detailInfo.getTotalscore() + " " + detailInfo.getProfitscore() + " " + detailInfo.getStablescore());

        //툴바 설정
        tvToolbar = findViewById(R.id.textName);
        tvToolbar.setText(detailInfo.getName());
        toolbarInforDetail=findViewById(R.id.toolbarInforDetail);
        setSupportActionBar(toolbarInforDetail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        //탭 레이아웃 + 뷰페이저
        tabsInforDetail=findViewById(R.id.tabsInforDetail);
        tabsInforDetail.addTab((tabsInforDetail.newTab().setText("지역 정보")));
        tabsInforDetail.addTab((tabsInforDetail.newTab().setText("인구(성별/연령별)")));
        tabsInforDetail.addTab((tabsInforDetail.newTab().setText("인구(그 외)")));

        inforDetailFragment1=new InforDetailFragment1();
        inforDetailFragment2=new InforDetailFragment2();
        inforDetailFragment3=new InforDetailFragment3();

        //프래그먼트 번들 전달
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailInfo",detailInfo);
        inforDetailFragment1.setArguments(bundle);
        inforDetailFragment2.setArguments(bundle);
        inforDetailFragment3.setArguments(bundle);

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(inforDetailFragment1);
        viewPagerAdapter.addFragment(inforDetailFragment2);
        viewPagerAdapter.addFragment(inforDetailFragment3);

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