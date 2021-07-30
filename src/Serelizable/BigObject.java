/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Serelizable;

import java.io.Serializable;

 public class BigObject implements Serializable {

        private int id;
        public void setId(final int id){
            this.id = id;
        }

        public int getId() {
           return id;
        }
    }