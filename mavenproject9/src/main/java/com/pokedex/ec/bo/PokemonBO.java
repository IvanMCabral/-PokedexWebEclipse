/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.ec.bo;

import com.pokedex.ec.entity.Pokemon;
import com.pokedex.ec.entity.Types;
import com.pokedex.ec.entity.User;
import com.pokedex.ec.dao.Methods;
import com.pokedex.ec.dao.PokemonDAO;
import com.pokedex.ec.db.Conexion;
import com.pokedex.ec.entity.PokemonUser;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class PokemonBO {

    private String message = "";
    private Methods mdao = new Methods();
    private PokemonDAO pdao = new PokemonDAO();
    private Methods methods;
    
    public PokemonBO(Methods methods) {
        this.methods = methods;
      }
    
    public PokemonBO() {
        
      }
    

    public String addPokemon(Pokemon pok) {
        Connection conn = Conexion.getConnection();

        try {//Go to DAO
            message = pdao.addPokemon( conn, pok );

        } catch (Exception e) {

            message = message + " " + e.getMessage();
        }//finally try
        finally {
            try {
                if (conn != null) {
                    //finally conection
                    conn.close();

                }
            } catch (Exception e) {
                message = message + " " + e.getMessage();
            }
        }
        return message;
    }

    public String addPokemonUser(PokemonUser pok) {
        Connection conn = Conexion.getConnection();

        try {//Go to DAO
            message = pdao.addPokemonxUser(conn, pok);

        } catch (Exception e) {

            message = message + " " + e.getMessage();
        }//finally try
        finally {
            try {
                if (conn != null) {
                    //finally conection
                    conn.close();

                }
            } catch (Exception e) {
                message = message + " " + e.getMessage();
            }
        }
        return message;
    }

    public String modifyPokemon(Pokemon pok) {

        Connection conn = Conexion.getConnection();

        try {
            message = pdao.modifyPokemon(conn, pok);

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

    public String deletePokemon(int id) {
        Connection conn = Conexion.getConnection();

        try {
            pdao.deletePokemon(conn, id);

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

    public ArrayList<Pokemon> loadPokemon() {
        Connection conn = Conexion.getConnection();

        ArrayList<Pokemon> Pokemonlist = new ArrayList<>();

        try {

            Pokemonlist = pdao.getPokemon(conn);

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

        return Pokemonlist;
    }

    public Pokemon loadPokemon(int id) {
        Connection conn = Conexion.getConnection();

        Pokemon PokemonOnly = new Pokemon();

        try {

            PokemonOnly = pdao.getPokemon(conn, id);

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
        return PokemonOnly;
    }

    public ArrayList<Types> loadTypes() {
        Connection conn = Conexion.getConnection();

        ArrayList<Types> Typeslist = new ArrayList<>();

        try {

            Typeslist = pdao.getTypes(conn);

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

        return Typeslist;
    }

    public ArrayList<Pokemon> loadLastPokemon() {
        Connection conn = Conexion.getConnection();

        ArrayList<Pokemon> Pokemonlist = new ArrayList<>();

        try {

            Pokemonlist = pdao.getLastPokemon(conn);

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

        return Pokemonlist;
    }

    public ArrayList<User> loadUser() {
        Connection conn = Conexion.getConnection();

        ArrayList<User> Userlist = new ArrayList<>();

        try {

            Userlist = pdao.getUser(conn);

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

        return Userlist;
    }

    public ArrayList<Pokemon> PokemonxUser(int id) {
        Connection conn = Conexion.getConnection();
        ArrayList<Pokemon> pokelist = new ArrayList<>();

        try {

            pokelist = pdao.getPokemonUser(conn, id);

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
        return pokelist;
    }

    public ArrayList<Pokemon> ListEvolves(int id) {
        Connection conn = Conexion.getConnection();
        ArrayList<Pokemon> pokelist = new ArrayList<>();

        try {

            pokelist = pdao.ListEvolves(conn, id);

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
        return pokelist;
    }

    public List listAbilities(int id) {
        Connection conn = Conexion.getConnection();
        List abilitylist = new ArrayList<>();

        String name = pdao.listAbilities(id);

        try {

            abilitylist = mdao.list(conn, name);

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
        return abilitylist;
    }

    public int serchaId(String Poke) {
        int poke = 0;
        Connection conn = Conexion.getConnection();

        try {
            poke = mdao.searchbyname(conn, Poke);

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

    public int serchaIdUser(String user) {
        int iduser = 0;
        Connection conn = Conexion.getConnection();

        try {
            iduser = mdao.searchuserbyname(conn, user);

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
        return iduser;
    }

    public int searchlvlmin(int id) {
        Connection conn = Conexion.getConnection();
        int min = 0;
        try {

            min = pdao.searchlvlmin(conn, id);

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
        return min;
    }

    public int searchlvlmax(int id) {
        Connection conn = Conexion.getConnection();
        int min = 0;
        try {

            min = pdao.searchlvlmax(conn, id);

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
        return min;
    }

}
