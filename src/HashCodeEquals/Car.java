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
public class Car {
   private String model;
   private int manufactureYear;
   private int dollarPrice;

   public Car(String model, int manufactureYear, int dollarPrice) {
       this.model = model;
       this.manufactureYear = manufactureYear;
       this.dollarPrice = dollarPrice;
   }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true; // мы ли это с самим с собой
        }
        if (o == null || getClass() != o.getClass()) {
            return false; // обязательная проверка что классы разные так как параметры могут быть одинаковы
        }
        // ПРоверка по всем полям.
        Car car = (Car) o;
        if (manufactureYear != car.manufactureYear) {
            return false;
        }
        if (dollarPrice != car.dollarPrice) {
            return false;
        }
        return model.equals(car.model);
//           return dollarPrice == car.dollarPrice;


    }

    @Override
    public int hashCode() {
        /* Количество полей должно быть одинаково как и equals
         у двух одинаковых объектов должен быть одинаковый hash
        */
        
        int result = model == null ? 0 : model.hashCode();
        result = 31 * result + manufactureYear; // Промежуточное умножаем что бы было меньше число коллизий
        result = 31 * result + dollarPrice;
        return result;
    }
    
}
