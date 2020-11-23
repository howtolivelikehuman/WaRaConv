package com.swapp.waraconvapp.Input;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.swapp.waraconvapp.DB.DataBase;
import com.swapp.waraconvapp.DB.DatabaseHelper;
import com.swapp.waraconvapp.R;

import java.util.ArrayList;
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

        DataBase db = new DataBase(new DatabaseHelper(activity.getApplicationContext()));
        ArrayList<String[]> rental = db.findRent();
       // ArrayList<Integer> rental = new ArrayList<>(Arrays.asList(Area.rental));

        for(int i=0; i<rental.size(); i++){
            entries.add(new Entry(i,Integer.parseInt(rental.get(i)[1])));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "지역구");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(5);
        lineDataSet.setCircleColor(Color.parseColor("#BAE0BD"));
        lineDataSet.setColor(Color.parseColor("#BAE0BD"));
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
        description.setText("1평 당 1층 평균 임대료, 원");
        lineChart.setDescription(description);

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.animateY(1500, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();

        RentalMarkerView markerView = new RentalMarkerView(activity.getApplicationContext(), R.layout.markerview);
        markerView.setRental(rental);
        markerView.setChartView(lineChart);
        lineChart.setMarker(markerView);

        editTextmax = (EditText) view.findViewById(R.id.editTextmax);
        editTextmin = (EditText) view.findViewById(R.id.editTextmin);

        input = getArguments().getParcelable("input");

        ImageButton nextBtn = view.findViewById(R.id.nextButton);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editTextmax.getText().toString().length() > 1){
                    rentalmax = Integer.parseInt(editTextmax.getText().toString());
                }
                if(editTextmin.getText().toString().length() > 1){
                    rentalmin = Integer.parseInt(editTextmin.getText().toString());
                    if(rentalmax>rentalmin){
                        input.setRentalmin(rentalmin);
                        input.setRentalmax(rentalmax);
                        ((InputListener)activity).inputSet(input,4);
                    }else{
                        Toast.makeText(getContext(), "올바른 금액을 입력하세요", Toast.LENGTH_SHORT).show();
                    }

                }
                ((InputListener)activity).inputSet(input,4);
            }
        });

        ImageButton backBtn = view.findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputListener)activity).inputSet(input,2);
            }
        });


        return view;
    }
}