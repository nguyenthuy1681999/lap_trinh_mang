/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadtime;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class MinuteThread extends Thread{
    private TimeFrame tf;
    private HourThread htd;
    private int count;
    public MinuteThread(TimeFrame tf, HourThread htd){
        super();
        this.tf = tf;
        this.htd = htd;
        count = 0;
    }
    public void increase(){
        count++;
        if(count == 60){       
        count = 0;
        htd.increase(); 
        }
    }
    public void run(){
            while(true){
            try{
            tf.setMinute(count);
            }catch(Exception e){
            System.out.println(e.getStackTrace());
            }
        }
    }
}
