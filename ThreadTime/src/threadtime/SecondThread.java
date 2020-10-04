/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadtime;

import org.w3c.dom.css.Counter;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class SecondThread extends Thread{
    private TimeFrame tf;
    private MinuteThread mtd;
    private int count; // biến đếm tính giây
    
    //hàm tạo cho SecondThred
    public SecondThread(TimeFrame tf, MinuteThread mtd){
        super();
        this.tf = tf;
        this.mtd = mtd;
        count = 0;
    }
    public void run(){ // cho luồng chạy
        while(true){
                try{
                    this.sleep(10);
                    count++;
                    if (count==60) {
                        count=0;
                        mtd.increase();
                        
                    }
                     tf.setSecond(count);
                }    
         catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        }
    }
}
