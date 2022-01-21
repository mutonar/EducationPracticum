/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BifuranialnaiaFunction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date; // в миллесекундаъ хранит с 70 года
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author nazarov
 */
public class FunctionToData {
   public static void main(String[] args) {

       FunctionToData functionToData = new FunctionToData();
       functionToData.addBifuranialnDataToFile(500);
       //MainForm mainForm = new MainForm(); // как импортировать то?
   }
   
   public void addBifuranialnDataToFile(long iter){
       long sec = 1000;
       String nameFile = "dataGraphick.txt";
       delFile(nameFile);
       addStrFileWrite(nameFile, "date" + "\t" + "column-x" + "\t" + "Addetion");
               
       // начальные данные
       Date date = new Date(0);
       DateFormat formatterToTableEvents = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss.SSS"); 
       
       double r = 3.83f; // прирост каждый период
       double x = 0.26f; // количество особей(так для наглядности все до максимум 1)
       double fizLimiter = 1.0f; // количество физически возможного
       
       //проверка(хрень какая то с вычислениями)
            r =  4.0119925f;
            x = 0.4998965894136156f;
            double x1 = r * x;
            double x2 = (fizLimiter - x);
            double x3 = x2 + x; // должна быть 1
            double x4 = x1 * x2;
       
       for (int i = 0; i < iter; i++) {
           date.setTime(i * sec);
           x = functionBifuranial(r, x, fizLimiter);
           addStrFileWrite(nameFile, formatterToTableEvents.format(date.getTime()) + "\t" + x + "\t" + r);
           r += 0.0005;
       }
       
   }
   
   /* функция подсчитывающая и создающеее Бифуракацию
   r - коэффециент роста
   x - количество особей
   fizLimiter - количество физически возможного
   */
   private double functionBifuranial(double r, double x, double fizLimiter){
       System.out.println(r);
       System.out.println(x);
       System.out.println(fizLimiter);
       return r * x * (fizLimiter - x);
       
   }
    
   
     // --- Добавить строку ---
    public static void addStrFileWrite(String fileName, String str) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true)); // true дописывает файл 
            writer.write(str + "\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FunctionToData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(FunctionToData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // --- Удалить файл ---
    private void delFile(String file) {
        File fileD = new File(file);
        if(!fileD.isDirectory()){
            fileD.delete();
        }
    }
}
