package com.example.servingwebcontent.Model;

public class CartItem {
    private String tenMonAn;
    private int soLuong;
    private int donGia;

    public CartItem() {}

    public CartItem(String tenMonAn, int soLuong, int donGia) {
        this.tenMonAn = tenMonAn;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getThanhTien() {
        return donGia * soLuong;
    }
}
