/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaorafile;

import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Nguyen Thu Thuy 1608
i */
public class BufferedInputStreamFlie {
     public static void main(String args[]) throws IOException {
        FileInputStream fin = null;
        java.io.BufferedInputStream bin = null;
 
        try {
            fin = new FileInputStream("C:\\Users\\Nguyen Thu Thuy 1608\\OneDrive\\Pictures\\a.jpg");
            bin = new java.io.BufferedInputStream(fin);
            int i;
            while ((i = bin.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            bin.close();
            fin.close();
        }
    }  
}
