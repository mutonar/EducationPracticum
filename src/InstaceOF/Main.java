/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InstaceOF;

/**
 *
 * @author nazarov
 * 
 * Работа с оператором instanceof -- сравнение объектов
 * 
 */
public class Main {
    public static void main(String[] args) {

       Integer x = new Integer(22);
       int x1 = 15;

       System.out.println(x instanceof Integer);
//       System.out.println(x instanceof String);// ошибка!
       
       // тут все понятно
       System.out.println("instanceof Cat");
       Cat cat = new Cat();
       System.out.println(cat instanceof Animal);
       System.out.println(cat instanceof MaineCoon);
       
       // тут не очень( сравнивает не по ссылке на какой то объект а на основе чего создан)
       Cat cat2 = new MaineCoon();
       System.out.println(cat2 instanceof Cat);
       System.out.println(cat2 instanceof MaineCoon);

   }
}


class Animal {

}

class Cat extends Animal {

}

class MaineCoon extends Cat {

}