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
// --- Класс который изменять нельзя ---
public class Rocket1 implements BaseInterface, BaseInterface2{ // Реализуем интерфейс
    int stages = 10;
    String name = "Rocket1";

    @Override
    public String name() {
        return name;
    }

    @Override
    public void doSomfing() { // Два одинаковых Метода
        disconnectStage(); // выполним то что в теле класса не пренадлижащее интерфейсу
    }
    
    @Override
    public void doSomfing2() { // это тот который не совпадает
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // --- Метод не принадлежащий интерфейсам ---
    public void disconnectStage(){
        --stages;
        System.out.println(stages);
    }
  
}