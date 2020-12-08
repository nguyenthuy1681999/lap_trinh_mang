/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinesechess;

import java.io.Serializable;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class Player implements Serializable {
    private int id;
    private String ten;

    public Player(String ten, String matkhau) {
        this.ten = ten;
        this.matkhau = matkhau;
    }
    private String matkhau;

    public Player(int id, String ten, String matkhau, int sotranthang, int sotranthua, int sotranhoa, int nuocdithang, int nuocdithua, float diem) {
        this.id = id;
        this.ten = ten;
        this.matkhau = matkhau;
        this.sotranthang = sotranthang;
        this.sotranthua = sotranthua;
        this.sotranhoa = sotranhoa;
        this.nuocdithang = nuocdithang;
        this.nuocdithua = nuocdithua;
        this.diem = diem;
    }
    private int sotranthang;
    private int sotranthua;
    private int sotranhoa;
    private int nuocdithang;
    private int nuocdithua;
    private float diem;
    public Player() {
    }

   
 


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getSotranthang() {
        return sotranthang;
    }

    public void setSotranthang(int sotranthang) {
        this.sotranthang = sotranthang;
    }

    public int getSotranthua() {
        return sotranthua;
    }

    public void setSotranthua(int sotranthua) {
        this.sotranthua = sotranthua;
    }

    public int getSotranhoa() {
        return sotranhoa;
    }

    public void setSotranhoa(int sotranhoa) {
        this.sotranhoa = sotranhoa;
    }

    public int getNuocdithang() {
        return nuocdithang;
    }

    public void setNuocdithang(int nuocdithang) {
        this.nuocdithang = nuocdithang;
    }

    public int getNuocdithua() {
        return nuocdithua;
    }

    public void setNuocdithua(int nuocdithua) {
        this.nuocdithua = nuocdithua;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
    
}
