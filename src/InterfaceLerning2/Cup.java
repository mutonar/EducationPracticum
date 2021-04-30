/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceLerning2;

/**
 *
 * @author nazarov
 */
public class Cup implements FromOut{ // 

    int container =10; // общая емкость чашки
    int currentContent = 3; 
    
    public void fillCup(){
        currentContent = container;
    }
    
    @Override
    public int runFromOut() { // Вызываем внутренний метод
        return getContent();
    }
    
    int getContent() {
        int valtContent = currentContent;
        currentContent = 0;
        return valtContent;
    }
    
    public void getCurrentContent(){
        System.out.println(currentContent);
    }
    
}
