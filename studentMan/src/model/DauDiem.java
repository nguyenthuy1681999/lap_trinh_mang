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
public class DauDiem {
    private String madaudiem;
    private String masinhvienmonhoc;
    private float diemcc;
    private float diemkt;
    private float diembtl;
    private float diemthi;

    public DauDiem() {
    }

    public DauDiem(String madaudiem, String masinhvienmonhoc, float diemcc, float diemkt, float diembtl, float diemthi) {
        this.madaudiem = madaudiem;
        this.masinhvienmonhoc = masinhvienmonhoc;
        this.diemcc = diemcc;
        this.diemkt = diemkt;
        this.diembtl = diembtl;
        this.diemthi = diemthi;
    }

    public String getMadaudiem() {
        return madaudiem;
    }

    public void setMadaudiem(String madaudiem) {
        this.madaudiem = madaudiem;
    }

    public String getMasinhvienmonhoc() {
        return masinhvienmonhoc;
    }

    public void setMasinhvienmonhoc(String masinhvienmonhoc) {
        this.masinhvienmonhoc = masinhvienmonhoc;
    }

    public float getDiemcc() {
        return diemcc;
    }

    public void setDiemcc(float diemcc) {
        this.diemcc = diemcc;
    }

    public float getDiemkt() {
        return diemkt;
    }

    public void setDiemkt(float diemkt) {
        this.diemkt = diemkt;
    }

    public float getDiembtl() {
        return diembtl;
    }

    public void setDiembtl(float diembtl) {
        this.diembtl = diembtl;
    }

    public float getDiemthi() {
        return diemthi;
    }

    public void setDiemthi(float diemthi) {
        this.diemthi = diemthi;
    }
    
}
