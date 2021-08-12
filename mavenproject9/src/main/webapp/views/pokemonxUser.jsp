<%-- 
    Document   : abmAbilityType
    Created on : 5 ago 2021, 14:03:58
   
--%>
<%@page import="com.pokedex.ec.entity.User"%>
<%@page import="com.pokedex.ec.bo.UserBO"%>
<%@page import="java.util.List"%>
<%@page import="com.pokedex.ec.entity.Pokemon"%>
<%@page import="com.pokedex.ec.bo.PokemonBO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <title>JSP Page</title>
    </head>
    <body>




    <body>
        <% PokemonBO pbo = new PokemonBO(); %>

        <div>
            <h1 class="col-auto text-light bg-dark p-5 text-center"> Add Pokemon To User</h1>


            <form action="pokemonxuserController?menu=pokemon" method="post">
                <select name="pokeSelect" class="form-control form-control">

                    <%

                        List<Pokemon> pokemonlist = pbo.loadPokemon();
                        if (pokemonlist != null) {
                            for (Pokemon pokemon : pokemonlist) {%>
                    <option  "><%=pokemon.getName()%></option>                        
                    <% }
                        }%>

                </select> <br><br>


                <select name="userSelect" class="form-control form-control">

                    <%
                        List<User> userlist = pbo.loadUser();
                        if (pokemonlist != null) {
                            for (User user : userlist) {%>
                    <option  "><%=user.getName()%></option>                        
                    <% }
                        }%>

                </select><br><br>
                <input type="submit" name="action" value="Add" class="form-control form-control btn btn-primary"><br><br>
                <input type="submit" name="action" value="Return" class="form-control form-control btn btn-primary"><br>
            </form>


    </body>
</html>
