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
        interRocket.doSomfing();
        
        start.baseInt = new Rocket1();
        start.baseInt = new RocketSmall();
        start.baseInt = new RocketBig();
        // interRocket.disconnectStage(); // На прямую не вызвать. так как при апкасте скрыт
    }
    
    
}


// --- Класс который изменять нельзя ---
class Rocket1 implements BaseInterface, BaseInterface2{ // Реализуем интерфейс
    
    int stages = 10;
    String name = "Rocket1";

    @Override
    public String name() {
        return name;
    }

    @Override
    public void doSomfing() {
        disconnectStage(); // выполним то что в теле класса не пренадлижащее интерфейсу
    }
    
    @Override
    public void doSomfing2() { // это тот который не совпадает
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // --- Метод не принадлежащий интерфейсам ---
    public void disconnectStage(){
        --stages;
    }
  
}


class RocketSmall implements BaseInterface{ // Реализуем интерфейс
    
    int stages = 30;
    String name = "RocketSmall";

    @Override
    public String name() {
        return name;
    }

    @Override
    public void doSomfing() {
        disconnectStage(); // выполним то что в теле класса не пренадлижащее интерфейсу
    }
    
      
    // --- Метод не принадлежащий интерфейсам ---
    public void disconnectStage(){
        --stages;
    }
  
}

class RocketBig implements BaseInterface{ // Реализуем интерфейс
    
    int stages = 80;
    String name = "RocketBig";

    @Override
    public String name() {
        return name;
    }

    @Override
    public void doSomfing() {
        disconnectStage(); // выполним то что в теле класса не пренадлижащее интерфейсу
    }
    
    
    // --- Метод не принадлежащий интерфейсам ---
    public void disconnectStage(){
        --stages;
    }
  
}


// --- это патерн адаптер реализация через наследование класса и нужного интерфейса ---
// Всеголишь обертка для интерфейса
class AdapterPatern extends Rocket1 implements EngeneRocket{

    @Override
    public void upSpeed() {
        this.disconnectStage(); // вот тут уже из реализации интерфейса и выполняем что нужно в классе
    }

    @Override
    public void downSpeed() {
        System.out.println("Speed is up!");
    }
    
}

// ---  вторая реализация адаптера более просто и тупо ---
class Adapter implements EngeneRocket{
    Rocket1 r1 = new Rocket1(); // просто всего лишь экземпляр класса используем
    
    @Override
    public void upSpeed() {
        r1.disconnectStage(); // вот тут уже из реализации интерфейса и выполняем что нужно в классе
    }

    @Override
    public void downSpeed() {
        System.out.println("Speed is up!");
    }
    
}
