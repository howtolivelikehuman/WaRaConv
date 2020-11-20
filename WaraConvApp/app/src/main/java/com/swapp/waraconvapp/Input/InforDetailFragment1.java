package com.swapp.waraconvapp.Input;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swapp.waraconvapp.R;

public class InforDetailFragment1 extends Fragment {

    TextView Fragment1TextView;

    public InforDetailFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infor_detail1, container, false);

        Fragment1TextView=view.findViewById(R.id.Fragment1TextView);

        Bundle bundle = getArguments();

        String dongName = bundle.getString("dongName");

        Fragment1TextView.setText(dongName);

        return view;
    }
}