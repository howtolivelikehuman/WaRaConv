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

import java.util.ArrayList;

public class InputFragment1 extends Fragment {
    Activity activity;
    Input input;
    Bundle bundle;
    ArrayList<String> arealist;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity) {
            // 사용될 activity 에 context 정보 가져오는 부분
            this.activity = (Activity)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState){
        arealist = new ArrayList<String>();
        View view = inflater.inflate(R.layout.inputfragment1, container, false);

        bundle = getArguments();
        //처음 실행됨
        if(bundle == null){
            input = new Input();
        }
        else{
            input = bundle.getParcelable("input");
            Toast.makeText(container.getContext(),"2에서 옴, 비율 : " + Integer.toString(input.getRatio()),Toast.LENGTH_LONG).show();
        }

        ImageButton nextBtn = view.findViewById(R.id.nextButton);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arealist.size() <1){
                    arealist.add("전체");
                }
                input.setArea(arealist);
                ((InputListener)activity).inputSet(input,2);
            }
        });
        return view;
    }
}
