/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceLerning2;

/**
 *
 * @author nazarov
 * 
 * Это интерфейс взаимодействия по типу MVC паттерна
 */
public class Drink {
    FromOut outContain; // Интерфейс ввода
    ToEnter fill;       // интерфейс заполнения
    
    public static void main(String[] args){
        Drink d = new Drink();
        
        Human h = new Human();
        Cup с1 = new Cup();
        
        d.runInterfaceContainer(с1); // заносим чашку в метод интерфейса
        d.runInterfaceEssentiality(h); // заносим человека в метод интерфейса

        int valFromCup = d.outContain.runFromOut(); // выполним метод самого интерфейса
        d.fill.setContent(valFromCup);              // выполним метод самого интерфейса Человека
        с1.getCurrentContent();                     // С мотрим изменилось ли что в объекте чашки
        h.getCurrentJeludok();                      // Что оталось в желудке
        
    }
    
   void runInterfaceContainer(Cup c){
       outContain = (FromOut) c;
    
    }
    
    void runInterfaceEssentiality(ToEnter c){
       fill = (ToEnter) c;
    
    }
}
