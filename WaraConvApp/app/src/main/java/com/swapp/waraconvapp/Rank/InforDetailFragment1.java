package com.swapp.waraconvapp.Rank;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.swapp.waraconvapp.Input.RentalMarkerView;
import com.swapp.waraconvapp.R;

import java.util.ArrayList;
import java.util.List;

public class InforDetailFragment1 extends Fragment {

    Activity activity;
    TextView TextViewName;
    TextView TextViewRank;
    TextView TextViewParentName;
    TextView TextViewTotalScore;
    TextView TextViewProfitScore;
    TextView TextViewStableScore;
    TextView TextViewRatio;

    TextView TextViewConv;
    TextView TextViewParentConv;
    TextView TextViewCityConv;
    TextView TextViewGuConv;
    TextView TextViewDongConv;

    LineChart lineChart;

    DetailInfo detailInfo;
    int[] rentseoul;
    int[] rentgu;
    int[] rentdong;
    int[] convnum;

    public InforDetailFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity) {
            // 사용될 activity 에 context 정보 가져오는 부분
            this.activity = (Activity)context;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infor_detail1, container, false);

        TextViewName=view.findViewById(R.id.textName);
        TextViewParentName=view.findViewById(R.id.textParentName);
        TextViewRank=view.findViewById(R.id.textRank);
        TextViewTotalScore=view.findViewById(R.id.textView_totalscore);
        TextViewProfitScore=view.findViewById(R.id.textView_profitscore);
        TextViewStableScore=view.findViewById(R.id.textView_stablescore);
        TextViewRatio = view.findViewById(R.id.textViewRatio);
        TextViewConv = view.findViewById(R.id.textViewConv);
        TextViewParentConv = view.findViewById(R.id.textViewParentConv);
        TextViewCityConv = view.findViewById(R.id.textViewCityConv);
        TextViewGuConv = view.findViewById(R.id.textViewGuConv);
        TextViewDongConv = view.findViewById(R.id.textViewDongConv);

        Bundle bundle = getArguments();
        detailInfo = bundle.getParcelable("detailInfo");
        rentseoul = bundle.getIntArray("rent0");
        rentgu = bundle.getIntArray("rent1");
        rentdong = bundle.getIntArray("rent2");
        convnum = bundle.getIntArray("convnum");


        detailInfo.getParent();
        detailInfo.getCode();

        TextViewName.setText(detailInfo.getName());
        TextViewParentName.setText(detailInfo.getParentName());
        TextViewRank.setText(Integer.toString(detailInfo.getRanknum()) + "순위");
        TextViewTotalScore.setText(Float.toString(detailInfo.getTotalscore())+ "점");
        TextViewProfitScore.setText(Float.toString(detailInfo.getProfitscore())+ "점");
        TextViewStableScore.setText(Float.toString(detailInfo.getStablescore())+ "점");
        TextViewRatio.setText("(설정 가중치 비율 = "+detailInfo.getRatio()+ " : " +(100-detailInfo.getRatio())+")");

        TextViewConv.setText(detailInfo.getName());
        TextViewParentConv.setText(detailInfo.getParentName());
        TextViewCityConv.setText(Integer.toString(convnum[0]));
        TextViewGuConv.setText(Integer.toString(convnum[1]));
        TextViewDongConv.setText(Integer.toString(convnum[2]));


        lineChart = (LineChart) view.findViewById(R.id.rentalChart);
        setGraph(lineChart);

        RentalMarkerView_rent markerView = new RentalMarkerView_rent(activity.getApplicationContext(), R.layout.markerview);
        markerView.setChartView(lineChart);
        lineChart.setMarker(markerView);


        return view;
    }
    public void setGraph(LineChart lineChart){
        List<Entry> entries1 =new ArrayList<>();
        List<Entry> entries2 =new ArrayList<>();
        List<Entry> entries3 =new ArrayList<>();

        for(int i=0; i<3; i++){
            entries1.add(new Entry(i,rentseoul[i]));
            entries2.add(new Entry(i,rentgu[i]));
            entries3.add(new Entry(i,rentdong[i]));
        }

        LineDataSet lineDataSet1 = new LineDataSet(entries1, "서울 특별시");
        lineDataSet1.setLineWidth(2);
        lineDataSet1.setCircleRadius(5);
        lineDataSet1.setCircleColor(Color.parseColor("#7BBEEB"));
        //lineDataSet1.setCircleColorHole(Color.parseColor("#5E9F76"));
        lineDataSet1.setColor(Color.parseColor("#7BBEEB"));
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setDrawHorizontalHighlightIndicator(false);
        lineDataSet1.setDrawHighlightIndicators(false);
        lineDataSet1.setDrawValues(false);

        LineDataSet lineDataSet2 = new LineDataSet(entries2, detailInfo.getParentName());
        lineDataSet2.setLineWidth(2);
        lineDataSet2.setCircleRadius(5);
        lineDataSet2.setCircleColor(Color.parseColor("#72CAAF"));
        lineDataSet2.setColor(Color.parseColor("#72CAAF"));
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setDrawHorizontalHighlightIndicator(false);
        lineDataSet2.setDrawHighlightIndicators(false);
        lineDataSet2.setDrawValues(false);

        LineDataSet lineDataSet3 = new LineDataSet(entries3, detailInfo.getName());
        lineDataSet3.setLineWidth(2);
        lineDataSet3.setCircleRadius(5);
        lineDataSet3.setCircleColor(Color.parseColor("#F49DB8"));
        lineDataSet3.setColor(Color.parseColor("#F49DB8"));
        lineDataSet3.setDrawCircles(true);
        lineDataSet3.setDrawHorizontalHighlightIndicator(false);
        lineDataSet3.setDrawHighlightIndicators(false);
        lineDataSet3.setDrawValues(false);



        LineData lineData = new LineData(lineDataSet1);
        lineData.addDataSet(lineDataSet2);
        lineData.addDataSet(lineDataSet3);
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
        description.setText("1평 당 1층 평균 임대료, 원 (2분기)");
        lineChart.setDescription(description);

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.animateY(1500, Easing.EasingOption.EaseInExpo);
        lineChart.invalidate();

    }
}