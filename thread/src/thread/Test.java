/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;
import thread.LogWriter;
/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class Test {
        public static void main(String[] args) {
            LogWriter lw1 = new LogWriter("thread1", "E://laptrinhmang//thread//src//thread//log.txt", 3000);
            LogWriter lw2 = new LogWriter("thread2", "E://laptrinhmang//thread//src//thread//log.txt", 4000);
            lw1.start();
            lw2.start();
        }
}
