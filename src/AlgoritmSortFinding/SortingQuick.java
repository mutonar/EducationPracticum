/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmSortFinding;

import java.util.Arrays;
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
        
        int[] toSort = new int[]{10, 220, 3, 1, 7};
        for(int element: sortQuick(toSort)){
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
            
            int pivot = arr[0]; // опорный элемент берем 0
            int[] less = new int[0];
            int[] greater = new int[0];
            
            for (int i = 1; i < arr.length; i++) { 
                int currentElement = arr[i];
                if (currentElement <= pivot) {
                   less = addElementToMass(less, currentElement);
                }
            }
            for (int i = 1; i < arr.length; i++) {
                int currentElement = arr[i];
                if (currentElement > pivot) {
                   greater = addElementToMass(greater, currentElement);
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
                    }
                }else{
                    tmpElement = element;
                }
            }
            System.out.println();
            if(checkSort) return arr; // нечего сортировать
            
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
}
