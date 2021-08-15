/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interdisciplinar.DAO;

import br.com.interdisciplinar.model.Poesia;
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
public class PoesiaDAOImpl implements GenericDAO {

    private Connection conn;

    public PoesiaDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Poesia poesia = (Poesia) object;
        PreparedStatement stmt = null;
        String sql = "Insert into poesia(conteudo, autor, data)" + "values (?,?,?);";
        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, poesia.getConteudo());
            stmt.setString(2, poesia.getAutor());
            stmt.setDate(3, new java.sql.Date(poesia.getData().getTime()));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Problemas ao cadastrar Poesia! Erro: " + ex.getMessage());
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
        String sql = "select p.* from poesia p order by p.id descs;";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Poesia poesia = new Poesia();
                poesia.setId(rs.getInt("id"));
                poesia.setConteudo(rs.getString("conteudo"));
                poesia.setAutor(rs.getString("autor"));
                poesia.setData(rs.getDate("data"));
                resultado.add(poesia);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Poesia! Erro: " + ex.getMessage());
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
        String sql = "Delete from poesia where id=?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Poesia! Erro:" + ex.getMessage());
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
        Poesia poesia = (Poesia) object;
        PreparedStatement stmt = null;
        String sql = "update poesia set conteudo=?, autor=?, data=? where id=?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, poesia.getConteudo());
            stmt.setString(2, poesia.getAutor());
            stmt.setDate(3, new java.sql.Date(poesia.getData().getTime()));
            stmt.setInt(4, poesia.getId());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println("Problemas ao alterar Poesia! Erro:" + ex.getMessage());
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