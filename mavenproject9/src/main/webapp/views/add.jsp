<%-- 
    Document   : add
   
--%>

<%@page import="com.pokedex.ec.entity.Pokemon"%>
<%@page import="com.pokedex.ec.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.pokedex.ec.entity.Types"%>
<%@page import="com.pokedex.ec.bo.PokemonBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <title>Add jsp Page</title>
    </head>
    <body>
        <% PokemonBO pbo = new PokemonBO(); %>
        <div>
            <h1 class="col-auto text-light bg-dark p-5 text-center"> Add Pokemon Evolution</h1>

            <form action="Controller?menu=pokemon" method="post">
                <div class="form-group  p-5 text-center col-auto  " >

                    <select name="pokeevo" class="form-control form-control">

                        <%

                            List<Pokemon> pokemonlist = pbo.loadLastPokemon();
                                    if (pokemonlist != null) {
                                        for (Pokemon pokemon : pokemonlist) {%>
                        <option  "><%=pokemon.getName()%></option>                        
                        <% }
                                    }%>

                    </select>


                    NAME:<br>
                    <input type="text" name="name" class="form-control form-control"><br>
                    TYPE:<br>
                    <select name="type" class="form-control form-control" >
                        <%

                            List<Types> listTypes = pbo.loadTypes();
                                if (listTypes != null) {
                                    for (Types type : listTypes) {%>
                        <option  value="<%=type.getName()%>"><%=type.getName()%></option>                        
                        <% }
                            } %>

                    </select><br>

                    TYPE2:<br>               
                    <select name="type2" class="form-control form-control text-center">
                        <%
                                if (listTypes != null) {
                                    for (Types type : listTypes) {%>
                        <option  value="<%=type.getName()%>"><%=type.getName()%></option>                        
                        <% }
                            } %>

                    </select><br>
                    LEVEL:<br>
                    <input type="text" name="level" class="form-control form-control"><br>
                    USER:<br>
                    <select name="user" class="form-control form-control">
                        <%
                                List<User> listUser = pbo.loadUser();

                                if (listUser != null) {
                                    for (User user : listUser) {%>
                        <option  value="<%=user.getName()%>"><%=user.getName()%></option>                        
                        <% }
                            }%>

                    </select><br>

                    <input type="submit" name="action" value="Add Evolution" class="form-control form-control btn btn-primary"><br>
                </div>
            </form>

        </div>
    </body>
</html>
