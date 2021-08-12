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

public class Pokemon {
    
    private int idpokemon;
    private String name;
    private String type;
    private String type2;
    private int level;
    private int evolution;
    private String user;

    public Pokemon(String name, String type, String type2, int level, int evolution, String user) {
        this.name = name;
        this.type = type;
        this.type2 = type2;
        this.level = level;
        this.evolution = evolution;
        this.user = user;
    }

    public Pokemon(int idpokemon, String name, String type, String type2, int level, int evolution, String user) {
        this.idpokemon = idpokemon;
        this.name = name;
        this.type = type;
        this.type2 = type2;
        this.level = level;
        this.evolution = evolution;       
        this.user = user;
    }

    
          
    public int getIdpokemon() {
        return idpokemon;
    }

    public void setIdpokemon(int idpokemon) {
        this.idpokemon = idpokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEvolution() {
        return evolution;
    }

    public void setEvolution(int evolution) {
        this.evolution = evolution;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    
    
    public Pokemon() {
    }

  
    
    
}