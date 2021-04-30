/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TrinyCondition;

/**
 *
 * @author nazarov
 * 
 * Изучаю тринарный оператор(просто практика что бы на забыть)
 */
public class NewClass {
    public static void main(String[] args)
    {
        int a =10;
        int b =10;
        int minVal;
        minVal = (a< b) ? a : b;
        System.out.println(minVal);
        
        int absValue = (a<0)? -a: a; // получить абсолютное число 
        System.out.println(absValue);
        
        System.out.println(true ? 1.0f: a );
    }
    
}
