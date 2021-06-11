/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package educationpracticum.phantomLink;

/*
 Тестирование фантомных ссылок(опять похоже нечего не работает) какой то пиздеж , память не освобождается
*/

import java.io.IOException;
import java.lang.ref.*;

public class Main {

   public static void main(String[] args) throws InterruptedException, IOException {
       Thread.sleep(10000);

       ReferenceQueue<TestClass> queue = new ReferenceQueue<>();
       Reference ref = new MyPhantomReference<>(new TestClass(), queue);

       System.out.println("ref = " + ref);

       Thread.sleep(5000);

       System.out.println("Вызывается сборка мусора!");

       System.gc();
       Thread.sleep(300);

       System.out.println("ref = " + ref);

       Thread.sleep(5000);

       System.out.println("Вызывается сборка мусора!");

       System.gc();
       
       System.in.read();
   }
}