<%-- 
    Document   : PokemonDataEvos
    Created on : 5 ago 2021, 10:10:01
    
--%>
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

        <h1 class="col-auto text-light bg-dark p-5 text-center">Data Pokemon Selected</h1>
        <div>
            <div class="row">
                <div class="card col-md-4">
                    <div class="card-body">
                        <h5 class="card-title">Pokemon</h5>
                        <table border="1" class="table table-striped " >
                            <thead >
                                <tr>                        
                                    <th  scope="col">Name</th>
                                    <th  scope="col">Type</th>
                                    <th  scope="col">Type2</th>
                                    <th  scope="col">Level</th>
                                    <th  scope="col">Evolution</th>
                                    <th scope="col">User</th>
                                </tr>
                            </thead>


                            <tbody>

                                <tr>

                                    <td><label>${pokemonSelected.getName()}</label></td>
                                    <td><label>${pokemonSelected.getType()}</label></td>
                                    <td><label>${pokemonSelected.getType2()}</label></td>
                                    <td><label>${pokemonSelected.getLevel()}</label></td>
                                    <td><label>${pokemonSelected.getEvolution()}</label></td>
                                    <td><label>${pokemonSelected.getUser()}</label></td>
                                </tr>



                            </tbody>
                        </table>
                    </div>
                </div>        


                <div class="card col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Family Pokemon</h5>
                        <table border="1" class="table table-striped">
                            <thead >
                                <tr> 
                                    <th scope="col">ID</th>
                                    <th  scope="col">Name</th>
                                    <th  scope="col">Type</th>
                                    <th  scope="col">Type2</th>
                                    <th  scope="col">Level</th>
                                    <th  scope="col">Evolution</th>
                                    <th scope="col">User</th>
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
                    </div>
                </div>
            </div>
        </div>
        <h1 class="col-auto text-light bg-dark p-2 text-center">Abilities and Evolves</h1>
        <div>
            <div class="row">
                <div class="card col-md-4">
                    <div class="card-body">
                        <h5 class="card-title">Pokemon</h5>
                        <table border="1" class="table table-striped">
                            <thead >
                                <tr> 

                                    <th  scope="col">Name</th>

                                </tr>
                            </thead>


                            <tbody>
                                <c:forEach var="poke" items="${listAbility}">
                                    <tr>

                                        <td>${poke}</td>

                                    </tr>
                                </c:forEach>


                            </tbody>
                        </table>
                    </div>
                </div>


                <div class="card col-md-6">
                    <div class="card-body">
                        <h5 class="card-title">Pokemon</h5>
                        <table border="1" class="table table-striped">
                            <thead >
                                <tr> 

                                    <th  scope="col">Name</th>

                                </tr>
                            </thead>


                            <tbody>
                                <c:forEach var="evo" items="${listEvo}">
                                    <tr>

                                        <td>${evo}</td>

                                    </tr>
                                </c:forEach>


                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
