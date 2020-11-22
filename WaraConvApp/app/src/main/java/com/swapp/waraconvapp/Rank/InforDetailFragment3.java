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

    //data
    int code;
    int[] gender;
    int[] generation;

    PieChart pieChartSex;
    BarChart barChartAge;

    TextView textViewSexDetail;

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
        code = bundle.getInt("code");
        gender = bundle.getIntArray("gender");
        generation = bundle.getIntArray("generation");

        View view = inflater.inflate(R.layout.fragment_infor_detail3, container, false);

        textViewSexDetail=view.findViewById(R.id.textViewSexDetail);
        textViewSexDetail.setText("남성:" + gender[0]+ "명  여성:" + gender[1]+"명");

        pieChartSex=view.findViewById(R.id.pieChartSex);
        barChartAge=view.findViewById(R.id.barChartAge);

        pieChartSex.clearChart();
        pieChartSex.addPieSlice(new PieModel("남성",gender[0], Color.parseColor("#CDA67F")));
        pieChartSex.addPieSlice(new PieModel("여성",gender[1],Color.parseColor("#00BFFF")));
        pieChartSex.startAnimation();

        barChartAge.clearChart();
        for(int i=0; i<generation.length;i++){
            barChartAge.addBar(new BarModel((i+1)+"0대",generation[i],Color.parseColor("#00BFFF")));
        }
        barChartAge.startAnimation();

        return view;
    }
}