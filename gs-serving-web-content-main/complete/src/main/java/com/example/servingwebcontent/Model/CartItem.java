package com.example.servingwebcontent.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.io.Serializable;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private int maMonAn;

    @JsonAlias({"tenMon", "tenMonAn"})
    private String tenMonAn;

    private int soLuong;

    @JsonAlias({"gia", "donGia"}) // ✅ hỗ trợ cả "gia" và "donGia"
    private int donGia;

    private int thanhTien;

    public CartItem() {}

    public CartItem(int maMonAn, String tenMonAn, int soLuong, int donGia) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }

    // ==== GETTERS ====
    public int getMaMonAn() {
        return maMonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    // ==== SETTERS ====
    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        updateThanhTien();
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
        updateThanhTien();
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    private void updateThanhTien() {
        this.thanhTien = this.soLuong * this.donGia;
    }
}
