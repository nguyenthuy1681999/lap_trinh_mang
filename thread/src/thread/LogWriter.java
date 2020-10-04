/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Calendar;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class LogWriter extends Thread{
    private String fileName;
    private long time;

    public LogWriter(String name,String fileName, long time) {
        super(name);
        this.fileName = fileName;
        this.time = time;
    }
    public void run(){
        for(int i=0; i<10; i++){
            try {
                Writer wr =  new BufferedWriter(new  FileWriter(fileName,true));
                this.sleep(time);
                wr.append(getName()+"["+Calendar.getInstance().getTime()+"]: Log-"+i+"\r\n");
                wr.close();
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}
