/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Reflection;

/**
 *
 * @author nazarov
 * 
 * Изучаем рефлексию
 * 
 */
public class MyClass {
   private int number;
   private String name = "default_123";
//    public MyClass(int number, String name) {
//        this.number = number;
//        this.name = name;
//    }
   public int getNumber() {
       return number;
   }
   public void setNumber(int number) {
       this.number = number;
   }
   public void setName(String name) {
       this.name = name;
   }
   private void printData(){
       System.out.println(number + name);
   }
}