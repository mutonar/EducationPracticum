/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceLerning;

/**
 *
 * @author nazarov
 * 
 * Изучаю как интерфейсы взаимодействуют и так же с патерном адаптер
 */
public class Main {
    public BaseInterface baseInt; // можем поместить сюда любую ракету
   
    public static void main(String[] args){
        Main start = new Main();
        
        Rocket1 baseInter1 = new Rocket1(); 
        BaseInterface interRocket = (BaseInterface) baseInter1;//upCast называется приведенние класса к Интерфейсу
        BaseInterface2 interRocket1 = (BaseInterface2) baseInter1;//upCast называется приведенние класса к Интерфейсу
        interRocket.doSomfing();
        interRocket1.doSomfing(); // отрабатывает
        
        start.baseInt = new Rocket1();
        start.baseInt = new RocketSmall();
        start.baseInt = new RocketBig();
        // interRocket.disconnectStage(); // На прямую не вызвать. так как при апкасте скрыт
    }
    
    
}
