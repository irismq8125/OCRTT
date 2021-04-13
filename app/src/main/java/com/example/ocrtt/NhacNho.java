package com.example.ocrtt;

public class NhacNho {

    String donthuoc, ngaybd, ngaykt, gsang, gtrua, gchieu, gtoi;

    public NhacNho() {
    }

    public NhacNho(String donthuoc, String ngaybd, String ngaykt, String gsang, String gtrua, String gchieu, String gtoi) {
        this.donthuoc = donthuoc;
        this.ngaybd = ngaybd;
        this.ngaykt = ngaykt;
        this.gsang = gsang;
        this.gtrua = gtrua;
        this.gchieu = gchieu;
        this.gtoi = gtoi;
    }

    public String getDonthuoc() {
        return donthuoc;
    }

    public void setDonthuoc(String donthuoc) {
        this.donthuoc = donthuoc;
    }

    public String getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(String ngaybd) {
        this.ngaybd = ngaybd;
    }

    public String getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(String ngaykt) {
        this.ngaykt = ngaykt;
    }

    public String getGsang() {
        return gsang;
    }

    public void setGsang(String gsang) {
        this.gsang = gsang;
    }

    public String getGtrua() {
        return gtrua;
    }

    public void setGtrua(String gtrua) {
        this.gtrua = gtrua;
    }

    public String getGchieu() {
        return gchieu;
    }

    public void setGchieu(String gchieu) {
        this.gchieu = gchieu;
    }

    public String getGtoi() {
        return gtoi;
    }

    public void setGtoi(String gtoi) {
        this.gtoi = gtoi;
    }
}
