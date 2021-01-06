/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class SinhVienMonHoc {
    private String masinhvienmonhoc;
    private String masinhvien;
    private String mamonhoc;

    public SinhVienMonHoc() {
    }
    
    public SinhVienMonHoc(String masinhvienmonhoc, String masinhvien, String mamonhoc) {
        this.masinhvienmonhoc = masinhvienmonhoc;
        this.masinhvien = masinhvien;
        this.mamonhoc = mamonhoc;
    }

    public String getMasinhvienmonhoc() {
        return masinhvienmonhoc;
    }

    public void setMasinhvienmonhoc(String masinhvienmonhoc) {
        this.masinhvienmonhoc = masinhvienmonhoc;
    }

    public String getMasinhvien() {
        return masinhvien;
    }

    public void setMasinhvien(String masinhvien) {
        this.masinhvien = masinhvien;
    }

    public String getMamonhoc() {
        return mamonhoc;
    }

    public void setMamonhoc(String mamonhoc) {
        this.mamonhoc = mamonhoc;
    }
    
}
