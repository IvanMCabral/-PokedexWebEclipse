/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.ec.bo;

import com.pokedex.ec.entity.Ability;
import com.pokedex.ec.entity.Types;
import com.pokedex.ec.dao.Methods;
import com.pokedex.ec.dao.TypeAbilityDAO;
import com.pokedex.ec.db.Conexion;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JList;

/**
 *
 * @author
 */
public class TypeAbilityBO {

    private String message = "";
    private TypeAbilityDAO tadao = new TypeAbilityDAO();
    private Methods mdao = new Methods();

    

    public List listAbilities() {
        Connection conn = Conexion.getConnection();
        String name = tadao.listAbilities();
        List abilitylist = new ArrayList<>();
        
        try {
            abilitylist= mdao.list(conn,  name);
            

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
        return abilitylist;
    }

    public List listAbilitiesNeg(int id) {
        Connection conn = Conexion.getConnection();
        List abilityNeglist = new ArrayList<>();
        
        String name = tadao.listAbilitiesNeg(id);
        try {
            abilityNeglist = mdao.list(conn, name);
            

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
        return abilityNeglist;
    }

    public void listTypes(JList list) {
        Connection conn = Conexion.getConnection();

        try {

            tadao.listTypes(conn, list);

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

    }

    public String addAbility(String object) {
        Connection conn = Conexion.getConnection();

        try {

            message = tadao.addAbility(conn, object);

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

    public String addAbilityP(Ability pok) {
        Connection conn = Conexion.getConnection();

        try {
            message = tadao.addAbilityP(conn, pok);

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

    public void deleteAbilityFull(Ability pok) {
        Connection conn = Conexion.getConnection();

        try {
            tadao.deleteAbilityFull(conn, pok);

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

    }

    public void deleteAbilityP(Ability pok) {
        Connection conn = Conexion.getConnection();

        try {
            tadao.deleteAbilityP(conn, pok);

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

    }

    public String addType(String object) {
        Connection conn = Conexion.getConnection();

        try {

            message = tadao.addTypes(conn, object);

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

    public void deleteTypeFull(Types pok) {
        Connection conn = Conexion.getConnection();

        try {
            tadao.deleteTypefull(conn, pok);

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
    }

}
