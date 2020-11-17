package com.swapp.waraconvapp.Input;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.swapp.waraconvapp.R;
import com.swapp.waraconvapp.Rank.RankActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputFragment3 extends Fragment {

    Activity activity;
    Input input;
    int rentalmin = 0; //default
    int rentalmax = 999999; //default
    LineChart lineChart;
    EditText editTextmax, editTextmin;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity) {
            // 사용될 activity 에 context 정보 가져오는 부분
            this.activity = (Activity)context;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.inputfragment3, container, false);

        lineChart = (LineChart) view.findViewById(R.id.rentalChart);
        List<Entry> entries =new ArrayList<>();

        ArrayList<Integer> rental = new ArrayList<>(Arrays.asList(Area.rental));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            rental.sort(null);
        }

        for(int i=0; i<rental.size(); i++){
            entries.add(new Entry(i,rental.get(i)));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "지역구");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(5);
        lineDataSet.setCircleColor(Color.parseColor("#BAE0BD"));
        lineDataSet.setCircleColorHole(Color.parseColor("#5E9F76"));
        lineDataSet.setColor(Color.parseColor("#BAE0BD"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setEnabled(false);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("단위 : 1평 당 임대료,원");
        lineChart.setDescription(description);

        lineChart.setDoubleTapToZoomEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.animateY(1500, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();

        RentalMarkerView markerView = new RentalMarkerView(activity.getApplicationContext(), R.layout.markerview);
        markerView.setChartView(lineChart);
        lineChart.setMarker(markerView);

        editTextmax = (EditText) view.findViewById(R.id.editTextmax);
        editTextmin = (EditText) view.findViewById(R.id.editTextmin);

        input = getArguments().getParcelable("input");
        Log.d("도시들", Integer.toString(input.getArea().size()));
        Log.d("비율", Integer.toString(input.getRatio()));

        ImageButton nextBtn = view.findViewById(R.id.nextButton);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setRentalmin(rentalmin);
                input.setRentalmax(rentalmax);
                //((InputListener)activity).inputSet(input,4);

                Intent intent = new Intent(getContext(), RankActivity.class);
                startActivity(intent);
            }
        });

        ImageButton backBtn = view.findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setRentalmin(rentalmin);
                input.setRentalmax(rentalmax);
                ((InputListener)activity).inputSet(input,2);
            }
        });


        return view;
    }
}