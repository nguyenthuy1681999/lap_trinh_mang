/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvctongab;

import java.io.Serializable;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class Tongab implements Serializable{
    private int a;
    private int b;

    public Tongab(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    public int tinhTong(){
        int tong = 0;
        tong = a + b;
        return tong;
    }
}
