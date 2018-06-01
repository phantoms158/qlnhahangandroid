package com.example.administrator.quanlynhahang.DTO;

public class banan {
    public String tenbanan;
    public String anhbanan;
    public String tinhtrang;

    public banan(String tenbanan, String anhbanan, String tinhtrang) {
        this.tenbanan = tenbanan;
        this.anhbanan = anhbanan;
        this.tinhtrang = tinhtrang;
    }

    public String getTenbanan() {
        return tenbanan;
    }

    public void setTenbanan(String tenbanan) {
        this.tenbanan = tenbanan;
    }

    public String getAnhbanan() {
        return anhbanan;
    }

    public void setAnhbanan(String anhbanan) {
        this.anhbanan = anhbanan;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
}
