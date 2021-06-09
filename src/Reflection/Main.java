/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nazarov
 */
public class Main {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        int number = myClass.getNumber();
        String name = null; //no getter =(
        System.out.println(number + " " + name);//output 0null

        printDataMethod(myClass);

        try {
            Field field = myClass.getClass().getDeclaredField("name"); // этот метод возвращает массив полей класса, private и protected
            // getFields() и getDeclaredFields() не возвращают поля класса-родителя!
            field.setAccessible(true);                      // Метод setAccessible(true) разрешает работу с приватным полем
            name = (String) field.get(myClass);             // получить значение поля
            System.out.println(number + " " + name);
            field.set(myClass, (String) "new value");       // Установить новое значение
            name = (String) field.get(myClass);             // получить значение поля
            System.out.println(number + " " + name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        printDataMethod(myClass);

        // создание класса рефлексией(Конструктор по умолчанию)
        MyClass myClassReflection = createWithTheHelpReflection();
        System.out.println(myClass);//output created object reflection.MyClass@60e53b93

        // создание класса рефлексией(Конструктор по умолчанию)
        MyClassWithConstructor createrClassWithoutDefaultConstructorReflection = createrClassWithoutDefaultConstructor();
        System.out.println(createrClassWithoutDefaultConstructorReflection);//output created object reflection.MyClass@60e53b93
        
        // Создание разных объектов из Singltone
        System.out.println("Two singlton");
        try {
            Class clazz = Class.forName(Singleton_learn.class.getName());
            Constructor declaredConstructor = clazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            Singleton_learn сlassSingelton1 = (Singleton_learn) declaredConstructor.newInstance();
            Singleton_learn сlassSingelton2 = (Singleton_learn) declaredConstructor.newInstance();
            System.out.println(сlassSingelton1);
            System.out.println(сlassSingelton2);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // --- доступ до приватного метода ---
    public static void printDataMethod(Object myClass) {

        try {
            Method method = myClass.getClass().getDeclaredMethod("printData");
            method.setAccessible(true);
            method.invoke(myClass); // вызов скрытого метода
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // --- Создание класса по средствам рефлексии (c констркуктором по умолчанию )---
    public static MyClass createWithTheHelpReflection() {
        MyClass myClass = null;
        try {
            Class clazz = Class.forName(MyClass.class.getName()); // Создаем класс по его имени
            myClass = (MyClass) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return myClass;
    }

    // --- Создание класса по средствам рефлексии (c констркуктором в котором есть параметры )---
    public static MyClassWithConstructor createrClassWithoutDefaultConstructor() {
        MyClassWithConstructor myClass = null;
        try {
            Class clazz = Class.forName(MyClassWithConstructor.class.getName());
            Class[] params = {int.class, String.class};
            myClass = (MyClassWithConstructor) clazz.getConstructor(params).newInstance(1, "default2");

            // просмотр всех конструкторов класса
            Constructor[] constructors = clazz.getConstructors();
            for (Constructor constructor : constructors) {
                Class[] paramTypes = constructor.getParameterTypes();
                for (Class paramType : paramTypes) {
                    System.out.print(paramType.getName() + " ");
                }
                System.out.println();
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return myClass;

    }

}
