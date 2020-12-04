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
public class RankMoveWin {
    private int id;
    private String ten;
    private int sotranthang;
    private int nuocdithang;
    private float trungbinhnuocdithang;
    private int rank;

    public RankMoveWin(int id, String ten, int sotranthang, int nuocdithang, float trungbinhnuocdithang, int rank) {
        this.id = id;
        this.ten = ten;
        this.sotranthang = sotranthang;
        this.nuocdithang = nuocdithang;
        this.trungbinhnuocdithang = trungbinhnuocdithang;
        this.rank = rank;
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

    public int getSotranthang() {
        return sotranthang;
    }

    public void setSotranthang(int sotranthang) {
        this.sotranthang = sotranthang;
    }

    public int getNuocdithang() {
        return nuocdithang;
    }

    public void setNuocdithang(int nuocdithang) {
        this.nuocdithang = nuocdithang;
    }

    public float getTrungbinhnuocdithang() {
        return trungbinhnuocdithang;
    }

    public void setTrungbinhnuocdithang(float trungbinhnuocdithang) {
        this.trungbinhnuocdithang = trungbinhnuocdithang;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
            
}
