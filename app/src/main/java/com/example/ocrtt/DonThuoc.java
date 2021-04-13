package com.example.ocrtt;

public class DonThuoc {

    String name;
    String link;
    String nd;
    String sl;


    public DonThuoc() {

    }


    public DonThuoc(String name, String link, String nd, String sl) {
        this.name = name;
        this.link = link;
        this.nd = nd;
        this.sl = sl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }
}
