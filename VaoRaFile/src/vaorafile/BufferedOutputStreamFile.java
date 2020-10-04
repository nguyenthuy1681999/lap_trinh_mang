package vaorafile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class BufferedOutputStreamFile {
    public static void main(String args[]) throws IOException {
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
 
        try {
            fout = new FileOutputStream("E://laptrinhmang//VaoRaFile//src//vaorafile//output.txt");
            bout = new BufferedOutputStream(fout);
            String s = "Nguyen Thu Thuy";
            byte b[] = s.getBytes();
            bout.write(b);
            bout.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            bout.close();
            fout.close();
        }
        System.out.println("success!");
    }
}