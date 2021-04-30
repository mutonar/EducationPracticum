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
public class StrCut {

    public static void main(String[] args) {
        String myName = "dom,ano,kz";
        int indeChar = myName.lastIndexOf(',');
        String newName = myName.substring(0, indeChar) + '.' + myName.substring(indeChar+1); // заменить последнюю запятую
        String newName2 = myName.substring(0, indeChar) + myName.substring(indeChar+1); // удалить последнюю запятую
        System.out.println("Orig: " + myName + "\nCut : " + newName + "\nCut2 : " + newName2);
    }
}
