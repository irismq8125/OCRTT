package com.example.ocrtt;

public class BangHoatChat {
    String mahoatchat, tenhoatchat;
    int ID;

    public BangHoatChat() {
    }

    public BangHoatChat(int anInt,String mahoatchat, String tenhoatchat) {
        this.ID = anInt;
        this.mahoatchat = mahoatchat;
        this.tenhoatchat = tenhoatchat;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMahoatchat() {
        return mahoatchat;
    }

    public void setMahoatchat(String mahoatchat) {
        this.mahoatchat = mahoatchat;
    }

    public String getTenhoatchat() {
        return tenhoatchat;
    }

    public void setTenhoatchat(String tenhoatchat) {
        this.tenhoatchat = tenhoatchat;
    }

    @Override
    public String toString()  {
        return this.mahoatchat;
    }
}
