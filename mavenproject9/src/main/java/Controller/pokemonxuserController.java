/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.pokedex.ec.bo.PokemonBO;
import com.pokedex.ec.bo.TypeAbilityBO;
import com.pokedex.ec.entity.PokemonUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ichu_
 */
public class pokemonxuserController extends HttpServlet {

    String vista = "views/pokemonxUser.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet typesAbController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet typesAbController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String access = "";
        String action = request.getParameter("action");

        if (menu.equals("pokemon")) {
            switch (action) {
                case "list":

                    TypeAbilityBO tabo = new TypeAbilityBO();
                    List listaAbilities = tabo.listAbilities();
                    request.setAttribute("listAbility", listaAbilities);
                    access = vista;
                    break;
            }

        }
        request.getRequestDispatcher(access).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String access = "";
        String action = request.getParameter("action");

        if (menu.equals("pokemon")) {
            switch (action) {
                case "Add":

                    //busco los parametros de pokemon y usuario y los agrego
                    String poke = request.getParameter("pokeSelect");
                    String user = request.getParameter("userSelect");
                    PokemonBO pbo = new PokemonBO();
                    int id = pbo.serchaId(poke);
                    int iduser = pbo.serchaIdUser(user);
                    PokemonUser pxu = new PokemonUser();
                    pxu.setIdpokemon(id);
                    pxu.setIduser(iduser);

                    pbo.addPokemonUser(pxu);

                    access = vista;
                    break;

                case "Return":

                    access = "/";
                    break;

            }

        }

        RequestDispatcher view = request.getRequestDispatcher(access);
        view.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
