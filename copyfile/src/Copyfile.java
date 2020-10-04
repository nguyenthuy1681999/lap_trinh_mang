import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class Copyfile extends Thread{
    public String filename1;
    public String filename2;

    public Copyfile(String filename1, String filename2) {
        this.filename1 = filename1;
        this.filename2 = filename2;
    }
   
    public void run(){
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = new FileInputStream(new File(filename1));
            outStream = new FileOutputStream(new File(filename2));
            int length;
            byte[] buffer = new byte[1024];

            // copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
           System.out.println("File is copied successful!");
            } catch (IOException e) {
               e.printStackTrace();
            } finally {
            try {
                inStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Copyfile.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        try {
            outStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Copyfile.class.getName()).log(Level.SEVERE, null, ex);
        }
           }
     }
    


