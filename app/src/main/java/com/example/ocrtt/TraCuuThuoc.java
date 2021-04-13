package com.example.ocrtt;

public class TraCuuThuoc {
    String stt, ma, ten;

    public TraCuuThuoc() {
    }

    public TraCuuThuoc(String stt, String ma, String ten) {
        this.stt = stt;
        this.ma = ma;
        this.ten = ten;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
