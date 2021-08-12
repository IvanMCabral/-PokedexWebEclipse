/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.pokedex.ec.bo.PokemonBO;
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
public class listController extends HttpServlet {
    String list = "views/list.jsp";
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
            out.println("<title>Servlet listController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listController at " + request.getContextPath() + "</h1>");
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
                   
                    PokemonBO pbo = new PokemonBO();
                    List lista = pbo.loadPokemon();
                    request.setAttribute("listPokemon", lista);
                    access = list;
            
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
                case "Filter":
                    
                    //consigo el dato del usuario a filtrar
                    String userfilter = (String)request.getParameter("userfiltro");
                    PokemonBO pbo = new PokemonBO();
                    // busco la id del usuario y busco los pokemones que tiene
                    int iduser = pbo.serchaIdUser(userfilter);
                    List lista = pbo.PokemonxUser(iduser);
                    
                    request.setAttribute("listPokemon", lista);
                    access = list;
                    break;
                    
            }
        
        
        }
        
        RequestDispatcher view = request.getRequestDispatcher(access);
         view.forward (request, response);

        
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
