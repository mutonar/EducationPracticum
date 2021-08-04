/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmSortFinding;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 *
 * @author nazarov
 *
 * Используется стратегия "Разделяй и властвуй" Алгоритм Евклида ( нахождение
 * общего делителя) 85 стр
 *
 */
public class SortingQuick {

    public static void main(String[] args) {
        int a = 50;
        int b = 136;
        
        int[] toSort = new int[rnd(5, 10)];
        for (int i = 0; i < toSort.length; i++) {
            toSort[i] = rnd(-10, 100);
        }
        
        for(int element: sortQuick(new int[]{3, 2, 5, -5})){
            System.out.print(element);
        }
    }

    private static void nodMinus(int a, int b) {
        // метод вычисления НОД вычитанием
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
            System.out.println(a + " " + b);
        }
    }

    private static void nodDivision(int a, int b) {
        // метод вычисления НОД делением
        while (a != 0 & b != 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
            System.out.println(a + " " + b);
        }
    }

    private static void nodRecurs(int a, int b) {
        // метогд вычисления НОД вычитанием
        if (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
            nodRecurs(a, b);
            System.out.println(a + " " + b);
        }
    }

    // --- Алгоритм Евклида ---
    private static void EvklidAlgoritm(int a, int b) {
        System.out.println("nodMinus");
        nodMinus(a, b);
        System.out.println("nodDivision");
        nodDivision(a, b);
        System.out.println("nodRecurs");
        nodRecurs(a, b);

    }

    // --- Метод быстрой сортировки ---
    private static int[] sortQuick(int[] arr) {
        /* нужно выбрать опорный элемент(тут будет 1 значение в массиве)
         бъем на два массива больше опорного и меньше
         */
        if(arr.length < 2){
            return arr;
        }else{
            boolean checkSort = true;
            
            int supportElement = 0;
            int pivot = arr[0]; // опорный элемент берем 0
            int[] less = new int[0];
            int[] greater = new int[0];
            
//            for (int i = 1; i < arr.length; i++) { 
//                int currentElement = arr[i];
//                if (currentElement <= pivot) {
//                   less = addElementToMass(less, currentElement);
//                }
//            }
//            for (int i = 1; i < arr.length; i++) {
//                int currentElement = arr[i];
//                if (currentElement > pivot) {
//                   greater = addElementToMass(greater, currentElement);
//                }            
//            }

             for (int i = 0; i < arr.length; i++) {
                if (supportElement == i) {
                    continue; // логично пропуск самого элемента
                }
                int currentElement = arr[i];
                if (currentElement > pivot) {
                    greater = addElementToMass(greater, currentElement);
                }
                if (currentElement <= pivot) {
                    less = addElementToMass(less, currentElement);
                }
            }
            // соединяем массивы
            less = addElementToMass(less, pivot);
            int[] currentSortMass = contecateMass(less, greater);
            Integer tmpElement = null;
            for(int element: currentSortMass){
                System.out.print(element + " " );
                if(tmpElement != null){ // проверка на сортировку
                    if(element <  tmpElement){
                        checkSort = false;
                        break;
                    }
                    tmpElement = element;
                }else{
                    tmpElement = element;
                }
            }
            System.out.println();
            if(checkSort) return currentSortMass; // нечего сортировать
            
            return  sortQuick(currentSortMass);// реккурсивно
        }
    }
    
    private static int[] addElementToMass(int[] arr, int element){
        int[] arrTmp = new int[arr.length + 1];
        for (int j = 0; j < arr.length; j++) {
            arrTmp[j] = arr[j];
        }
        arrTmp[arrTmp.length - 1] = element;
        return arrTmp;
    }
    
     private static int[] contecateMass(int[] arr1, int[] arr2){
        int[] arrTmp = new int[arr1.length + arr2.length];
        int idTmpArr = 0;
        for (int i = idTmpArr; i < arr1.length; i++) {
            arrTmp[idTmpArr] = arr1[i];
            ++idTmpArr;
        }
        for (int i = 0; i < arr2.length; i++) {
            arrTmp[idTmpArr] = arr2[i];
            ++idTmpArr;
        }
        return arrTmp;
    }
     
     static public  int rnd(int  min,int max)
    {
        Random rand = new Random();
        int diff = max - min;
        Random random = new Random();
        int rnd = random.nextInt(diff + 1);
        rnd += min;
        return rnd;
    }
}
