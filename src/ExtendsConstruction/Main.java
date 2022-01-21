/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExtendsConstruction;

/**
 *
 * @author nazarov
 * 
 * Конструкторы не наследуются
 * Но вызываются
 */
public class Main {
      public static void main(String[] args) {
        Bmw bmw = new Bmw();
        Bmw bmw2 = new Bmw(1, 2, 3);
    }
}


class Car {
    public Car() {
        System.out.println("Машина_void constructor ");
    }
    
    public Car(int speed) {
        
        System.out.println("Машина_constructor_int" + speed);
    }
}

class Bmw extends Car {    
    public Bmw() {
        //super(123);
        System.out.println("Bmw_constructor ");
    }
    
    public Bmw(int a, int b, int c) {
        super(a);   // какой ни какой конструктор должен быть вызван
    }
    
 }
