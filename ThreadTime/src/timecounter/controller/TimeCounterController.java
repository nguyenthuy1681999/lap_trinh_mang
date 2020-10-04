/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timecounter.controller;

import sun.applet.AppletViewer;
import timecounter.model.HourThread;
import timecounter.model.MinuteThread;
import timecounter.model.SecondThread;
import timecounter.view.TimeFrame;



/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class TimeCounterController extends Thread{
    private HourThread hourThread;
    private MinuteThread minuteThread;
    private SecondThread secondThread;
    private TimeFrame timeFrame;

    public TimeCounterController(HourThread hourThread, MinuteThread minuteThread, SecondThread secondThread, TimeFrame timeFrame) {
        this.hourThread = hourThread;
        this.minuteThread = minuteThread;
        this.secondThread = secondThread;
        this.timeFrame = timeFrame;
    }
    public void IncreaseHour(){
        hourThread.setCounthour(hourThread.getCounthour()+1);
        timeFrame.setHour(hourThread.getCounthour());
    }
    public void IncreaseMinute(){
        minuteThread.setCountminute(minuteThread.getCountminute()+1);
        timeFrame.setMinute(minuteThread.getCountminute());
    }
    public void run(){
        while(true){
                try{
                    this.sleep(200);                  
                    secondThread.setCountsecond(secondThread.getCountsecond()+1);
                    timeFrame.setSecond(secondThread.getCountsecond());
                    if (secondThread.getCountsecond()==60) {
                    secondThread.setCountsecond(0);
                    timeFrame.setSecond(0);
                    IncreaseMinute();     
                    }
                    if (minuteThread.getCountminute()==60) {
                    minuteThread.setCountminute(0);
                    IncreaseHour();
                    timeFrame.setMinute(0);
                    }
                }    
         catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        }
    }
    
}
