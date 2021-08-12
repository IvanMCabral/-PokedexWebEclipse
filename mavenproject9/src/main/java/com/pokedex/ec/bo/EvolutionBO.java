/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.ec.bo;

import com.pokedex.ec.entity.Evolve;
import com.pokedex.ec.dao.EvolveDAO;
import com.pokedex.ec.dao.Methods;
import com.pokedex.ec.db.Conexion;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class EvolutionBO {

    private String message = "";
    private EvolveDAO edao = new EvolveDAO();
    private Methods mdao = new Methods();

    public String addEvoTable(Evolve ev) {
        Connection conn = Conexion.getConnection();

        try {

            message = edao.addEvoTable(conn, ev);
        } catch (Exception e) {

            message = message + " " + e.getMessage();
        }//finally try
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                message = message + " " + e.getMessage();
            }
        }
        return message;
    }

    public int lastPoke() {
        int poke = 0;
        Connection conn = Conexion.getConnection();

        try {
            poke = edao.lastPoke(conn);

        } catch (Exception e) {

            message = message + " " + e.getMessage();
        }//finally try
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                message = message + " " + e.getMessage();
            }
        }
        return poke;
    }

    public List listEvos(int id) {
        Connection conn = Conexion.getConnection();
        List evolist = new ArrayList<>();
        try {
            evolist = edao.listEvos(conn, id);

        } catch (Exception e) {

            message = message + " " + e.getMessage();
        }//finally try
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                message = message + " " + e.getMessage();
            }
        }
        return evolist;
    }
}
