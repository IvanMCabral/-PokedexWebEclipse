/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.ec.dao;

import com.pokedex.ec.entity.Pokemon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class Methods {

    private String message = "";

    public ArrayList<Pokemon> listPokemon(Connection con, String string) {

        String sql = string;

        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Pokemon> Pokemonlist = new ArrayList<>();

        try {

            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setIdpokemon(rs.getInt("IDPOKEMON"));
                pokemon.setName(rs.getString("NAME"));
                pokemon.setType(rs.getString("TYPE"));
                if (rs.getString("TYPE2") == null) {
                    pokemon.setType2("does not have");
                } else {
                    pokemon.setType2(rs.getString("TYPE2"));
                }

                pokemon.setLevel(rs.getInt("LEVEL"));
                pokemon.setEvolution(rs.getInt("EVOLUTION"));
                pokemon.setUser(rs.getString("USER"));
                Pokemonlist.add(pokemon);

            }

        } catch (SQLException e) {

            message = "ERROR SAVE \n " + e.getMessage();
        }

        return Pokemonlist;
    }

    public int searchbyname(Connection con, String pokeName) {
        int idpokemon = 0;
        PreparedStatement pst = null;
        ResultSet result = null;

        String sql = "SELECT IDPOKEMON FROM POKEMON WHERE NAME=" + "'" + pokeName + "' ";

        try {

            pst = con.prepareStatement(sql);
            result = pst.executeQuery();

            if (result.next()) {

                idpokemon = result.getInt("IDPOKEMON");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {

                    JOptionPane.showMessageDialog(null, ex);

                }
            }
        }

        return idpokemon;

    }

    public int searchuserbyname(Connection con, String username) {
        int idpokemon = 0;
        PreparedStatement pst = null;
        ResultSet result = null;

        String sql = "SELECT IDUSER FROM USER WHERE NAME=" + "'" + username + "' ";

        try {

            pst = con.prepareStatement(sql);
            result = pst.executeQuery();

            if (result.next()) {

                idpokemon = result.getInt("IDUSER");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {

                    JOptionPane.showMessageDialog(null, ex);

                }
            }
        }

        return idpokemon;

    }

    

    public List list(Connection con, String name) {
        List lista = new ArrayList<>();
        int id = 0;

        String sql = name;

        Statement st = null;
        ResultSet rs = null;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                id = 1;

                String item = (rs.getString("NAME"));
                lista.add(item);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR LIST ");
        }

        return lista;

    }

    public int searchLvl(Connection con, int id, String s) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            // Search for the level
            st = con.prepareStatement(s);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {

                id = rs.getInt("LEVEL");

            } else {
                id = 0;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error LEVEL");
        }

        return id;

    }

    public String addObject(Connection con, String d, String h) {
        PreparedStatement pst = null;
        ResultSet rs;

        String search = "SELECT * FROM " + d + " WHERE NAME = '" + h + "'";

        try {
            pst = con.prepareStatement(search);
            rs = pst.executeQuery();
            if (rs.next()) {
                message = "That name already exists";
                return message;
            }

        } catch (SQLException e) {
            message = "ERROR SAVE \n " + e.getMessage();
        }

        String sql = "INSERT INTO " + d + " (NAME) VALUES( '" + h + "')";
        try {

            pst = con.prepareStatement(sql);
            message = "Insert ";
            pst.execute();
            pst.close();
            con.commit();

        } catch (SQLException e) {
            message = "ERROR SAVE \n " + e.getMessage();
        }

        return message;
    }

    public void deleteObject(Connection con, String sql) {
        PreparedStatement st = null;

        try {

            st = con.prepareStatement(sql);
            st.execute();
            con.commit();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "ERROR DELETE");
        }

    }

}
