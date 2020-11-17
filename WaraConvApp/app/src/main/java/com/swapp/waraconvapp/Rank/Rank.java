package com.swapp.waraconvapp.Rank;

import android.os.Parcel;
import android.os.Parcelable;

public class Rank {
    int key;
    int rank;
    int score;
    String dongName;

    public Rank(){
    }

    public Rank(int key, int rank, int score, String dongName) {
        this.key = key;
        this.rank = rank;
        this.score = score;
        this.dongName = dongName;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDongName() {
        return dongName;
    }

    public void setDongName(String dongName) {
        this.dongName = dongName;
    }
}
