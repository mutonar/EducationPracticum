/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package education_and_practic;

/**
 *
 * @author admin
 */
public class LinkValueEditInFunction {
    
    public static void main(String[] args){
        int[] testINT = {1,2,3,4,5,};
        LinkValueEditInFunction test = new LinkValueEditInFunction();
        test.editMass(testINT);
        editMass(testINT);
        
        for (int i = 0; i < testINT.length; i++) {
            System.out.println(testINT[i]);
        }
    }
    
    private static void editMass(int[] massINT){
       // massINT = new int[]{6,6,6,6,6}; // так не изменят данные 
        for (int i = 0; i < massINT.length; i++) {
            massINT[i] = massINT[i] + 1;
        }
    }
}
