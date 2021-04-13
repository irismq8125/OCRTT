package com.example.ocrtt;

public class AlarmChiTiet {

    String name;
    String stt;
    String tenthuoc;
    String soluongth;
    String conlai;

    public AlarmChiTiet() {
    }

    public AlarmChiTiet(String name, String stt, String thuoc, String soluong, String conlai) {
        this.name = name;
        this.stt = stt;
        this.tenthuoc = thuoc;
        this.soluongth = soluong;
        this.conlai = conlai;
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

    public String getTenthuoc() {
        return tenthuoc;
    }

    public void setTenthuoc(String tenthuoc) {
        this.tenthuoc = tenthuoc;
    }

    public String getSoluongth() {
        return soluongth;
    }

    public void setSoluongth(String soluongth) {
        this.soluongth = soluongth;
    }

    public String getConlai() {
        return conlai;
    }

    public void setConlai(String conlai) {
        this.conlai = conlai;
    }

}
