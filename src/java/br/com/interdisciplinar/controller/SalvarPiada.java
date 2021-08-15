package br.com.interdisciplinar.controller;

import br.com.interdisciplinar.DAO.GenericDAO;
import br.com.interdisciplinar.DAO.PiadaDAOImpl;
import br.com.interdisciplinar.model.Piada;
import br.com.interdisciplinar.util.Conversoes;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Enilson Filho
 */
@WebServlet(name = "SalvarPiada", urlPatterns = {"/SalvarPiada"})
public class SalvarPiada extends HttpServlet {

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
        String mensagem = null;
        String conteudo = request.getParameter("conteudo");
        String autor = request.getParameter("autor");
        Date data = Conversoes.converterDate(request.getParameter("data"));

        Piada piada = new Piada();
        piada.setConteudo(conteudo);
        piada.setAutor(autor);
        piada.setData(data);

        if (request.getParameter("id").equals("")) {

            try {
                GenericDAO dao = new PiadaDAOImpl();
                if (dao.cadastrar(piada)) {
                    mensagem = "Piada cadastrado com sucesso!";
                    response.sendRedirect(request.getContextPath() + "/ListarPiada");
                } else {
                    mensagem = "Problemas ao cadastrar Piada!";
                    response.sendRedirect(request.getContextPath() + "/ListarPiada");
                }
                request.setAttribute("mensagem", mensagem);
            } catch (Exception ex) {
                System.err.println("Problemas ao cadastrar Piada!Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            piada.setId(Integer.parseInt(request.getParameter("id")));
            try {
                GenericDAO dao = new PiadaDAOImpl();
                if (dao.alterar(piada)) {
                    mensagem = "Piada alterado com sucesso!";
                    response.sendRedirect(request.getContextPath() + "/ListarPiada");
                } else {
                    mensagem = "Problemas ao alterar Piada!";
                    response.sendRedirect(request.getContextPath() + "/ListarPiada");
                }
                request.setAttribute("mensagem", mensagem);
            } catch (Exception ex) {
                System.err.println("Problemas ao alterar Piada!Erro:" + ex.getMessage());
                ex.printStackTrace();
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
