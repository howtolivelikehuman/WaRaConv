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

public class InforDetailFragment4 extends Fragment {

    DetailInfo detailInfo;
    PieChart pieChartWorkLive;
    BarChart barChartHouseHold;

    TextView textViewWorkLiveDetail;
    int code;
    int[] live_work;
    int[] house;

    public InforDetailFragment4() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        code = bundle.getInt("code");
        code = bundle.getInt("code");
        live_work = bundle.getIntArray("live_work");
        house = bundle.getIntArray("house");

        View view = inflater.inflate(R.layout.fragment_infor_detail4, container, false);

        textViewWorkLiveDetail=view.findViewById(R.id.textViewWorkLiveDetail);
        textViewWorkLiveDetail.setText("주거인구:" + live_work[0]+ "명  직장인구:" + live_work[1]+"명");

        pieChartWorkLive=view.findViewById(R.id.pieChartWorkLive);
        barChartHouseHold=view.findViewById(R.id.barChartHouseHold);

        pieChartWorkLive.clearChart();
        pieChartWorkLive.addPieSlice(new PieModel("주거인구",live_work[0], Color.parseColor("#72CAAF")));
        pieChartWorkLive.addPieSlice(new PieModel("직장인구",live_work[1],Color.parseColor("#F49DB8")));
        pieChartWorkLive.startAnimation();

        barChartHouseHold.clearChart();
        for(int i=0; i<house.length; i++){
            barChartHouseHold.addBar(new BarModel((i+1)+"인세대",house[i],Color.parseColor("#7BBEEB")));
        }
        barChartHouseHold.startAnimation();

        return view;
    }
}