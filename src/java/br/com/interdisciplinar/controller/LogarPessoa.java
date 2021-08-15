package br.com.interdisciplinar.controller;

import br.com.interdisciplinar.DAO.PessoaDAOImpl;
import br.com.interdisciplinar.model.Pessoa;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Enilson Filho
 */
@WebServlet(name = "LogarPessoa", urlPatterns = {"/LogarPessoa"})
public class LogarPessoa extends HttpServlet {

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
        String mensagem = null;

        if (request.getParameter("acao").equals("logar")) {

            if (!request.getParameter("login").equals("") || !request.getParameter("senha").equals("")) {

                try {
                    PessoaDAOImpl dao = new PessoaDAOImpl();
                    Pessoa pessoa = dao.logarPessoa(request.getParameter("login"), request.getParameter("senha"));

                    if (pessoa != null) {

                        HttpSession session = request.getSession(true);
                        session.setAttribute("pessoa", pessoa);

                        request.getRequestDispatcher("ListarMusica").forward(request, response);

                    } else {
                        mensagem = "Login ou Senha inválidos, Tente Novamente!";
                        request.setAttribute("msglogar", mensagem);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }

                } catch (Exception ex) {
                    System.out.println("Problemas ao logar Pessoa! Erro: " + ex.getMessage());
                    ex.printStackTrace();
                }

            } else {
                mensagem = "Login ou Senha inválidos!";
                request.setAttribute("msglogar", mensagem);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else if (request.getParameter("acao").equals("logout")) {
            HttpSession session = request.getSession(true);
            session.invalidate();
            response.sendRedirect("index.jsp");
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
