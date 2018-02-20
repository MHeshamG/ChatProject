/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlchathisory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DR Gamal
 */
public class FileOperations {
         
    
        
    public void copy(InputStream sourse, String path) {
        System.out.println(path);

        Thread th = new Thread(() -> {

            FileOutputStream os = null;
            try {
                File newFile = new File(path);
                os = new FileOutputStream(newFile);
                int readByte ; 
                while((readByte=sourse.read())!= -1){
                    os.write(readByte);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SaveChat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SaveChat.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(SaveChat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             

              

            
            
        }
        );
        
        th.start();

    }
}
