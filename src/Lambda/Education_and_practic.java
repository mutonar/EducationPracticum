/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lambda;

/**
 *
 * @author admin
 * 
 * Изучаю как работает лямбда и функциональный интерфейс
 * 
 */
public class Education_and_practic {

    private static int valueClass1 = 4, valueClass2 =9; // показать работу Лябда с переменными класса
    
    public static void main(String[] args) {
        int valueFunction1 = 4, valueFunction2 =9; // показать работу Лябда с переменными фукции


        Cliet anonimClient = new Cliet(){ // реализация анонимного класса из функционального интерфейса
            @Override
            public double getResult(double i, double j) {
               return i + j;
            }
        };
        System.out.println(anonimClient.getResult(100.4, 200.5));
        
        // не терминальная Лямбда возвращает значение
        Cliet anonimClientLambda = (amount1, amount2) ->  amount1 + amount2; // Тоже самое но в реализации лямбды
//      Cliet anonimClientLambda = (double amount1, double amount2) ->  amount1 + amount2; // можно с типами параметров а смысл?
        System.out.println(anonimClientLambda.getResult(100.4, 200.5));
        
        // реализация терминальной Лямбды
        ClietPrintStr printer = string -> System.out.println(string);
        printer.printStr("Hellow str");
        
        // лямбда в несколько операторов
        Factorial factorial = value -> {
            int result = 1;
            for(int i=1; i <= value; ++i){
                result *=i;
            }
            return result;
        };
        System.out.println(factorial.getResult(10));
        
        // лямбда с переменными Класса
        Operation operClass = () -> {
            valueClass1 = 100;
            return valueClass1 + valueClass2;
                    };
        System.out.println(operClass.getResult());
        System.out.println(valueClass1);
        System.out.println(valueClass2);
        
        // лямбда с переменными фукции
        Operation operFunc = () -> {
//            valueFunction1 = 100; // а тут уже так нельзя
            return valueFunction1 + valueFunction2;
                    };
//        valueFunction1 = 100; // и тут нельзя(можно использовать но не изменять)
    }
    
}


// функциональный интерфейс
interface Cliet{
   double getResult(double i, double j);
}
// функциональный интерфейс(нечего не возращает)
interface ClietPrintStr{
   void printStr(String s);
}

// функциональный интерфейс Факториала
interface Factorial{
   int getResult(int i);
}

// функциональный интерфейс без параметров
interface Operation{
   int getResult();
}