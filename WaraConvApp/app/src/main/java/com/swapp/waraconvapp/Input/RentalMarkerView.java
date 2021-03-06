package com.swapp.waraconvapp.Input;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.swapp.waraconvapp.R;

import java.util.ArrayList;

public class RentalMarkerView extends MarkerView {
    private TextView tvContent;
    private ArrayList<String[]> rental;

    public void setRental(ArrayList<String[]> rental) {
        this.rental = rental;
    }

    public RentalMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvContent = (TextView)findViewById(R.id.tvContent);
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String text, gu;
        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            text = Utils.formatNumber(ce.getHigh(), 0, false);
            gu = findGu(text);
            tvContent.setText(""+gu+"\r\n"+text+"원");
        } else {
            text = Utils.formatNumber(e.getY(), 0, false);
            gu = findGu(text);
            tvContent.setText(""+gu+"\r\n"+text+"원");
        }
        super.refreshContent(e, highlight);
    }

    public String findGu(String rent){
        int index;
        for(index=0; index<rental.size(); index++){
            if(rental.get(index)[1].equals(rent)){
                return rental.get(index)[0];
            }
        }
        return  null;
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
