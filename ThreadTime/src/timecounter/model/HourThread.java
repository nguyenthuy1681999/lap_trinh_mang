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
public class HourThread{
    private int counthour = 0;
    public HourThread(){
        super();
   
    }

    public void setCounthour(int counthour) {
        this.counthour = counthour;
    }
    public int getCounthour(){
        return counthour;
    }   
}
