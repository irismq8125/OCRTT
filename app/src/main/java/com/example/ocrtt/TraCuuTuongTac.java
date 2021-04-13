package com.example.ocrtt;

public class TraCuuTuongTac {
    String name, stt, mahc1, tenhc1, mahc2, tenhc2, mucdo, noidung;

    public TraCuuTuongTac() {
    }

    public TraCuuTuongTac(String name, String stt, String mahc1, String tenhc1, String mahc2, String tenhc2, String mucdo, String noidung) {
        this.name = name;
        this.stt = stt;
        this.mahc1 = mahc1;
        this.tenhc1 = tenhc1;
        this.mahc2 = mahc2;
        this.tenhc2 = tenhc2;
        this.mucdo = mucdo;
        this.noidung = noidung;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getMahc1() {
        return mahc1;
    }

    public void setMahc1(String mahc1) {
        this.mahc1 = mahc1;
    }

    public String getTenhc1() {
        return tenhc1;
    }

    public void setTenhc1(String tenhc1) {
        this.tenhc1 = tenhc1;
    }

    public String getMahc2() {
        return mahc2;
    }

    public void setMahc2(String mahc2) {
        this.mahc2 = mahc2;
    }

    public String getTenhc2() {
        return tenhc2;
    }

    public void setTenhc2(String tenhc2) {
        this.tenhc2 = tenhc2;
    }

    public String getMucdo() {
        return mucdo;
    }

    public void setMucdo(String mucdo) {
        this.mucdo = mucdo;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
