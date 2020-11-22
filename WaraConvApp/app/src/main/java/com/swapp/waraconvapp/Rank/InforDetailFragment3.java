package com.swapp.waraconvapp.Rank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swapp.waraconvapp.R;

public class InforDetailFragment3 extends Fragment {

    TextView Fragment3TextView;

    public InforDetailFragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_infor_detail3, container, false);

        Fragment3TextView =view.findViewById(R.id.Fragment3TextView);

        Bundle bundle = getArguments();

        String dongName = bundle.getString("dongName");

        Fragment3TextView.setText(dongName);

        return view;
    }
}