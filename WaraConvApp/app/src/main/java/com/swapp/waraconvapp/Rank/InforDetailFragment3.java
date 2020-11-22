package com.swapp.waraconvapp.Rank;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swapp.waraconvapp.DB.DetailInfo;
import com.swapp.waraconvapp.R;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

public class InforDetailFragment3 extends Fragment {

    DetailInfo detailInfo;
    PieChart pieChartWorkLive;
    BarChart barChartHouseHold;

    TextView textViewWorkLiveDetail;

    public InforDetailFragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        detailInfo = bundle.getParcelable("detailInfo");

        View view = inflater.inflate(R.layout.fragment_infor_detail3, container, false);

        textViewWorkLiveDetail=view.findViewById(R.id.textViewWorkLiveDetail);
        textViewWorkLiveDetail.setText("주거인구: 13459명\n직장인구: 11210명");

        pieChartWorkLive=view.findViewById(R.id.pieChartWorkLive);
        barChartHouseHold=view.findViewById(R.id.barChartHouseHold);

        pieChartWorkLive.clearChart();
        pieChartWorkLive.addPieSlice(new PieModel("주거인구",13459, Color.parseColor("#CDA67F")));
        pieChartWorkLive.addPieSlice(new PieModel("직장인구",11210,Color.parseColor("#00BFFF")));
        pieChartWorkLive.startAnimation();

        barChartHouseHold.clearChart();
        barChartHouseHold.addBar(new BarModel("1인세대",2043,Color.parseColor("#00BFFF")));
        barChartHouseHold.addBar(new BarModel("2인세대",1135,Color.parseColor("#00BFFF")));
        barChartHouseHold.addBar(new BarModel("3인세대",991,Color.parseColor("#00BFFF")));
        barChartHouseHold.addBar(new BarModel("4인세대",915,Color.parseColor("#00BFFF")));
        barChartHouseHold.addBar(new BarModel("5인세대",234,Color.parseColor("#00BFFF")));

        barChartHouseHold.startAnimation();

        return view;
    }
}