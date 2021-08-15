/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interdisciplinar.DAO;

import br.com.interdisciplinar.model.Frase;
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
public class FraseDAOImpl implements GenericDAO {

    private Connection conn;

    public FraseDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Frase frase = (Frase) object;
        PreparedStatement stmt = null;
        String sql = "Insert into frase(conteudo, autor, data)" + "values (?,?,?);";
        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, frase.getConteudo());
            stmt.setString(2, frase.getAutor());
            stmt.setDate(3, new java.sql.Date(frase.getData().getTime()));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Problemas ao cadastrar Frase! Erro: " + ex.getMessage());
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
        String sql = "select f.* from frase f order by f.id desc;";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Frase frase = new Frase();
                frase.setId(rs.getInt("id"));
                frase.setConteudo(rs.getString("conteudo"));
                frase.setAutor(rs.getString("autor"));
                frase.setData(rs.getDate("data"));
                resultado.add(frase);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Frase! Erro: " + ex.getMessage());
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
        String sql = "Delete from frase where id=?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Frase! Erro:" + ex.getMessage());
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
        Frase frase = (Frase) object;
        PreparedStatement stmt = null;
        String sql = "update frase set conteudo=?, autor=?, data=? where id=?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, frase.getConteudo());
            stmt.setString(2, frase.getAutor());
            stmt.setDate(3, new java.sql.Date(frase.getData().getTime()));
            stmt.setInt(4, frase.getId());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println("Problemas ao alterar Frase! Erro:" + ex.getMessage());
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
