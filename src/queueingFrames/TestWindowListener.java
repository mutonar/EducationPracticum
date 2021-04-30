/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queueingFrames;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import static jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize;

// один слушатель для всех? 
public class TestWindowListener implements WindowListener {

    boolean onCreate = false;
    JFrame frame;
    
    TestWindowListener(JFrame frame) {
        this.frame = frame;
        
    }

    @Override
    // первый запуск окна идет после windowActivated
    public void windowOpened(WindowEvent e) {
        System.out.println("windowOpened " + frame.getTitle());
        //globalData.listF.add(frame);
        onCreate = true;
    }

    @Override
    // это вызывается перед закрытием окна
    public void windowClosing(WindowEvent e) {
        System.out.println("windowClosing " + frame.getTitle());
        for (int i = 0; i < globalData.listF.size(); i++) {
            JFrame f = globalData.listF.get(i);
            if (f == frame) {
                globalData.listF.remove(f);
            }
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("windowClosed " + frame.getTitle());
    }

    @Override
    // при сворачивании
    public void windowIconified(WindowEvent e) {
        System.out.println("windowIconified " + frame.getTitle());
        globalData.windowIconified = true;
    }

    @Override
    // это когда окно развернули обратно из трея
    public void windowDeiconified(WindowEvent e) {
        System.out.println("windowDeiconified " + frame.getTitle());

    }

    @Override
    // когда ткнули на окно или сняли с него фокус(при любых действиях)
    public void windowActivated(WindowEvent e) {

        System.out.println("  windowActivated " + frame.getTitle());

        if (onCreate) { // проверка только после создания окна
            ++globalData.sumFrame;
            if (!globalData.windowIconified) { // проверка на сворачивание

                if (globalData.captureFocus) { // только одно окно сожет захватить управление
                    globalData.captureFocus = false; // сразу блочим для остальныхF
                    for (int i = 0; i < globalData.listF.size(); i++) {
                        JFrame f = globalData.listF.get(i);
                        // все окна на передний план
                        if (f == frame) {
                            System.out.println("I am find myself " + f.getTitle());
                            --globalData.sumFrame; // нужен правильный подсчет
                            continue;
                        }
                        //f.setExtendedState(JFrame.ICONIFIED);
                        f.setExtendedState(JFrame.NORMAL);
                        f.toFront();
                        f.requestFocus();
                    }

                }
            } else {
                globalData.windowIconified = false;
                globalData.sumFrame = 0;
            }

            if (globalData.sumFrame >= globalData.listF.size()) { // проверка раньше времени чем обработка
                globalData.sumFrame = 0;
                globalData.captureFocus = true; // разблокировка

            }
        }
        System.out.println("Size ListMemory " + getObjectSize(globalData.listF) + " byte");
        System.out.println("sum List " + globalData.listF.size());
    }

    @Override
    // когда ушло из фокуса окна(ткнули на другое)
    public void windowDeactivated(WindowEvent e) {
        System.out.println("windowDeactivated " + frame.getTitle());
       // if (!globalData.captureFocus) { // если захватили то триггер отключения не срабатывает

        //  globalData.captureFocus = true; // разблокировка
        //}
    }

}
