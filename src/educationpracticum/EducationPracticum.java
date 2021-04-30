/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationpracticum;

import java.io.IOException;
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
public class EducationPracticum {

    //String str = "";
    //int in = 12876;
    //char c = 'a';
    int startR = 1; // диапазоны рандома
    int endR = 1000;

    public static void main(String[] args) throws IOException {

        EducationPracticum mainStart = new EducationPracticum();
        //BackgroundThread controlThread = mainStart.runProcessTime(); // Запуск процееса 
        //BackgroundThread controlThreadStringBuilder = mainStart.runProcessTimeStringBuffer(); // Запуск процееса 
        //BackgroundThread viwerlThread = mainStart.sumSizeProcess(controlThread); // Запуск процееса 
        mainStart.fillMemory();
        System.out.println("Out off method! ");
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
        char ABC[] = new char[26];
        for (int i = 0; i < 26; i++) {
            ABC[i] = (char) ('a' + i);
        }
        String tmpStr = new String();
        int sum = 0;
        try {
            char[] buffArray;
            for (;;) {

                int lenStr = rnd(startR, endR); // длина рандомной строки
                buffArray = new char[lenStr];
                // StringBuffer sb = new StringBuffer();
                for (int j = 0; j < lenStr; ++j) {
                    char ch = ABC[rnd(0, ABC.length - 1)]; // рандомный символ из алфавита
                    buffArray[j] = ch;
                    //tmpStr.append(ch);
                }
            //tmpStr = String.valueOf(buffArray); //  и так жрет память
                //tmpStr = String.copyValueOf(buffArray); //  и так жрет память это рекомендуют
                //tmpStr = Arrays.toString(buffArray).intern(); //  и так жрет память и intern не помогает
                //tmpStr = sb.toString();
                //tmpStr = sb;
                //tmpStr.setLength(0); // так очистить StringBuffer
                System.out.println("Size string " + getObjectSize(tmpStr) + " byte");
                ++sum;
            // для остановки и тестов сколько в диспетчеры жрет

                
                if (sum == 100) {
                    System.in.read();
                }
                if (sum == 1000) {
                    System.in.read();
                }
                if (sum == 5000) {
                    System.in.read();
                }
                if (sum == 20000) {
                    System.in.read();
                    break;
                }

            }

            System.out.println("Out off cycle for! ");
            System.in.read(); // котрольно смотрим после выхода все равно жрет

        } catch (IOException ex) {
            Logger.getLogger(EducationPracticum.class.getName()).log(Level.SEVERE, null, ex);
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
