/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AlgoritmSortFinding;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author nazarov
 * 
 * Сортировка выборкой(просто знать как работает, эффективней быстрая сортировка)
 * \
 */
public class SortingCased {
    
    public static void main(String[] args){
        int[] test = {98761,8982,3,5,778,890,2234,7890,457,345,1823};
        ArrayList<Integer> list = new ArrayList<Integer>() {{
            //add(234);
            add(1);
            add(1);
            add(2);
            add(2);
//            add(123123);
//            add(96);
//            add(967989934);
//            add(89234);
        }};
        SortingCased sort = new SortingCased();
        sort.sortList(list);

    
    }
    
    // Фукция наибольшего в списке
    private int[] findMaxVal(ArrayList<Integer> list ){
        int[] findElement = new int[2];
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            int valL = list.get(i);
            if (valL > max) {
                max = valL;
                findElement[0] = max;
                findElement[1] = i;
            }
        }
        return findElement;
    }
    
    // сортировка
    private void sortList(ArrayList<Integer> list ){
        ArrayList<Integer> listSort = new ArrayList<Integer>(){};
        for (int i = 0; i < list.size(); i++) {
            int[] maxEl = findMaxVal(list);
            listSort.add(maxEl[0]);
            list.remove(maxEl[1]);
            --i;
        }
        
        for (int i = 0; i < listSort.size(); i++) {
            System.out.println(listSort.get(i));
        }
    
    }
    
}
