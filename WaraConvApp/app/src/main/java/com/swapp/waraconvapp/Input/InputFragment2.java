package com.swapp.waraconvapp.Input;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.swapp.waraconvapp.R;

public class InputFragment2 extends Fragment {
    Activity activity;
    Input input;
    SeekBar seekBar;
    TextView profit_ratio, safe_ratio, textView_description;
    int ratio = 50;

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

        //bundle 받아서 처리
        input = getArguments().getParcelable("input");
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