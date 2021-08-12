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

public class Evolve {
    
    private int Evolve;
    private int poke;
    private int EvolveAt;
    private int evolvesTo;
    

    public Evolve() {
    }

    public Evolve(int Evolve, int poke, int EvolveAt, int evolvesTo) {
        this.Evolve = Evolve;
        this.poke = poke;
        this.EvolveAt = EvolveAt;
        this.evolvesTo = evolvesTo;
        
    }

    public int getEvolve() {
        return Evolve;
    }

    public void setEvolve(int Evolve) {
        this.Evolve = Evolve;
    }

    public int getPoke() {
        return poke;
    }

    public void setPoke(int poke) {
        this.poke = poke;
    }

    public int getEvolveAt() {
        return EvolveAt;
    }

    public void setEvolveAt(int EvolveAt) {
        this.EvolveAt = EvolveAt;
    }

    public int getEvolvesTo() {
        return evolvesTo;
    }

    public void setEvolvesTo(int evolvesTo) {
        this.evolvesTo = evolvesTo;
    }

   
    
    
}
