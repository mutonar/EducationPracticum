/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package HashCodeEquals;

/**
 *
 * @author nazarov
 */
public class HashEquals {
    public static void main(String[] args){
       Car ferrariGTO = new Car("Ferrari 250 GTO", 1963, 70000000);
       Car ferrariSpider = new Car("Ferrari 335 S Spider Scaglietti", 1963, 70000000);

       System.out.println("Эти два объекта равны друг другу?");
       System.out.println(ferrariGTO.equals(ferrariSpider));

       System.out.println("Какие у них хэш-коды?");
       System.out.println(ferrariGTO.hashCode());
       System.out.println(ferrariSpider.hashCode());

    }
}


