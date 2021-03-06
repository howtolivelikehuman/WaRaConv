package com.swapp.waraconvapp.Input;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swapp.waraconvapp.DB.DataBase;
import com.swapp.waraconvapp.DB.DatabaseHelper;
import com.swapp.waraconvapp.R;

import java.util.ArrayList;
import java.util.List;

public class InputFragment1 extends Fragment {
    Activity activity;
    Input input;
    Bundle bundle;
    ArrayList<String> area;
    private RecyclerView recyclerView;
    AreaAdapter areaAdapter;

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
        area = new ArrayList<String>();
        View view = inflater.inflate(R.layout.inputfragment1, container, false);

        recyclerView = view.findViewById(R.id.area_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        List<AreaAdapter.Gu> data = new ArrayList<>();

        /*for(int i=0; i<areaList.length; i++){
            String s = areaList[i];

            if(s.lastIndexOf("구") == s.length()-1 && s.length() <= 3){
                data.add(new AreaAdapter.Gu(AreaAdapter.HEADER, s));
            }
            else{
                data.add(new AreaAdapter.Gu(AreaAdapter.CHILD,s));
            }
        }
        recyclerView.setAdapter(new AreaAdapter(data));*/

        DataBase db = new DataBase(new DatabaseHelper(activity.getApplicationContext()));
        area = db.findArea();

        //맨 윗줄 서울 제외
        int i=1;
        while(i < area.size()) {
            String s = area.get(i);
            AreaAdapter.Gu gu;

            if (s.lastIndexOf("구") == s.length() - 1) {

                gu = new AreaAdapter.Gu(AreaAdapter.HEADER, s);
                gu.invisibleChildren = new ArrayList<>();
                //Log.d("구", s + " " + Integer.toString(i));
                s = area.get(++i);

                while (s.lastIndexOf("동") == s.length() - 1) {
                    //Log.d("동", s + " " + Integer.toString(i));
                    gu.invisibleChildren.add(new AreaAdapter.Gu(AreaAdapter.CHILD, s));

                    if (i == area.size() - 1) {
                        i++;
                        break;
                    } else s = area.get(++i);
                }
                data.add(gu);
            }
        }
        /*while(i < areaList.length){
            String s = areaList[i];
            AreaAdapter.Gu gu;

            if(s.lastIndexOf("구") == s.length()-1){

                gu = new AreaAdapter.Gu(AreaAdapter.HEADER, s);
                gu.invisibleChildren = new ArrayList<>();
                //Log.d("구", s + " " + Integer.toString(i));
                s = areaList[++i];

                while(s.lastIndexOf("동") == s.length()-1) {
                    //Log.d("동", s + " " + Integer.toString(i));
                    gu.invisibleChildren.add(new AreaAdapter.Gu(AreaAdapter.CHILD, s));

                    if (i == areaList.length - 1) {
                        i++;
                        break;
                    } else s = areaList[++i];
                }
                data.add(gu);
            }
        }*/

        areaAdapter = new AreaAdapter(data);
        recyclerView.setAdapter(areaAdapter);
        area = areaAdapter.area;

        bundle = getArguments();

        //처음 실행됨
        if(bundle == null){
            input = new Input();
        }
        else{
            input = bundle.getParcelable("input");
        }

        ImageButton backBtn = view.findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputListener)activity).inputSet(input,0);
            }
        });

        ImageButton nextBtn = view.findViewById(R.id.nextButton);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(area.size() <1){
                    area.add("전체");
                }
                input.setArea(area);
                ((InputListener)activity).inputSet(input,2);
            }
        });
        return view;
    }
}


