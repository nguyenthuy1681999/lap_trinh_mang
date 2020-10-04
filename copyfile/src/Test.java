
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class Test {
    public static void main(String[] args) {  
        String fileroot = "E:\\laptrinhmang\\copyfile\\src\\copyfile\\test1\\";
        String filenamecopy = "E:\\laptrinhmang\\copyfile\\src\\copyfile\\test2\\test1\\";
        File folderroot = new File("E:\\laptrinhmang\\copyfile\\src\\copyfile\\test1\\.");
        String namefolderroot = folderroot.getParentFile().getName();    
        File foldercopy = new File("E:\\laptrinhmang\\copyfile\\src\\copyfile\\test2\\"+namefolderroot+"\\");
        boolean created = foldercopy.mkdir();
        String[] flcopy = folderroot.list();
        for (String file : flcopy) {
            Copyfile copyfile = new Copyfile(fileroot+file, filenamecopy+file);
            copyfile.start();
        }
    }
}
