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

public class Ability {
    private int idAbility;
    private String name;
    private int idpokemon;

    public Ability() {
    }

    public Ability(int idAbility, String name, int idpokemon) {
        this.idAbility = idAbility;
        this.name = name;
        this.idpokemon = idpokemon;
    }

    
    public int getIdAbility() {
        return idAbility;
    }

    public void setIdAbility(int idAbility) {
        this.idAbility = idAbility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdpokemon() {
        return idpokemon;
    }

    public void setIdpokemon(int idpokemon) {
        this.idpokemon = idpokemon;
    }
    
    
}
