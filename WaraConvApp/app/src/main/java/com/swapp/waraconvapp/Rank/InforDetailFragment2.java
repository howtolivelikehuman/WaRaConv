package com.swapp.waraconvapp.Rank;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.swapp.waraconvapp.R;

import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

public class InforDetailFragment2 extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        View view = inflater.inflate(R.layout.fragment_infor_detail2, container, false);
        return view;
    }
}
