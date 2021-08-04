/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InOutStream;

/**
 *
 * @author nazarov
 * 
 * чтение и запись данных(методов нужных нет что странно)
 * https://betacode.net/13367/java-bytearrayoutputstream
 * https://betacode.net/13527/java-inputstream
 */ 
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
public class InputStream_transferTo_ex1 {
 
    public static void main(String[] args) throws IOException {
        String s = "123456789-987654321-ABCDE";
        byte[] bytes = s.getBytes();
         
        // ByteArrayInputStream is a subclass of InputStream.
        InputStream reader = new ByteArrayInputStream(bytes);
 
        // Or Windows path: C:/Somepath/out-file.txt
        File file = new File("/Volumes/Data1/test/out-file.txt");
        // Create parent folder.
        file.getParentFile().mkdirs(); // так создать полный путь с корня
 
        OutputStream writer = new FileOutputStream(file);
 
        reader.skip(10); // Skips 10 bytes.
 
        //reader.transferTo(writer); // нет этого метода о чем говорят
 
        reader.close();
        writer.close();
    }
}
