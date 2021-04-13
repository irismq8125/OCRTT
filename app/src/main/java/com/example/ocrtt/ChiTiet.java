package com.example.ocrtt;

public class ChiTiet {

    String stt;
    String thuoc;

    String thoigian;
    String cachdung;
    String soluong;


    public ChiTiet() {
    }

    public ChiTiet(String stt, String thuoc,String thoigian, String cachdung, String soluong) {
        this.stt = stt;
        this.thuoc = thuoc;
        this.thoigian = thoigian;
        this.cachdung = cachdung;
        this.soluong = soluong;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getThuoc() {
        return thuoc;
    }

    public void setThuoc(String thuoc) {
        this.thuoc = thuoc;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getCachdung() {
        return cachdung;
    }

    public void setCachdung(String cachdung) {
        this.cachdung = cachdung;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
