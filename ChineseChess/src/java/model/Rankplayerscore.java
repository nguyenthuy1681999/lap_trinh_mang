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
public class Rankplayerscore {
    private int id;
    private String ten;
    private float diem;
    private int rank;

    public Rankplayerscore(int id, String ten, float diem, int rank) {
        this.id = id;
        this.ten = ten;
        this.diem = diem;
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

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
}
