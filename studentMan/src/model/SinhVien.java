/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class SinhVien {
    private String masinhvien;
    private String hovaten;
    private Date ngaysinh;
    private String quequan;
    private String khoa;

    public SinhVien() {
    }

    public SinhVien(String masinhvien, String hovaten, Date ngaysinh, String quequan, String khoa) {
        this.masinhvien = masinhvien;
        this.hovaten = hovaten;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
        this.khoa = khoa;
    }

    public String getMasinhvien() {
        return masinhvien;
    }

    public void setMasinhvien(String masinhvien) {
        this.masinhvien = masinhvien;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }
    
}
