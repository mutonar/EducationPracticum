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
import java.util.stream.Stream;

/**
 *
 * @author ad
 */
public class StringInLeakMemory {
    int numberFile = 0;
    
    public void callTestMemory(int num){
        for (int i = 0; i < num; i++) {
            numberFile = i;
            testMemoryString();
            System.out.println("Press Key!");
            try {
                System.in.read();
            } catch (IOException ex) {
                Logger.getLogger(StringInLeakMemory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void testMemoryString() {
        FileInputStream inputStream = null;
        try {
            //String confName = "E:\\tmp\\bigFile\\_20210718_190002_User_TREND.csv_Divide\\" + numberFile +".tmp";
            String confName = "E:\\tmp\\bigFile\\_20210718_190002_User_TREND.csv";
            System.out.println("NameFile: " + confName);
            ArrayList<String[]> allRows = new ArrayList<>();
            List<List<char[]>> listListChar = new ArrayList<>();
            allRows.clear();
            inputStream = new FileInputStream(confName);
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            while (scanner.hasNextLine()) {
                //String line = scanner.nextLine();
                String[] splitStr = null;
                splitStr = scanner.nextLine().split("\\t");

                //String[] tmp = new String[splitStr.length];
                //System.arraycopy( splitStr, 0, tmp, 0, splitStr.length );
                //splitStr = null;
                if (splitStr != null) {
                    allRows.add(splitStr);
                    // Сравним все элементы в этом листе и подмассивы тоже
                    for (String[] element : allRows) {
                        for(String s: element){
                            for (String splitStr1 : splitStr) {
                            }
                                
                            }
                            
                    }
//                    List<char[]> listCharFromString = new ArrayList<>();
//                    for (int i = 0; i < splitStr.length; i++) {
//                        String str = splitStr[i];
//                        char[] strChar = str.toCharArray();
//                        listCharFromString.add(strChar); 
//                    }
//                    listListChar.add(listCharFromString);
                }
                System.out.println("sumallRows: " + allRows.size());
            }
//            int sumInt = 0;
//            //Character c1 = new Character('\n');
//            inputStream = new FileInputStream(confName);
//            scanner = new Scanner(inputStream);
//            while (scanner.hasNext()) {
//                char c = scanner.next().charAt(0);
//                System.out.print(c);
//                if(c == '\n')  {
//                    System.out.println();
//                }
//                ++sumInt;
//            }
            System.out.println("sumallRows: " + allRows.size());
            scanner.close();
            inputStream.close();
            allRows.clear();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StringInLeakMemory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StringInLeakMemory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
