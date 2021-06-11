/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package educationpracticum.phantomLink;

public class TestClass {
   private StringBuffer data;
   public TestClass() {
       this.data = new StringBuffer();
       for (long i = 0; i < 20000000; i++) {
           this.data.append('x');
       }
   }
   @Override
   protected void finalize() {
       System.out.println("У объекта TestClass вызван метод finalize!!!");
   }
}