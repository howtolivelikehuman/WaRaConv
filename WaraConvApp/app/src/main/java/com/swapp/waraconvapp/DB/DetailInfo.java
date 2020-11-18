package com.swapp.waraconvapp.DB;

import android.os.Parcel;
import android.os.Parcelable;

import com.swapp.waraconvapp.Input.Input;

public class DetailInfo implements Parcelable {
    int code;
    String name;
    String parentName;
    int ranknum;
    float profitscore;
    float stablescore;
    float totalscore;
    int parent;

    //data
    int[] male;
    int[] female;
    int[] onehouse;
    int[] convnum;
    int[] rent;

    public DetailInfo(){ }
    public DetailInfo(Parcel in){
        readFromParcel(in);
    }
    public void readFromParcel(Parcel in){
        code = in.readInt();
        name = in.readString();
        parentName = in.readString();
        parent = in.readInt();
        ranknum = in.readInt();
        profitscore = in.readFloat();
        stablescore = in.readFloat();
        totalscore = in.readFloat();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel in, int i){
        in.writeInt(code);
        in.writeString(name);
        in.writeString(parentName);
        in.writeInt(ranknum);
        in.writeFloat(profitscore);
        in.writeFloat(stablescore);
        in.writeFloat(totalscore);
    }

    public static final Parcelable.Creator<DetailInfo> CREATOR = new Parcelable.Creator<DetailInfo>(){
        public DetailInfo createFromParcel(Parcel in){
            return new DetailInfo(in);
        }
        @Override
        public DetailInfo[] newArray(int i) {
            return new DetailInfo[i];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int[] getMale() {
        return male;
    }

    public void setMale(int[] male) {
        this.male = male;
    }

    public int[] getFemale() {
        return female;
    }

    public void setFemale(int[] female) {
        this.female = female;
    }

    public int[] getOnehouse() {
        return onehouse;
    }

    public void setOnehouse(int[] onehouse) {
        this.onehouse = onehouse;
    }

    public int[] getConvnum() {
        return convnum;
    }

    public void setConvnum(int[] convnum) {
        this.convnum = convnum;
    }

    public int[] getRent() {
        return rent;
    }

    public void setRent(int[] rent) {
        this.rent = rent;
    }

    public int getRanknum() {
        return ranknum;
    }

    public void setRanknum(int ranknum) {
        this.ranknum = ranknum;
    }

    public float getProfitscore() {
        return profitscore;
    }

    public void setProfitscore(float profitscore) {
        this.profitscore = profitscore;
    }

    public float getStablescore() {
        return stablescore;
    }

    public void setStablescore(float stablescore) {
        this.stablescore = stablescore;
    }

    public float getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(float totalscore) {
        this.totalscore = totalscore;
    }
}
