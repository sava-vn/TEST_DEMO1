package com.sava.test_demo1;

public class Song {
    private int mID;
    private String mTen;
    private String mCaSi;
    private int mThoiLuong;

    public Song() {
    }

    public Song(int mID, String mTen, String mCaSi, int mThoiLuong) {
        this.mID = mID;
        this.mTen = mTen;
        this.mCaSi = mCaSi;
        this.mThoiLuong = mThoiLuong;
    }

    public Song(String mTen, String mCaSi, int mThoiLuong) {
        this.mTen = mTen;
        this.mCaSi = mCaSi;
        this.mThoiLuong = mThoiLuong;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmTen() {
        return mTen;
    }

    public void setmTen(String mTen) {
        this.mTen = mTen;
    }

    public String getmCaSi() {
        return mCaSi;
    }

    public void setmCaSi(String mCaSi) {
        this.mCaSi = mCaSi;
    }

    public int getmThoiLuong() {
        return mThoiLuong;
    }

    public void setmThoiLuong(int mThoiLuong) {
        this.mThoiLuong = mThoiLuong;
    }
}
