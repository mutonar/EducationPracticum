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
 */
public class Human implements ToEnter{

    int jeludok = 10;
    

    @Override
    public void setContent(int val) {
        jeludok -= val;
    }
    
    public void getCurrentJeludok(){
        System.out.println(jeludok);
    }
}
