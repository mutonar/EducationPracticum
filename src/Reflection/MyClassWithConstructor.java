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
public class MyClassWithConstructor {
   private int number;
   private String name = "default_123";
   private float parametr;
    
   public MyClassWithConstructor(int number, String name) {
        this.number = number;
        this.name = name;
    }
   
    public MyClassWithConstructor(int number, String name, float parametr) {
        this.number = number;
        this.name = name;
        this.parametr = parametr;
    }
   
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