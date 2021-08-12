<%-- 
    Document   : edit
    
--%>

<%@page import="java.util.Iterator"%>
<%@page import="com.pokedex.ec.entity.Pokemon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.pokedex.ec.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.pokedex.ec.entity.Types"%>
<%@page import="com.pokedex.ec.bo.PokemonBO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <% PokemonBO pbo = new PokemonBO(); %>
        <div>
            <h1 class="col-auto text-light bg-dark p-5 text-center"> view Pokemon </h1>


            <form action="viewController?menu=pokemon" method="post">
                <select name="pokeSelect" class="form-control form-control">

                    <%

                        List<Pokemon> pokemonlist = pbo.loadPokemon();
                        if (pokemonlist != null) {
                            for (Pokemon pokemon : pokemonlist) {%>
                    <option  "><%=pokemon.getName()%></option>                        
                    <% }
                        }%>

                </select>
                <br><br>
                <input type="submit" name="action" value="Filter" class="form-control form-control btn btn-primary"><br><br>
                <input type="submit" name="action" value="Abilities" class="form-control form-control btn btn-primary"><br>
            </form>





        </div>



    </body>


</html>
