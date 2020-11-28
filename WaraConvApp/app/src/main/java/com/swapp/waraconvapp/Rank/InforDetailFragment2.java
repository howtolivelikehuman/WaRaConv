package com.swapp.waraconvapp.Rank;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.swapp.waraconvapp.DB.DetailInfo;
import com.swapp.waraconvapp.R;

import java.util.ArrayList;
import java.util.List;


public class InforDetailFragment2 extends Fragment {
    Activity activity;
    LineChart lineChart_profit;
    LineChart lineChart_stable;
    DetailInfo detailInfo;
    int[][] profit = new int[3][];
    float[][] survive = new float[3][];

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity) {
            // 사용될 activity 에 context 정보 가져오는 부분
            this.activity = (Activity)context;
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infor_detail2, container, false);
        Bundle bundle = getArguments();

        detailInfo = bundle.getParcelable("detailInfo");
        profit[0] = bundle.getIntArray("profit0");
        profit[1] = bundle.getIntArray("profit1");
        profit[2] = bundle.getIntArray("profit2");

        survive[0] = bundle.getFloatArray("survive0");
        survive[1] = bundle.getFloatArray("survive1");
        survive[2] = bundle.getFloatArray("survive2");

        lineChart_profit = (LineChart) view.findViewById(R.id.rentalChart_profit);
        lineChart_stable = (LineChart) view.findViewById(R.id.rentalChart_stable);

        setGraph(lineChart_profit,profit,"3개월간 매출, 원 (2분기)");
        setGraph(lineChart_stable,survive,"3개월간 개폐업수, % (2분기)");



        RentalMarkerView_ps markerView1 = new RentalMarkerView_ps(activity.getApplicationContext(), R.layout.markerview,1);
        markerView1.setChartView(lineChart_stable);
        lineChart_stable.setMarker(markerView1);
        lineChart_stable.setExtraRightOffset(30f);

        RentalMarkerView_ps markerView2 = new RentalMarkerView_ps(activity.getApplicationContext(), R.layout.markerview,0);
        markerView2.setChartView(lineChart_profit);
        lineChart_profit.setMarker(markerView2);
        lineChart_profit.setExtraRightOffset(30f);

        return view;
    }
    public void setGraph(LineChart lineChart, int[][] list, String des){
        String[] colors = {"#7BBEEB","#72CAAF","#F49DB8"};

        LineDataSet[] lineDataSets = new LineDataSet[4];
        List<Entry>[] entry = new List[4];
        for(int i=0; i<3; i++){
            entry[i] = new ArrayList<>();
            for(int j=0; j<3; j++){
                entry[i].add(new Entry(j,list[i][j]));
            }
        }

        //expected2020
        entry[3] = new ArrayList<>();
        entry[3].add(new Entry(2,list[2][3]));
        setGraph(lineDataSets, lineChart, des, entry);
    }
    public void setGraph(LineChart lineChart, float[][] list, String des){
        List<Entry>[] entry = new List[4];
        LineDataSet[] lineDataSets = new LineDataSet[4];
        for(int i=0; i<3; i++){
            entry[i] = new ArrayList<>();
            for(int j=0; j<3; j++){
                entry[i].add(new Entry(j,list[i][j]));
            }
        }
        //expected2020
        entry[3] = new ArrayList<>();
        entry[3].add(new Entry(2,list[2][3]));
        setGraph(lineDataSets, lineChart, des, entry);
    }
    public void setGraph(LineDataSet[] lineDataSets, LineChart lineChart, String des, List<Entry>[] entry){
        String[] colors = {"#7BBEEB","#72CAAF","#F49DB8","#F8B990"};

        lineDataSets[0] = new LineDataSet(entry[0], "서울특별시");
        lineDataSets[1] = new LineDataSet(entry[1], detailInfo.getParentName());
        lineDataSets[2] = new LineDataSet(entry[2], detailInfo.getName());
        lineDataSets[3] = new LineDataSet(entry[3], detailInfo.getName()+" [예측]");

        for(int i=0; i<4; i++){
            lineDataSets[i].setLineWidth(2);
            lineDataSets[i].setCircleRadius(5);
            lineDataSets[i].setCircleColor(Color.parseColor(colors[i]));
            lineDataSets[i].setColor(Color.parseColor(colors[i]));
            lineDataSets[i].setDrawCircles(true);
            lineDataSets[i].setDrawHorizontalHighlightIndicator(false);
            lineDataSets[i].setDrawHighlightIndicators(false);
            lineDataSets[i].setDrawValues(false);
        }

        LineData lineData = new LineData(lineDataSets);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3,true);
        final String xVal[]={"2018년","2019년","2020년"};
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xVal[(int) value]; // xVal is a string array
            }
        });

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setDrawLabels(false);
        yLAxis.setDrawAxisLine(true);
        yLAxis.setDrawGridLines(true);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText(des);
        lineChart.setDescription(description);

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.animateY(2000, Easing.EasingOption.EaseInQuad);
        lineChart.invalidate();
    }
}
