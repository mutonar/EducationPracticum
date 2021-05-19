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
// --- это патерн адаптер реализация через наследование класса и нужного интерфейса ---
// Всеголишь обертка для интерфейса
public class AdapterPatern extends Rocket1 implements EngeneRocket{

    @Override
    public void upSpeed() {
        this.disconnectStage(); // вот тут уже из реализации интерфейса и выполняем что нужно в классе
    }

    @Override
    public void downSpeed() {
        System.out.println("Speed is up!");
    }
    
}