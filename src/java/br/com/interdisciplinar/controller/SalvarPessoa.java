package br.com.interdisciplinar.controller;

import br.com.interdisciplinar.DAO.GenericDAO;
import br.com.interdisciplinar.DAO.PessoaDAOImpl;
import br.com.interdisciplinar.model.Pessoa;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Enilson Filho
 */
@WebServlet(name = "SalvarPessoa", urlPatterns = {"/SalvarPessoa"})
public class SalvarPessoa extends HttpServlet {

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
        String nomePessoa = request.getParameter("nomepessoa");
        String telefonePessoa = request.getParameter("telefonepessoa");
        String celularPessoa = request.getParameter("celularpessoa");
        String loginPessoa = request.getParameter("loginpessoa");
        String senhaPessoa = request.getParameter("senhapessoa");
        Pessoa.Tipo tipoPessoa = Pessoa.Tipo.parse(request.getParameter("tipopessoa"));


        Pessoa pessoa = new Pessoa();
        pessoa.setNomePessoa(nomePessoa);
        pessoa.setTelefonePessoa(telefonePessoa);
        pessoa.setCelularPessoa(celularPessoa);
        pessoa.setLoginPessoa(loginPessoa);
        pessoa.setSenhaPessoa(senhaPessoa);
        pessoa.setTipoPessoa(Pessoa.Tipo.CLIENTE);

        if (request.getParameter("idpessoa").equals("")) {

            try {
                GenericDAO dao = new PessoaDAOImpl();
                if (dao.cadastrar(pessoa)) {
                    mensagem = "Usuário cadastrado com sucesso!";
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                } else {
                    mensagem = "Problemas ao cadastrar Usuário!";
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                }
                request.setAttribute("mensagem", mensagem);
            } catch (Exception ex) {
                System.err.println("Problemas ao cadastrar Usuário!Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            pessoa.setIdPessoa(Integer.parseInt(request.getParameter("idpessoa")));
            try {
                GenericDAO dao = new PessoaDAOImpl();
                if (dao.alterar(pessoa)) {
                    mensagem = "Usuário alterado com sucesso!";
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                } else {
                    mensagem = "Problemas ao alterar Usuário!";
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                }
                request.setAttribute("mensagem", mensagem);
            } catch (Exception ex) {
                System.err.println("Problemas ao alterar Usuário!Erro:" + ex.getMessage());
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