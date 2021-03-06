package com.swapp.waraconvapp.Input;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.swapp.waraconvapp.Input.Input;
import com.swapp.waraconvapp.Input.InputFragment1;
import com.swapp.waraconvapp.Input.InputFragment2;
import com.swapp.waraconvapp.Input.InputFragment3;
import com.swapp.waraconvapp.Input.InputListener;
import com.swapp.waraconvapp.R;
import com.swapp.waraconvapp.Rank.RankActivity;

public class SearchActivity extends AppCompatActivity implements InputListener {

    Toolbar toolbar;
    InputFragment1 inputfragment1;
    InputFragment2 inputfragment2;
    InputFragment3 inputfragment3;
    Input input;
    TabLayout tabs;
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titleText = findViewById(R.id.titleText);
        titleText.setText("검색");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        inputfragment1 = new InputFragment1();
        inputfragment2 = new InputFragment2();
        inputfragment3 = new InputFragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, inputfragment1).commit();

        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("지역선택"));
        tabs.addTab(tabs.newTab().setText("운영방향"));
        tabs.addTab(tabs.newTab().setText("임대시세"));

        LinearLayout tabStrip = ((LinearLayout)tabs.getChildAt(0));
        tabStrip.setEnabled(true);
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setClickable(false);
        }
    }

    @Override
    public void inputSet(Input option, int index) {
        this.input = option;
        Bundle bundle = new Bundle();

        switch (index){
            case 0:
            {
                finish();
                break;
            }
            case 1:
            {
                bundle.putParcelable("input",input);
                inputfragment1.setArguments(bundle);
                tabs.getTabAt(0).select();
                getSupportFragmentManager().beginTransaction().replace(R.id.container,inputfragment1).commit();
                break;
            }
            case 2:
            {
                bundle.putParcelable("input",input);
                inputfragment2.setArguments(bundle);
                tabs.getTabAt(1).select();
                getSupportFragmentManager().beginTransaction().replace(R.id.container,inputfragment2).commit();
                break;
            }
            case 3:
            {
                bundle.putParcelable("input",input);
                inputfragment3.setArguments(bundle);
                tabs.getTabAt(2).select();
                getSupportFragmentManager().beginTransaction().replace(R.id.container,inputfragment3).commit();
                break;
            }
            case 4:
            {
                Intent intent = new Intent(getApplicationContext(), RankActivity.class);
                intent.putExtra("input", input);
                startActivity(intent);
                finish();
                break;
            }
        }
    }
}
