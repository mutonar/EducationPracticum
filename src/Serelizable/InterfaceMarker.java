/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serelizable;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterfaceMarker {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int originalId = 12;
        BigObject objectOriginal = new BigObject();
        objectOriginal.setId(originalId);
        
        ByteArrayOutputStream writeBuffer = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(writeBuffer);
        outputStream.writeObject(objectOriginal);
        outputStream.close();

        // ---  write to file --- 
        OutputStream fos0 = new FileOutputStream("objectOriginal.bin_0"); // в этот не пишет почему?
        OutputStream fos1 = new FileOutputStream("objectOriginal.bin_1"); // в этот не пишет почему?
        FileOutputStream fos2 = new FileOutputStream(new File("objectOriginal.bin_2")); 
        FileOutputStream fos3 = new FileOutputStream("objectOriginal.bin_3"); 
        
        writeBuffer.writeTo(fos0);
        writeBuffer.writeTo(fos1);
        writeBuffer.writeTo(fos2);
        writeBuffer.writeTo(fos3);
        
        fos0.close();
        fos1.close();
        fos2.close();
        fos3.close();        
        
        byte[] buffer = writeBuffer.toByteArray();
        ByteArrayInputStream readBuffer = new ByteArrayInputStream(buffer);
        ObjectInputStream inputStream = new ObjectInputStream(readBuffer);
        
        BigObject objectCopy = (BigObject) inputStream.readObject();
        if (objectCopy.getId() == originalId) {
            System.out.println("originalId equals copiedId");
        }
        
        // сравнение сохранненого из файла
        buffer = readFileToObject("objectOriginal.bin_3");
        readBuffer = new ByteArrayInputStream(buffer);
        inputStream = new ObjectInputStream(readBuffer);
        
        objectCopy = (BigObject) inputStream.readObject();
        if (objectCopy.getId() == originalId) {
            System.out.println("originalId equals copiedId");
        }
    }
    
   // --- прочитать файл в байтах --- 
   private static byte[] readFileToObject(String nameFile){
       InputStream is = null;
       ByteArrayOutputStream buffer = new ByteArrayOutputStream();
       
        try {
            is = new FileInputStream(nameFile);
            
            int nRead;
            byte[] data = new byte[16384];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }    
            is.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InterfaceMarker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceMarker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return buffer.toByteArray();
   }
}
