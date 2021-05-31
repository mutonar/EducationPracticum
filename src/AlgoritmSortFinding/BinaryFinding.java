/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AlgoritmSortFinding;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author nazarov
 * 
 * Бинарный поиск , работает только при отсортированном наборе данных
 * 
 * бинарный поиск выполняется за логорифм 2(рост не сравним с обычным поиском)
 */
public class BinaryFinding {
    
    public static void main(String[] args)
    {
        int[] arrB = new int[10];
        for (int i = 0; i < arrB.length; i++) {
            arrB[i] = ThreadLocalRandom.current().nextInt(0, 100); // просто заполение его по порядку
        }
        Arrays.sort(arrB); // Сортируем
        
        for (int i : arrB) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println(searchBinary(arrB, arrB[0]));
        System.out.println(searchBinary(arrB, arrB[arrB.length - 1]));
        System.out.println(searchBinary(arrB, 30));
        System.out.println(searchBinary(arrB, 8));
    }
    
    public static int searchBinary(int[] arrB,  int item )
    {
     int low = 0;
        int high = arrB.length - 1;
        while(low <= high)
        {
            int mid = low + high; // среднее эЛЕМЕНТ
            int guess = arrB[mid]; // текущее значение среднего элемента
            if (guess == item) {
                return mid;
            } if(guess > item)
                high = mid - 1;
            else low = mid + 1;
        }
        return 0;
    }
}
