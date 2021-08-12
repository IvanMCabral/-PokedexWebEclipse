/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.ec.dao;

import com.pokedex.ec.bo.PokemonBO;
import com.pokedex.ec.db.Conexion;
import com.pokedex.ec.entity.Pokemon;
import com.pokedex.ec.entity.PokemonUser;
import com.pokedex.ec.entity.Types;
import com.pokedex.ec.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class PokemonDAO {

    private Methods mdao = new Methods();
    private String message = "";
    
    private DataSource ds;

    public PokemonDAO(DataSource ds) {
        this.ds = ds;
    }
     public PokemonDAO() {
        
    }

    public String addPokemon(Connection con, Pokemon pok) {
        PreparedStatement pst = null;
        ResultSet rs;
        //first I look for if it exists
        String search = "SELECT * FROM POKEMON WHERE NAME = ?";

        try {
            
            pst = con.prepareStatement(search);
            pst.setString(1, pok.getName());
            rs = pst.executeQuery();

            if (rs.next()) {
                message = "There is already a pokemon with that name";
                return message;

            }

        } catch (SQLException e) {
            message = "Error save \n " + e.getMessage();
        }

        //Pokemon insert
        String sql = "INSERT INTO POKEMON (NAME, TYPE, TYPE2, LEVEL, USER, EVOLUTION) VALUES( ?, ?, ?, ?, ?,?)";
        try {
            
            pst = con.prepareStatement(sql);

            pst.setString(1, pok.getName());
            pst.setString(2, pok.getType());
            pst.setString(3, pok.getType2());
            pst.setInt(4, pok.getLevel());
            pst.setString(5, pok.getUser());
            pst.setInt(6, pok.getEvolution());
            message = "New Pokemon Inserted!!";

            pst.execute();
            pst.close();
            con.commit();

        } catch (SQLException e) {
            message = "Error Save";
        }

        return message;
    }

    public String addPokemonxUser(Connection con, PokemonUser pok) {
        PreparedStatement pst = null;
        ResultSet rs;
        //first I look for if it exists

        //Pokemon insert
        String sql = "INSERT INTO POKEMONXUSER (IDPOKEMON, IDUSER) VALUES( ?, ?)";
        try {

            pst = con.prepareStatement(sql);

            pst.setInt(1, pok.getIdpokemon());
            pst.setInt(2, pok.getIduser());

            pst.execute();
            pst.close();
            con.commit();

        } catch (SQLException e) {
            message = "Error Save \n " + e.getMessage();
        }

        return message;
    }

    public String modifyPokemon(Connection con, Pokemon pok) {
        PreparedStatement pst = null;
        ResultSet rs;
        //first I look for if the name exists
        String search = "SELECT * FROM POKEMON WHERE NAME = ? AND IDPOKEMON != ?";

        try {

            pst = con.prepareStatement(search);
            pst.setString(1, pok.getName());
            pst.setInt(2, pok.getIdpokemon());
            rs = pst.executeQuery();

            if (rs.next()) {
                message = "There is already a pokemon with that name";
                return message;

            }

        } catch (SQLException e) {
            message = "Error save \n " + e.getMessage();
        }

        String sql = "UPDATE POKEMON SET NAME = ?, TYPE = ?, TYPE2 = ?, LEVEL = ?, USER = ? "
                + " WHERE IDPOKEMON = ?";
        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, pok.getName());
            pst.setString(2, pok.getType());
            pst.setString(3, pok.getType2());
            pst.setInt(4, pok.getLevel());
            pst.setString(5, pok.getUser());
            pst.setInt(6, pok.getIdpokemon());           

            pst.execute();
            pst.close();
            con.commit();
            
            message = "Edited OK";

        } catch (SQLException e) {
            message = "Error Save";
        }

        return message;
    }

    public ArrayList<Pokemon> getPokemon(Connection con) {
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Pokemon> Pokemonlist = new ArrayList<>();

        try {

            String sql = "SELECT * FROM POKEMON ";
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

    public ArrayList<Pokemon> getPokemonUser(Connection con, int id) {
        PreparedStatement st = null;
        ResultSet rs;
        PokemonBO pbo = new PokemonBO();
        ArrayList<Pokemon> Pokemonlist = new ArrayList<>();

        try {

            String sql = "SELECT IDPOKEMON FROM POKEMONXUSER WHERE IDUSER = " + "'" + id + "'";
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int idpokemon = rs.getInt("IDPOKEMON");
                Pokemon p = pbo.loadPokemon(idpokemon);
                Pokemonlist.add(p);

            }

        } catch (SQLException e) {

            message = "ERROR SAVE \n " + e.getMessage();
        }

        return Pokemonlist;

    }

    public Pokemon getPokemon(Connection con, int id) {
        Statement st;
        ResultSet rs;
        Pokemon pokemon = new Pokemon();
        try {

            st = con.createStatement();

            String sql = "SELECT * FROM POKEMON WHERE IDPOKEMON= " + "'" + id + "'";

            rs = st.executeQuery(sql);

            while (rs.next()) {

                pokemon.setIdpokemon(id);
                pokemon.setName(rs.getString("NAME"));
                pokemon.setType(rs.getString("TYPE"));
                pokemon.setType2(rs.getString("TYPE2"));
                pokemon.setLevel(rs.getInt("LEVEL"));
                pokemon.setEvolution(rs.getInt("EVOLUTION"));
                pokemon.setUser(rs.getString("USER"));

            }

        } catch (SQLException e) {

            message = "ERROR SAVE \n " + e.getMessage();
        }

        return pokemon;

    }

    public ArrayList<Types> getTypes(Connection con) {
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Types> Typeslist = new ArrayList<>();

        try {

            String sql = "SELECT * FROM TYPE ";
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Types types = new Types();

                types.setName(rs.getString("NAME"));

                Typeslist.add(types);

            }

        } catch (SQLException e) {

            message = "ERROR SAVE \n " + e.getMessage();
        }

        return Typeslist;

    }

    public ArrayList<User> getUser(Connection con) {
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<User> Userlist = new ArrayList<>();

        try {

            String sql = "SELECT * FROM USER ";
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                User User = new User();

                User.setName(rs.getString("NAME"));

                Userlist.add(User);

            }

        } catch (SQLException e) {

            message = "ERROR SAVE \n " + e.getMessage();
        }

        return Userlist;

    }

    public ArrayList<Pokemon> ListEvolves(Connection con, int id) {

        ArrayList<Pokemon> Pokemonlist = new ArrayList<>();

        int id2 = 1;

        String sql = "SELECT IDPOKEMON FROM EVO WHERE EVOLVESTO = (?)";
        String sql1 = "SELECT * FROM POKEMON WHERE IDPOKEMON =(?)";
        String sql2 = "SELECT EVOLVESTO FROM EVO WHERE IDPOKEMON = (?)";

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            //Search preevos
            while (id2 > 0) {
                st = con.prepareStatement(sql);
                st.setInt(1, id);
                rs = st.executeQuery();

                if (rs.next()) {
                    id2 = rs.getInt("IDPOKEMON");
                    id = id2;
                } else {
                    id2 = 0;
                }
            }
            //list preevo

            st = con.prepareStatement(sql1);
            st.setInt(1, id);
            rs = st.executeQuery();

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

            //search full evolution
            while (id > 0) {
                //sql2
                st = con.prepareStatement(sql2);
                st.setInt(1, id);
                rs = st.executeQuery();

                if (rs.next()) {
                    id = rs.getInt("EVOLVESTO");
                } else {
                    id = 0;
                }

                //list full evolutions sql1
                st = con.prepareStatement(sql1);
                st.setInt(1, id);
                rs = st.executeQuery();

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

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "ERROR LIST TABLE");
        }

        return Pokemonlist;
    }

    public void deletePokemon(Connection con, int id) {

        PreparedStatement st = null;
        ResultSet rs = null;
        int idpre, idpok = id;

        //evolutions 
        String sql = "DELETE POKEMON, EVO FROM POKEMON JOIN EVO ON POKEMON.IDPOKEMON=EVO.IDPOKEMON WHERE POKEMON.IDPOKEMON = ? ";
        String searchEvo = "SELECT EVOLVESTO FROM EVO WHERE IDPOKEMON = ?";
        String DeleteOne = "DELETE FROM POKEMON WHERE IDPOKEMON = ?";
        String deleteUserxPokemon = "DELETE FROM POKEMONXUSER WHERE IDPOKEMON = ?";

        //pre evolution update
        String searchPreevo = "SELECT IDPOKEMON FROM EVO WHERE EVOLVESTO = ?";
        String updatePreevo = "UPDATE POKEMON SET EVOLUTION =0 WHERE IDPOKEMON = ? ";
        String deletePreevo = "DELETE FROM EVO WHERE IDPOKEMON = ? ";

        try {
            // search preevo for update
            st = con.prepareStatement(searchPreevo);
            st.setInt(1, id);
            rs = st.executeQuery();

            while (rs.next()) {

                idpre = rs.getInt("IDPOKEMON");
                st = con.prepareStatement(updatePreevo);
                st.setInt(1, idpre);
                st.execute();

                st = con.prepareStatement(deletePreevo);
                st.setInt(1, idpre);
                st.execute();
                con.commit();

                st = con.prepareStatement(deleteUserxPokemon);
                st.setInt(1, idpre);
                st.execute();
                con.commit();

            }

            //Delete evolutions      
            while (id > 0) {

                st = con.prepareStatement(searchEvo);
                st.setInt(1, id);
                rs = st.executeQuery();

                if (rs.next()) {
                    id = rs.getInt("EVOLVESTO");

                    st = con.prepareStatement(sql);
                    st.setInt(1, idpok);
                    st.execute();
                    con.commit();

                    st = con.prepareStatement(deleteUserxPokemon);
                    st.setInt(1, id);
                    st.execute();
                    con.commit();

                    idpok = id;

                } else {
                    st = con.prepareStatement(DeleteOne);
                    st.setInt(1, id);
                    st.execute();
                    con.commit();

                    st = con.prepareStatement(deleteUserxPokemon);
                    st.setInt(1, id);
                    st.execute();
                    con.commit();

                    id = 0;
                }
            }

            //Delete pokemon Choose
            st = con.prepareStatement(sql);
            st.setInt(1, idpok);
            st.execute();
            con.commit();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error Delete");
        }

    }

    public int searchlvlmin(Connection con, int id) {

        String searchPreevo = "SELECT LEVEL FROM EVO WHERE IDPOKEMON=(SELECT IDPOKEMON FROM EVO WHERE EVOLVESTO = ?)";
        int idmin = mdao.searchLvl(con, id, searchPreevo);

        return idmin;

    }

    public int searchlvlmax(Connection con, int id) {

        String s = "SELECT LEVEL FROM EVO WHERE IDPOKEMON=?";
        int idmax = mdao.searchLvl(con, id, s);

        if (idmax == 0) {
            idmax = 101;
        }

        return idmax;

    }

    public String listAbilities(int id) {

        String name = "select name from ability where idpokemon =" + "'" + id + "'";

        return name;
    }



    public ArrayList<Pokemon> getLastPokemon(Connection con) {
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Pokemon> Pokemonlist = new ArrayList<>();

        try {

            String sql = "SELECT * FROM POKEMON where evolution = 0 ";
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

    public String tablePokemons() {

        String sql = "SELECT * FROM POKEMON";
        return sql;
    }

    public String listPokemon(String string) {

        String sql = "SELECT * FROM POKEMON WHERE USER =" + "'" + string + "'";
        return sql;
    }

    

}
