<%-- 
    Document   : list
    
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="com.pokedex.ec.entity.User"%>
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
        <div>
            <h1 class="col-auto text-light bg-dark p-5 text-center">Pokemons</h1>
            <table border="1" class="table table-striped">
                <thead >
                    <tr> 
                        <th scope="col">ID</th>
                        <th  scope="col">Name</th>
                        <th  scope="col">Type</th>
                        <th  scope="col">Type2</th>
                        <th  scope="col">Level</th>
                        <th  scope="col">Evolution</th>
                        <th scope="col">User who registered first</th>
                    </tr>
                </thead>


                <tbody>
                    <c:forEach var="poke" items="${listPokemon}">
                        <tr>
                            <td>${poke.getIdpokemon()} </td>
                            <td>${poke.getName()}</td>
                            <td>${poke.getType()}</td>
                            <td>${poke.getType2()}</td>
                            <td>${poke.getLevel()}</td>
                            <td>${poke.getEvolution()}</td>
                            <td>${poke.getUser()}</td>
                        </tr>
                    </c:forEach>


                </tbody>
            </table>
            <form action="listController?menu=pokemon" method="post">
                <select name="userfiltro" class="form-control form-control"  >
                    <%PokemonBO pbo = new PokemonBO();
                                List<User> listUser = pbo.loadUser();
                                if (listUser != null) {
                                    for (User user : listUser) {%>
                    <option  value="<%=user.getName()%>"><%=user.getName()%></option>                        
                    <% }
                            }%><br>

                    <input type="submit" name="action" value="Filter" class="form-control form-control btn btn-primary"><br>
                    <input type="submit" name="action" value="list" class="form-control form-control btn btn-primary"><br>
                    </form>
                    </div>

                </select>
        </div>
    </body>
</html>
