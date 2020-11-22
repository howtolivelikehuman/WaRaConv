package com.swapp.waraconvapp.Rank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swapp.waraconvapp.DB.DetailInfo;
import com.swapp.waraconvapp.R;

public class InforDetailFragment1 extends Fragment {

    TextView TextViewName;
    TextView TextViewRank;
    TextView TextViewParentName;
    TextView TextViewTotalScore;
    TextView TextViewProfitScore;
    TextView TextViewStableScore;

    DetailInfo detailInfo;

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

        TextViewName=view.findViewById(R.id.textName);
        TextViewParentName=view.findViewById(R.id.textParentName);
        TextViewRank=view.findViewById(R.id.textRank);
        TextViewTotalScore=view.findViewById(R.id.textView_totalscore);
        TextViewProfitScore=view.findViewById(R.id.textView_profitscore);
        TextViewStableScore=view.findViewById(R.id.textView_stablescore);

        Bundle bundle = getArguments();
        detailInfo = bundle.getParcelable("detailInfo");
        TextViewName.setText(detailInfo.getName());
        TextViewParentName.setText(detailInfo.getParentName());
        TextViewRank.setText(Integer.toString(detailInfo.getRanknum()) + "순위");
        TextViewTotalScore.setText(Float.toString(detailInfo.getTotalscore())+ "점");
        TextViewProfitScore.setText(Float.toString(detailInfo.getProfitscore())+ "점");
        TextViewStableScore.setText(Float.toString(detailInfo.getStablescore())+ "점");

        return view;
    }
}