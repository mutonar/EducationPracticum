/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package educationpracticum.phantomLink;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class RessurectDemo {
    private A a;

    public static class A {
        private RessurectDemo demo;
        private String data;

        public String getData() {
            return data;
        }

        public A(RessurectDemo demo) {
            this.demo = demo;
            System.out.println("-> Старт заполнения памяти");
            StringBuffer buff = new StringBuffer();
            for (long i = 0; i < 30000000; i++) {
                buff.append('a');
            }
            this.data = buff.toString();
            System.out.println("<-заполнили объект информацией (смотрим в диспетчере задач)");
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("в этом месте мог быть вызов у объекта метода *.finalize()");
        }
    }

    private static class MyPhantomReference<T> extends PhantomReference<T> {
        public MyPhantomReference(T obj, ReferenceQueue<? super T> queue) {
            super(obj, queue);
            Thread thread = new MyPollingThread<T>(queue);
            thread.start();
        }

        public void cleanup() {
            System.out.println("очищаем фантомную ссылку");
            System.out.println("ждем, когда после очередного System.gc() освободятся 400 МЬ, занятые объектом");
            clear();
        }

        public static class MyPollingThread<T> extends Thread {
            private ReferenceQueue<? super T> referenceQueue;

            public MyPollingThread(ReferenceQueue<? super T> referenceQueue) {
                this.referenceQueue = referenceQueue;
            }

            @Override
            public void run() {
                System.out.println("запускаем процесс очистки очереди фантомных ссылок");
                Reference<?> ref = null;
                while ((ref = referenceQueue.poll()) == null) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Thread " + getName() + " has been interrupted");
                    }
                }

                if (ref instanceof MyPhantomReference<?>) {
                    System.out.println("нашли  фант.ссылку в очереди = "+ ref);
                    System.out.println("решаем,что делать с ссылкой (убиваем)");
                    ((MyPhantomReference<?>) ref).cleanup();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        RessurectDemo demo = new RessurectDemo();
        Thread.sleep(20000);
        Reference<A> ref = new MyPhantomReference<RessurectDemo.A>(new A(demo), new ReferenceQueue<RessurectDemo.A>());
        System.out.println("создали фант.ссылку на объект = " + ref);
        System.out.println("при этом объект из фант.ссылки = " + ref.get());
        int count = 0;
        
//        for (int i = 0;i<2;i++){
//            Thread.sleep(1000);
//            System.out.println("вызываем System.gc(): "+ ++count);
//            System.gc();
//        }
        
        while(true){
            System.out.println("нажмите Enter вызвав System.gc(): "+ ++count);
            System.in.read();
            System.gc();
        }
        
        
        //System.out.println("программа закончилась");
    }
}