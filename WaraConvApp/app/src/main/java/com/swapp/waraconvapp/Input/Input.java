package com.swapp.waraconvapp.Input;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;

public class Input implements Parcelable {
    private ArrayList<String> area = new ArrayList<String>();
    private int ratio;
    private int rentalmin;
    private int rentalmax;

    public Input(){ //default
        this.ratio = 50;
        this.rentalmin = 0;
        this.rentalmax = Integer.MAX_VALUE;
    };
    public Input(ArrayList<String> area, int ratio, int rmin, int rmax){
        this.area = area;
        this.ratio = ratio;
        this.rentalmin = rmin;
        this.rentalmax = rmax;
    }

    public Input(Parcel in){
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in){
        area = (ArrayList<String>)in.readSerializable();
        ratio = in.readInt();
        rentalmin = in.readInt();
        rentalmax = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel in, int i){
        in.writeSerializable(area);
        in.writeInt(ratio);
        in.writeInt(rentalmin);
        in.writeInt(rentalmax);
    }

    public static final Parcelable.Creator<Input> CREATOR = new Parcelable.Creator<Input>(){
        public Input createFromParcel(Parcel in){
            return new Input(in);
        }
        @Override
        public Input[] newArray(int i) {
            return new Input[i];
        }
    };

    public ArrayList<String> getArea() {
        return area;
    }

    public void setArea(ArrayList<String> area) {
        this.area = area;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public int getRentalmin() {
        return rentalmin;
    }

    public void setRentalmin(int rentalmin) {
        this.rentalmin = rentalmin;
    }

    public int getRentalmax() {
        return rentalmax;
    }

    public void setRentalmax(int rentalmax) {
        this.rentalmax = rentalmax;
    }
}
