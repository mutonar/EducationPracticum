/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Reflection;

/**
 *
 * @author nazarov
 * 
 * два потока одновременно могут все же создать два экземпляра
 * synchronized синхронизирует несколько потоков но по ресурсам очень трудозатратно
 * 
 */
public class Singleton_learn {
    private static  String inFile = "this start \n";
    private static Singleton_learn instance;
    private Singleton_learn(){
        System.out.println("One constraction");
    }
    
    public static synchronized Singleton_learn getInstance(){ // #3 
      if(instance == null){                     //если объект еще не создан
        instance = new Singleton_learn();	//создать новый объект
      }
      return instance;                          // вернуть ранее созданный объект
    }
    
    public void setInFile(String str){
    inFile += str + "\n";
    }
    public String getInFile(){
        return inFile;
    }
}
