/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmSortFinding;

/**
 *
 * @author nazarov
 *
 * Используется стратегия "Разделяй и властвуй" Алгоритм Евклида ( нахождение
 * общего делителя)
 *
 */
public class SortingQuick {

    public static void main(String[] args) {
      int a = 50;
      int b = 136;

      System.out.println("nodMinus");
      nodMinus(a, b);
      System.out.println("nodDivision");
      nodDivision(a, b);
      System.out.println("nodRecurs");
      nodRecurs(a, b);
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
            System.out.println(a  + " " + b);
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
      
      // --- Метод быстрой сортировки ---
      private static void sortQuick(int[] arr) {
          /* нужно выбрать опорный элемент(тут будет 1 значение в массиве)
          бъем на два массива больше опорного и меньше
          */
          
          
      }
}
