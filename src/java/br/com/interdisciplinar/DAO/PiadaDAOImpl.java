/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interdisciplinar.DAO;

import br.com.interdisciplinar.model.Piada;
import br.com.interdisciplinar.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enilson Filho
 */
public class PiadaDAOImpl implements GenericDAO {

    private Connection conn;

    public PiadaDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Piada piada = (Piada) object;
        PreparedStatement stmt = null;
        String sql = "Insert into piada(conteudo, autor, data)" + "values (?,?,?);";
        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, piada.getConteudo());
            stmt.setString(2, piada.getAutor());
            stmt.setDate(3, new java.sql.Date(piada.getData().getTime()));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Problemas ao cadastrar Piada! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.err.println("Problema ao fechar conexão! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select p.* from piada p order by p.id desc;";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Piada piada = new Piada();
                piada.setId(rs.getInt("id"));
                piada.setConteudo(rs.getString("conteudo"));
                piada.setAutor(rs.getString("autor"));
                piada.setData(rs.getDate("data"));
                resultado.add(piada);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Piada! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public void excluir(Integer idObject) {
        PreparedStatement stmt = null;
        String sql = "Delete from piada where id=?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Piada! Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Boolean alterar(Object object) {
        Piada piada = (Piada) object;
        PreparedStatement stmt = null;
        String sql = "update piada set conteudo=?, autor=?, data=? where id=?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, piada.getConteudo());
            stmt.setString(2, piada.getAutor());
            stmt.setDate(3, new java.sql.Date(piada.getData().getTime()));
            stmt.setInt(4, piada.getId());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println("Problemas ao alterar Piada! Erro:" + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.err.println("Problemas ao fechar conexão! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
