/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package educationpracticum.phantomLink;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class MyPhantomReference<TestClass> extends PhantomReference<TestClass> {

   public MyPhantomReference(TestClass obj, ReferenceQueue<TestClass> queue) {

       super(obj, queue);

       Thread thread = new QueueReadingThread<TestClass>(queue);

       thread.start();
   }

   public void cleanup() {
       System.out.println("Очистка фантомной ссылки! Удаление объекта из памяти!");
       clear();
   }
}