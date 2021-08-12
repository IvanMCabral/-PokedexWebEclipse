/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.ec.bo;

import com.pokedex.ec.dao.UserDAO;
import com.pokedex.ec.db.Conexion;
import java.sql.Connection;
import javax.swing.JComboBox;

/**
 *
 * @author
 */
public class UserBO {

    private String message = "";
    private UserDAO udao = new UserDAO();

    public String addUser(String object) {
        Connection conn = Conexion.getConnection();

        try {

            message = udao.addUser(conn, object);

        } catch (Exception e) {

            message = message + " " + e.getMessage();
        }//finally
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

    

}
