/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.ec.dao;

import com.pokedex.ec.entity.Evolve;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class EvolveDAO {

    private String message = "";

    public String addEvoTable(Connection con, Evolve ev) {
        PreparedStatement pst = null;

        String sql = "INSERT INTO EVO ( IDPOKEMON, LEVEL, EVOLVESTO) VALUES(  ?, ?,  ?)";
        String sql2 = "UPDATE POKEMON SET EVOLUTION = 1  WHERE IDPOKEMON = ?";
        try {

            pst = con.prepareStatement(sql);

            pst.setInt(1, ev.getPoke());
            pst.setInt(2, ev.getEvolveAt());
            pst.setInt(3, ev.getEvolvesTo());
            pst.execute();
            pst.close();
            con.commit();

            //update evolution pokemon table
            pst = con.prepareStatement(sql2);
            pst.setInt(1, ev.getPoke());
            pst.execute();
            pst.close();
            con.commit();

            message = "Insert OK";

        } catch (SQLException e) {
            message = "Error \n " + e.getMessage();
        }

        return message;
    }

    
    public int lastPoke(Connection con) {

        int id = 0;

        PreparedStatement pst = null;
        ResultSet result = null;

        String sql = "SELECT MAX(IDPOKEMON) FROM POKEMON ";

        try {

            pst = con.prepareStatement(sql);
            result = pst.executeQuery();

            if (result.next()) {

                id = result.getInt("MAX(IDPOKEMON)");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }

        return id;
    }

    
    public List listEvos(Connection con, int id) {
        int id2 = 0;

        List lista = new ArrayList<>();

        String sql = "SELECT EVOLVESTO FROM EVO WHERE IDPOKEMON = (?)";
        String sql1 = "SELECT LEVEL FROM EVO WHERE IDPOKEMON =(?)";
        String sql2 = "SELECT NAME FROM POKEMON WHERE IDPOKEMON =(?)";

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            //Search items Pokemon Choosen
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                id2 = rs.getInt("EVOLVESTO");
            }

            if (id2 > 0) {

            } else {
                id = id2;
            }

            //Search level evolution
            while (id > 0) {
                String m = "";

                st = con.prepareStatement(sql1);
                st.setInt(1, id);
                rs = st.executeQuery();

                if (rs.next()) {
                    m = rs.getString("LEVEL");

                }

                st = con.prepareStatement(sql);
                st.setInt(1, id);
                rs = st.executeQuery();

                if (rs.next()) {
                    id = rs.getInt("EVOLVESTO");
                } else {
                    id = 0;
                }
                //search name pokemon for the list
                st = con.prepareStatement(sql2);
                st.setInt(1, id);
                rs = st.executeQuery();

                while (rs.next()) {

                    String n = rs.getString("NAME");
                    lista.add("Evolves at " + m + " To: " + n);
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR LIST");
        }

        return lista;

    }

}
