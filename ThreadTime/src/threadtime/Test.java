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
public class Test {
    public static void main(String[] args) {
        TimeFrame tf = new TimeFrame();
        tf.setVisible(true);
        HourThread htd = new HourThread(tf);
        MinuteThread mtd = new MinuteThread(tf,htd);
        SecondThread std = new SecondThread(tf,mtd);
        htd.start();
        mtd.start();
        std.start();
    }
}
