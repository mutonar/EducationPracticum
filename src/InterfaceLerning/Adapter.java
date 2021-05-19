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
// ---  вторая реализация адаптера более просто и тупо ---
public class Adapter implements EngeneRocket{
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