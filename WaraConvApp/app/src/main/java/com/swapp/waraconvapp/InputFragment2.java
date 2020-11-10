package com.swapp.waraconvapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class InputFragment2 extends Fragment {
    Activity activity;
    Input input;
    SeekBar seekBar;
    TextView profit_ratio, safe_ratio, textView_description;
    int ratio = 50; //default

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
        View view = inflater.inflate(R.layout.inputfragment2, container, false);


        profit_ratio = view.findViewById(R.id.profit_ratio);
        safe_ratio = view.findViewById(R.id.safe_ratio);
        textView_description = view.findViewById(R.id.textView_description);

        textView_description.setText("설명\n 수익성은 매출을 기반으로 도출한 결과입니다.\n 예상 매출이 높을수록 높은 점수를 부여합니다.\n " +
                "안전성은 폐업률을 기반으로 도출한 결과입니다.\n 예상 폐업률이 낮을수록 높은 점수를 부여합니다.\n\n 입력하신 비율에 따라 수익성과 안전성의 가중치를 주어 점수화를 통해 랭킹을 부여합니다.");

        //bundle 받아서 처리
        input = getArguments().getParcelable("input");
        ratio = input.getRatio();
        Toast.makeText(container.getContext(),"2에서 옴, 비율 : " + Integer.toString(ratio),Toast.LENGTH_LONG).show();
        profit_ratio.setText(Integer.toString(ratio)+"%");
        safe_ratio.setText(Integer.toString(100-ratio) + "%");


        seekBar = view.findViewById(R.id.seekBar);
        seekBar.setProgress(ratio);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ratio = seekBar.getProgress();
                profit_ratio.setText(Integer.toString(ratio)+"%");
                safe_ratio.setText(Integer.toString(100-ratio) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                ratio = seekBar.getProgress();
                profit_ratio.setText(Integer.toString(ratio)+"%");
                safe_ratio.setText(Integer.toString(100-ratio) + "%");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ratio = seekBar.getProgress();
                profit_ratio.setText(Integer.toString(ratio)+"%");
                safe_ratio.setText(Integer.toString(100-ratio) + "%");
            }
        });

        ImageButton nextBtn = view.findViewById(R.id.nextButton);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setRatio(ratio);
                ((InputListener)activity).inputSet(input,3);
            }
        });

        ImageButton backBtn = view.findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setRatio(ratio);
                ((InputListener)activity).inputSet(input,1);
            }
        });


        return view;
    }
}