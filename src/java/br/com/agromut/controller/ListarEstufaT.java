package br.com.agromut.controller;

import br.com.agromut.dao.EstufaDAOImpl;
import br.com.agromut.dao.GenericDAO;
import br.com.agromut.dao.ProducaoDAOImpl;
import br.com.agromut.dao.ProdutoDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListarEstufaT", urlPatterns = {"/ListarEstufaT"})
public class ListarEstufaT extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                EstufaDAOImpl dao = new EstufaDAOImpl();
                request.setAttribute("estufas", dao.listarAtivosEstufa());

                ProdutoDAOImpl daop = new ProdutoDAOImpl();
                request.setAttribute("produtosestufa", daop.listar());
                
                ProducaoDAOImpl daopd = new ProducaoDAOImpl();
                request.setAttribute("produtosestufasalvo", daopd.listarprodutoestufa());

                request.getRequestDispatcher("usuarioAdm/listarestufasativas.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("problemas ao listar estufas! Erro: " + e.getMessage());
                e.printStackTrace();
            }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
