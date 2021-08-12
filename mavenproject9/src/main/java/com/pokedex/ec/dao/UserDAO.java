/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.ec.dao;


import java.sql.Connection;
import javax.swing.JComboBox;


/**
 *
 * @author 
 */
public class UserDAO {
    private String message = "";
    private Methods mdao = new Methods();
    

    
    public String addUser(Connection conn, String object) {
        
        String d = "USER";
        message = mdao.addObject( conn, d, object);
        return message;

    }
    
    
}
