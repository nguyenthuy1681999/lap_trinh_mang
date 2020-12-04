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
public class RankMoveLoss {
    private int id;
    private String ten;
    private int sotranthua;
    private int nuocdithua;
    private float trungbinhnuocdithua;
    private int rank;  

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

    public int getSotranthua() {
        return sotranthua;
    }

    public void setSotranthua(int sotranthua) {
        this.sotranthua = sotranthua;
    }

    public int getNuocdithua() {
        return nuocdithua;
    }

    public void setNuocdithua(int nuocdithua) {
        this.nuocdithua = nuocdithua;
    }

    public float getTrungbinhnuocdithua() {
        return trungbinhnuocdithua;
    }

    public void setTrungbinhnuocdithua(float trungbinhnuocdithua) {
        this.trungbinhnuocdithua = trungbinhnuocdithua;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public RankMoveLoss(int id, String ten, int sotranthua, int nuocdithua, float trungbinhnuocdithua, int rank) {
        this.id = id;
        this.ten = ten;
        this.sotranthua = sotranthua;
        this.nuocdithua = nuocdithua;
        this.trungbinhnuocdithua = trungbinhnuocdithua;
        this.rank = rank;
    }
}
