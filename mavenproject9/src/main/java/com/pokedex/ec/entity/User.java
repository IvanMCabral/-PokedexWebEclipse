/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.ec.entity;

/**
 *
 * @author 
 */

public class User {
    
    private int id;
    private String name;
   

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    
    public User(int id, String name) {
        this.id = id;
        this.name = name;
        
    }
 
    public User() {
    }
}
