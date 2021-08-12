/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.ec.dao;

import com.pokedex.ec.entity.Ability;
import com.pokedex.ec.entity.Types;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JList;

/**
 *
 * @author 
 */

public class TypeAbilityDAO {

    private Methods mdao = new Methods();
    private String message = "";

    
    public String listAbilities() {

        String name = "SELECT NAME FROM ABILITY ";

        return name;
    }

    public String listAbilitiesNeg( int id) {

        String name = "SELECT DISTINCT NAME FROM ABILITY WHERE NAME NOT IN (SELECT NAME FROM ABILITY WHERE IDPOKEMON = '" + id + "')";
        
        return name;                  
        
    }

    public void listTypes(Connection conn,JList list) {

        String name = "SELECT NAME FROM TYPE WHERE NAME NOT IN (SELECT TYPE FROM POKEMON) AND NAME NOT IN (SELECT TYPE2 FROM POKEMON WHERE TYPE2 IS NOT NULL) ";

        mdao.list(conn, name);
    }

    public String addAbility(Connection conn, String object) {
        String d = "ABILITY";
        message = mdao.addObject(conn, d, object);
        return message;

    }

    public String addAbilityP(Connection con, Ability pok) {       
        PreparedStatement pst = null;
        ResultSet rs;

        String sql = "INSERT INTO ABILITY (NAME, IDPOKEMON) VALUES( ?, ?) ";
        String searchHave = "SELECT IDPOKEMON FROM ABILITY WHERE IDPOKEMON=(?) ";
        String search = "SELECT DISTINCT IDPOKEMON FROM ABILITY WHERE ((SELECT COUNT(IDPOKEMON) FROM ABILITY WHERE IDPOKEMON = (?)) <= 3 ) AND IDPOKEMON=(?) ";
        try {
            //i am looking for the ability and i make sure that the pokemon doesnt have 4 or more
            pst = con.prepareStatement(search);
            pst.setInt(1, pok.getIdpokemon());
            pst.setInt(2, pok.getIdpokemon());
            rs = pst.executeQuery();
           
            if (rs.next()) {
                //If run, i can add it
                pst = con.prepareStatement(sql);
                pst.setString(1, pok.getName());
                pst.setInt(2, pok.getIdpokemon());                               
                pst.execute();
                pst.close();
                con.commit();
                message = "Insert ";

            } else {
                //i look if the pokemon has  4 ability
                pst = con.prepareStatement(searchHave);
                pst.setInt(1, pok.getIdpokemon());
                rs = pst.executeQuery();                
                if (rs.next()) {                   
                    message = "The pokemon already has 4 skills (MAX 4!!)";
                    return message;

                } else {       
                    //If else, i can add it
                    pst = con.prepareStatement(sql);
                    pst.setString(1, pok.getName());
                    pst.setInt(2, pok.getIdpokemon());                                       
                    pst.execute();
                    pst.close();
                    con.commit();
                    message = "Insert ";

                }

            }

        } catch (SQLException e) {
            message = "ERROR SAVE \n " + e.getMessage();
        }
        
        return message;
    }

    public void deleteAbilityP(Connection conn, Ability pok) {

        int d= pok.getIdpokemon();
        String e= String.valueOf(d);
        String h = pok.getName();
        
        String sql = "DELETE FROM ABILITY WHERE IDPOKEMON = " + e + " AND NAME ='" + h + "' ";

        mdao.deleteObject(conn, sql );

    }

    public void deleteAbilityFull(Connection conn, Ability pok ) {
        
        String d= "ABILITY";
        String h = pok.getName();
        String sql = "DELETE FROM " + d + " WHERE NAME = '" + h + "' ";
        mdao.deleteObject(conn, sql );

        
    }
    
    
      public void deleteTypefull(Connection conn, Types pok ) {

        String d= "TYPE";
        String h = pok.getName();
        String sql = "DELETE FROM " + d + " WHERE NAME = '" + h + "' ";
        mdao.deleteObject(conn, sql );

        
    }
    

    public String addTypes(Connection conn, String object) {
        String d = "TYPE";
        message = mdao.addObject(conn, d, object);
        return message;
        
    }

}
