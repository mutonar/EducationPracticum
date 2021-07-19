/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package String;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ad
 */
public class StringInLeakMemory {

    public static void main(String[] args) {
        FileInputStream inputStream = null;
        try {
            String confName = "C:\\Users\\ad\\Documents\\NetBeansProjects\\JavaApplication5\\test_graph1.txt";
            ArrayList<String[]> allRows = new ArrayList<>();
            List<List<char[]>> listListChar = new ArrayList<>();
            allRows.clear();
            inputStream = new FileInputStream(confName);
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitStr = {line};
                splitStr = line.split("\\t");
                if (splitStr.length > 1) {
                } 
                if (splitStr != null) {

                    allRows.add(splitStr);
//                    List<char[]> listCharFromString = new ArrayList<>();
//                    for (int i = 0; i < splitStr.length; i++) {
//                        String str = splitStr[i];
//                        char[] strChar = str.toCharArray();
//                        listCharFromString.add(strChar); 
//                    }
//                    listListChar.add(listCharFromString);
                }
            }
            int sumInt = 0;
            //Character c1 = new Character('\n');
            inputStream = new FileInputStream(confName);
            scanner = new Scanner(inputStream);
            while (scanner.hasNext()) {
                char c = scanner.next().charAt(0);
                System.out.print(c);
                if(c == '\n')  {
                    System.out.println();
                }
                ++sumInt;
            }
            System.out.println("sumInt: " + sumInt);
            scanner.close();
            inputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StringInLeakMemory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StringInLeakMemory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
