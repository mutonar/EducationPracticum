/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceLerning;

/**
 *
 * @author nazarov
 */
public class RocketSmall implements BaseInterface{ // Реализуем интерфейс
    
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