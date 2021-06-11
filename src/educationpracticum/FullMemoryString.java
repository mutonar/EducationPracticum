/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationpracticum;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize;

/**
 *
 * @author nazarov
 */
// пробуем заполнить оперативную память(вычисляем сколько занимает это место в памяти)
public class FullMemoryString {
    int startR = 1; // диапазоны рандома
    int endR = 1000;

    public static void main(String[] args) throws IOException {

        FullMemoryString mainStart = new FullMemoryString();
        //BackgroundThread controlThread = mainStart.runProcessTime(); // Запуск процееса 
        //BackgroundThread controlThreadStringBuilder = mainStart.runProcessTimeStringBuffer(); // Запуск процееса 
        //BackgroundThread viwerlThread = mainStart.sumSizeProcess(controlThread); // Запуск процееса 
        mainStart.fillMemory();
        System.out.println("End fillMemory.");
        System.in.read(); // котрольно смотрим после выхода
        
    }

    
    // --- возращает рандомный int ---
    static public int rnd(int min, int max) {
        Random rand = new Random();
        //  int rnd = (int) (Math.random() * max); // фуфловый рандом
        //int rnd = ThreadLocalRandom.current().nextInt(min, max); // так тоже норм современно
        int diff = max - min;
        Random random = new Random();
        int rnd = random.nextInt(diff + 1);
        rnd += min;
        return rnd;
    }

    
    // --- заполнение памяти вне потока ---
    void fillMemory() {
        // тупое заполнение
//        char ABC[] = new char[26];
//        for (int i = 0; i < ABC.length; i++) {
//            ABC[i] = (char) ('a' + i);
//        }
        // Алфавит Английский
        char ABC[] = { 
            'a', 'b','c','d','e','f','g',
            'h','i','j','k','l','m','n',
            'o','p','q','r','s','t','u',
            'v','w','x','y','z'
        };
        
        int sum = 0;
        ArrayList<String> listStr = new ArrayList<>();

        try {
            char[] buffArray;
            
            for (;;) {
                //StringBuilder tmpStr = new StringBuilder();
                String tmpStr = new String();
                int lenStr = rnd(startR, endR); // длина рандомной строки
                buffArray = new char[lenStr];
                // StringBuffer sb = new StringBuffer();
                for (int j = 0; j < lenStr; ++j) {
                    char ch = ABC[rnd(0, ABC.length - 1)]; // рандомный символ из алфавита
                    buffArray[j] = ch;
                    //tmpStr.append(ch);
                }
                tmpStr = String.valueOf(buffArray); //  и так жрет память(что я сделал что память больше не жрет?)
                //tmpStr = String.copyValueOf(buffArray); //  и так жрет память это рекомендуют
                //tmpStr = Arrays.toString(buffArray).intern(); //  и так жрет память и intern не помогает
                //tmpStr = sb.toString();
                //tmpStr = sb;
                //tmpStr.setLength(0); // так очистить StringBuffer
                
//                System.out.print("Size buffArray " + getObjectSize(buffArray) + " byte" + " | STR --> " ); // Так массив смотрим
//                for (int i = 0; i < buffArray.length; i++) {
//                    System.out.print(buffArray[i]);    
//                }
                
                //System.out.print("Size String " + getObjectSize(tmpStr) + " byte" + " | STR --> " ); // Так строки смотрим
                listStr.add(tmpStr);
                //System.out.print(tmpStr);    
                //System.out.println();
                
                ++sum;
            // для остановки и тестов сколько в диспетчеры жрет

                
                if (sum == 100) {
                    System.out.println("cycle  100 out!");
                    System.out.print("Size ArrayString " + getObjectSize(listStr) + " byte"); // Так массив смотрим
                    //listStr.clear();
                    //System.in.read();
                }
                if (sum == 1000) {
                    System.out.println("cycle  1000 out! ");
                    System.out.print("Size ArrayString " + getObjectSize(listStr) + " byte"); // Так массив смотрим
                    //listStr.clear();
                   // System.in.read();
                }
                if (sum == 10000) {
                    System.out.println("cycle  10000! ");
                    System.out.print("Size ArrayString " + getObjectSize(listStr) + " byte"); // Так массив смотрим
                    //listStr.clear();
                    //System.in.read();
                }
                if (sum == 100000) {
                    System.out.println("cycle  100000 out: press key Enter ! ");
                    System.out.print("Size ArrayString " + getObjectSize(listStr) + " byte"); // Так массив смотрим
                    System.in.read();
                    break;
                }
//                if (sum == 1000000) { // вот тут уже вылет
//                    System.out.println("cycle  1000000 out: press key Enter ! ");
//                    System.out.print("Size ArrayString " + getObjectSize(listStr) + " byte"); // Так массив смотрим
//                    listStr.clear();
//                    System.in.read();
//                    break;
//                }

            }
            System.out.println("Out off cycle for! ");


        } catch (IOException ex) {
            Logger.getLogger(FullMemoryString.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    // -------- запуск  процесса вычисления и забивание памяти -----------
    public BackgroundThread runProcessTime() {

        DoIt di = () -> {    // лямбда 
            String tmpStr = "";
            ArrayList<String> listStr = new ArrayList<>();
            int sizeList = rnd(startR, endR);
            System.out.println("len List " + sizeList);

            for (int i = 0; i < sizeList; ++i) {
                char ch = 'a';
                int lenStr = rnd(startR, endR);
                for (int j = 0; j < lenStr; ++j) {
                    StringBuilder sb = new StringBuilder(tmpStr);
                    sb.insert(tmpStr.length(), ch);
                    tmpStr = sb.toString();
                }
                listStr.add(tmpStr);
                tmpStr = "";
            }
            System.out.println("Size ListStr " + listStr.size());
            System.out.println("Size ListMemory " + getObjectSize(listStr) + " byte");
            int sumStr = 0; // сумма строк
            for (String s : listStr) {
                sumStr += getObjectSize(s);
            }
            System.out.println("Size sum String " + sumStr + " byte");
            listStr.clear();
            System.out.println("Size ListMemory after clear " + getObjectSize(listStr) + " byte");

        };
        BackgroundThread bt = new BackgroundThread("Loader", di);
        bt.start();
        return bt;
    }

    
    // -------- запуск  процесса вычисления и забивание памяти(исп. не потокобезопасный StringBuffer) ----------
    public BackgroundThread runProcessTimeStringBuffer() {

        DoIt di = () -> {    // лямбда 
            StringBuilder tmpStr = new StringBuilder();
            ArrayList<StringBuilder> listStr = new ArrayList<>();
            int sizeList = rnd(startR, endR);
            System.out.println("len List " + sizeList);

            for (int i = 0; i < sizeList; ++i) {
                char ch = 'a';
                int lenStr = rnd(startR, endR);
                for (int j = 0; j < lenStr; ++j) {
                    StringBuilder sb = new StringBuilder(tmpStr);
                    sb.insert(tmpStr.length(), ch);
                    tmpStr = sb;
                }
                listStr.add(tmpStr);
                tmpStr = new StringBuilder();
            }
            System.out.println("Size ListStr " + listStr.size());
            System.out.println("Size ListMemory " + getObjectSize(listStr) + " byte");
            int sumStr = 0; // сумма строк
            for (StringBuilder s : listStr) {
                sumStr += getObjectSize(s);
            }
            System.out.println("Size sum String " + sumStr + " byte");
            listStr.clear();
            System.out.println("Size ListMemory after clear " + getObjectSize(listStr) + " byte");

        };
        BackgroundThread bt = new BackgroundThread("Loader", di);
        bt.start();
        return bt;
    }

    //  --------- вычисление объема процесса который работает фоном -----------
    public BackgroundThread sumSizeProcess(BackgroundThread obj) {
        DoIt di = () -> {
            System.out.println("Size Process Loader " + getObjectSize(obj) + " byte");
        };
        BackgroundThread bt = new BackgroundThread("Sizer", di);
        bt.start();
        return bt;
    }

}
