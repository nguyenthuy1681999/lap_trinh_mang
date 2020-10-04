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
public class HourThread extends Thread{
    private TimeFrame tf;
    private int count;
    
    public HourThread(TimeFrame tf){
        super();
        this.tf = tf;
        count = 0;
    }
    public void increase(){
        count++;
    }
    public void run(){
      while(true){
          try {
              tf.setHour(count);
          } catch (Exception e) {
              System.out.println(e.getStackTrace());
          }
      }
    }
}
