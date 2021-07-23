/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package String;

/**
 *
 * @author nazarov
 */
public class Run {

    public static void main(String[] args) {
        StringInLeakMemory runTestMemory = new StringInLeakMemory();
        runTestMemory.callTestMemory(10);
        runTestMemory = null;
    }
}
