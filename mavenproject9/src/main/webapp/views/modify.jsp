<%-- 
    Document   : modify
  
--%>

<%@page import="com.pokedex.ec.entity.User"%>
<%@page import="com.pokedex.ec.entity.Types"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.pokedex.ec.bo.PokemonBO"%>
<%@page import="com.pokedex.ec.entity.Pokemon"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <title>List Pokemons</title>

    </head>
    <body>
        <% PokemonBO pbo = new PokemonBO(); %>
        <div>
            <h1 class="col-auto text-light bg-dark p-5 text-center">Pokemons</h1>




        </div>
        <div class="row">
            <div class="card col-md-4">
                <div class="card-body">
                    <h5 class="card-title">Pokemon</h5>
                    <h6 class="card-subtitle mb-2 text-muted">In this panel you can manage the data of the Pokemons</h6>
                    <div>
                        <form action="Controller?menu=pokemon" method="post">
                            <div class="form-group">
                                <label>Name</label>

                                <input type="text" name="name" class="form-control" value="${pokemonSelected.getName()}">

                                <small class="form-text text-muted">Inser a valid name</small>
                            </div>
                            <div class="form-group">
                                <label>Type</label>
                                <select name="type" class="form-control form-control" >
                                    <option>${pokemonSelected.getType()}</option>
                                    <%

                                        List<Types> listTypes = pbo.loadTypes();
                                        if (listTypes != null) {
                                            for (Types type : listTypes) {%>
                                    <option><%=type.getName()%></option>                        
                                    <% }
                                        } %>
                                </select>

                            </div>
                            <div class="form-group">
                                <label>Type2</label>
                                <select name="type2" class="form-control form-control text-center">
                                    <option>${pokemonSelected.getType2()}</option>
                                    <%
                                        if (listTypes != null) {
                                            for (Types type : listTypes) {%>
                                    <option  value="<%=type.getName()%>"><%=type.getName()%></option>                        
                                    <% }
                                        } %>
                                </select>

                            </div>

                            <div class="form-group">
                                <label>Level</label>
                                <input type="text" name="level" class="form-control" value="${pokemonSelected.getLevel()}">
                            </div>
                            <div class="form-group">
                                <label>User</label>
                                <select name="user" class="form-control form-control">
                                    <option>${pokemonSelected.getUser()}</option>
                                    <%

                                        List<User> listUser = pbo.loadUser();
                                        if (listUser != null) {
                                            for (User user : listUser) {%>
                                    <option  "><%=user.getName()%></option>                        
                                    <% }
                                        }%>

                                </select>
                            </div>


                            <input type="submit" name="action" value="Add Pokemon" class="btn btn-primary" >
                            <input type="submit" class="btn btn-success" name="action" value="Modify Pokemon" >
                        </form>
                    </div>
                </div>
            </div>

            <div class="card col-md-8">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Name</th>
                            <th scope="col">Type</th>
                            <th scope="col">Type2</th>
                            <th scope="col">Level</th>
                            <th scope="col">Evolution</th>
                            <th scope="col">User</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%

                            List<Pokemon> list = pbo.loadPokemon();
                            Iterator<Pokemon> iter = list.iterator();
                            Pokemon poke = null;

                            while (iter.hasNext()) {
                                poke = iter.next();


                        %>

                    <tbody>

                        <tr>
                            <td><%= poke.getIdpokemon()%> </td>
                            <td><%= poke.getName()%></td>
                            <td><%= poke.getType()%></td>
                            <td><%= poke.getType2()%></td>
                            <td><%= poke.getLevel()%></td>
                            <td><%= poke.getEvolution()%></td>
                            <td><%= poke.getUser()%></td>


                            <td>
                                <a class="btn btn-warning" href="Controller?action=edit&id=<%= poke.getIdpokemon()%>">Editar</a>
                                <a class="btn btn-danger" href="Controller?action=delete&id=<%= poke.getIdpokemon()%>">Eliminar</a>
                            </td>

                        </tr>


                        <% }%>





                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
