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
public class PokemonUser {
    
    private int idpokemon;
    private int iduser;

    public int getIdpokemon() {
        return idpokemon;
    }

    public void setIdpokemon(int idpokemon) {
        this.idpokemon = idpokemon;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public PokemonUser() {
    }

    public PokemonUser(int idpokemon, int iduser) {
        this.idpokemon = idpokemon;
        this.iduser = iduser;
    }
    
    
    
}
