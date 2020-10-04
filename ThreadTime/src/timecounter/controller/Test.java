/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timecounter.controller;

import timecounter.model.HourThread;
import timecounter.model.MinuteThread;
import timecounter.model.SecondThread;
import timecounter.view.TimeFrame;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class Test {
    public static void main(String[] args) {
        HourThread ht = new HourThread();
        MinuteThread mt = new MinuteThread();
        SecondThread st = new SecondThread();
        TimeFrame tf = new TimeFrame();
        tf.setVisible(true);
        TimeCounterController tcc = new TimeCounterController(ht, mt, st, tf);
        tcc.start();
        
    }
}
