/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timecounter.model;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class SecondThread extends Thread{
    private int countsecond =0;

    public SecondThread() {
        super();
    }

    public int getCountsecond() {
        return countsecond;
    }

    public void setCountsecond(int countsecond) {
        this.countsecond = countsecond;
    }
    
    
}
