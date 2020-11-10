package com.swapp.waraconvapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class InputFragment3 extends Fragment {

    Activity activity;
    Input input;
    int rentalmin = 0; //default
    int rentalmax = Integer.MAX_VALUE; //default

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

        input = getArguments().getParcelable("input");
        Toast.makeText(container.getContext(),"2에서 옴, 비율 : " + Integer.toString(input.getRatio()),Toast.LENGTH_LONG).show();

        ImageButton nextBtn = view.findViewById(R.id.nextButton);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setRentalmin(rentalmin);
                input.setRentalmax(rentalmax);
                ((InputListener)activity).inputSet(input,3);
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