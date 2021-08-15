/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interdisciplinar.DAO;

import br.com.interdisciplinar.model.Consulta;
import br.com.interdisciplinar.model.Pessoa;
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
public class ConsultaDAOImpl implements GenericDAO {

    private Connection conn;

    public ConsultaDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Consulta consulta = (Consulta) object;
        PreparedStatement stmt = null;
        String sql = "Insert into consulta(horario, data, id_pessoa)" + "values (?,?,?);";
        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, consulta.getHorario());
            stmt.setDate(2, new java.sql.Date(consulta.getData().getTime()));
            stmt.setInt(3, consulta.getPessoa().getIdPessoa());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Problemas ao cadastrar Consulta! Erro: " + ex.getMessage());
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
        String sql = "select c.id, c.data, c.horario, p.nome_pessoa, p.id_pessoa "
                + "from Consulta c, Pessoa p "
                + "where p.id_pessoa = c.id_pessoa";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setHorario(rs.getString("horario"));
                consulta.setData(rs.getDate("data"));
                consulta.setPessoa(new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome_pessoa")));
                resultado.add(consulta);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Consulta! Erro: " + ex.getMessage());
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
        String sql = "Delete from consulta where id=?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Consulta! Erro:" + ex.getMessage());
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
        Consulta consulta = (Consulta) object;
        PreparedStatement stmt = null;
        String sql = "update consulta set horario=?, data=?, id_pessoa=? where id=?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, consulta.getHorario());
            stmt.setDate(2, new java.sql.Date(consulta.getData().getTime()));
            stmt.setInt(3, consulta.getPessoa().getIdPessoa());
            stmt.setInt(4, consulta.getId());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println("Problemas ao alterar Consulta! Erro:" + ex.getMessage());
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
