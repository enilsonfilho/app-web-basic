/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interdisciplinar.DAO;

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
public class PessoaDAOImpl implements GenericDAO {

    private Connection conn;

    public PessoaDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Pessoa pessoa = (Pessoa) object;
        PreparedStatement stmt = null;
        String sql = "Insert into pessoa (nome_pessoa, telefone_pessoa, celular_pessoa, "
                + "login_pessoa, senha_pessoa, tipo_pessoa)" + "values (?,?,?,?,?,?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNomePessoa());
            stmt.setString(2, pessoa.getTelefonePessoa());
            stmt.setString(3, pessoa.getCelularPessoa());
            stmt.setString(4, pessoa.getLoginPessoa());
            stmt.setString(5, pessoa.getSenhaPessoa());
            stmt.setString(6, pessoa.getTipoPessoa().getTipo());
  
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar usuário! Error: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar connection! Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "select p.id_pessoa, p.nome_pessoa, p.telefone_pessoa, p.celular_pessoa, p.login_pessoa, p.senha_pessoa, p.tipo_pessoa \n"
                + "from pessoa p\n"
                + "order by p.nome_pessoa;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("id_pessoa"));
                pessoa.setNomePessoa(rs.getString("nome_pessoa"));
                pessoa.setTelefonePessoa(rs.getString("telefone_pessoa"));
                pessoa.setCelularPessoa(rs.getString("celular_pessoa"));
                pessoa.setTipoPessoa(Pessoa.Tipo.parse(rs.getString("tipo_pessoa")));
                pessoa.setLoginPessoa(rs.getString("login_pessoa"));
                pessoa.setSenhaPessoa(rs.getString("senha_pessoa"));
                resultado.add(pessoa);
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao listar pessoas! Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fazer conexão! Erro" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;

    }
    
    public List<Object> listarCliente() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "select p.id_pessoa, p.tipo_pessoa ,p.nome_pessoa, p.telefone_pessoa, p.celular_pessoa, p.login_pessoa, p.senha_pessoa, p.tipo_pessoa \n"
                + "from pessoa p "
                + "where p.tipo_pessoa = 'U' "
                + "order by p.nome_pessoa;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("id_pessoa"));
                pessoa.setNomePessoa(rs.getString("nome_pessoa"));
                pessoa.setTelefonePessoa(rs.getString("telefone_pessoa"));
                pessoa.setCelularPessoa(rs.getString("celular_pessoa"));
                pessoa.setTipoPessoa(Pessoa.Tipo.parse(rs.getString("tipo_pessoa")));
                pessoa.setLoginPessoa(rs.getString("login_pessoa"));
                pessoa.setSenhaPessoa(rs.getString("senha_pessoa"));
                resultado.add(pessoa);
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao listar pessoas! Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fazer conexão! Erro" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;

    }

    public List<Object> listarUsuarios() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "select p.id_pessoa, p.nome_pessoa, p.telefone_pessoa, p.celular_pessoa, p.login_pessoa, p.senha_pessoa, p.tipo_pessoa \n"
                + "from pessoa p\n"
                + "where tipo_pessoa = 'U'\n"
                + "order by p.nome_pessoa;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("id_pessoa"));
                pessoa.setNomePessoa(rs.getString("nome_pessoa"));
                pessoa.setTelefonePessoa(rs.getString("telefone_pessoa"));
                pessoa.setCelularPessoa(rs.getString("celular_pessoa"));
                pessoa.setTipoPessoa(Pessoa.Tipo.parse(rs.getString("tipo_pessoa")));
                pessoa.setLoginPessoa(rs.getString("login_pessoa"));
                pessoa.setSenhaPessoa(rs.getString("senha_pessoa"));
                resultado.add(pessoa);
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao listar usuários! Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fazer conexão! Erro" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    public List<Object> listarAdministradores() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "select p.id_pessoa, p.nome_pessoa, p.telefone_pessoa, p.celular_pessoa, p.login_pessoa, p.senha_pessoa, p.tipo_pessoa \n"
                + "from pessoa p\n"
                + "where tipo_pessoa = 'A'\n"
                + "order by p.nome_pessoa;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("id_pessoa"));
                pessoa.setNomePessoa(rs.getString("nome_pessoa"));
                pessoa.setTelefonePessoa(rs.getString("telefone_pessoa"));
                pessoa.setCelularPessoa(rs.getString("celular_pessoa"));
                pessoa.setTipoPessoa(Pessoa.Tipo.parse(rs.getString("tipo_pessoa")));
                pessoa.setLoginPessoa(rs.getString("login_pessoa"));
                pessoa.setSenhaPessoa(rs.getString("senha_pessoa"));
                resultado.add(pessoa);
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao listar administradores! Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fazer conexão! Erro" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public void excluir(Integer idObject) {
        PreparedStatement stmt = null;
        String sql = "Delete from pessoa where id_pessoa=?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Colaborador! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public Object carregar(int idObject) {
        Pessoa pessoa = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select p.id_pessoa, p.nome_pessoa, p.telefone_pessoa, p.celular_pessoa, p.login_pessoa, p.senha_pessoa, p.tipo_pessoa \n"
                + "from pessoa p\n"
                + "where p.id_pessoa = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            if (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("id_pessoa"));
                pessoa.setNomePessoa(rs.getString("nome_pessoa"));
                pessoa.setTelefonePessoa(rs.getString("telefone_pessoa"));
                pessoa.setCelularPessoa(rs.getString("celular_pessoa"));
                pessoa.setTipoPessoa(Pessoa.Tipo.parse(rs.getString("tipo_pessoa")));
                pessoa.setLoginPessoa(rs.getString("login_pessoa"));
                pessoa.setSenhaPessoa(rs.getString("senha_pessoa"));
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar usuários! Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Ocorreu problemas ao tentar fazer conexão com o banco de dados! Erro" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pessoa;
    }

    @Override
    public Boolean alterar(Object object) {
        Pessoa pessoa = (Pessoa) object;
        PreparedStatement stmt = null;
        String sql = "update pessoa set nome_pessoa=?, telefone_pessoa=?, celular_pessoa=?, "
                + "login_pessoa=?, senha_pessoa=?, tipo_pessoa=? where id_pessoa=?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNomePessoa());
            stmt.setString(2, pessoa.getTelefonePessoa());
            stmt.setString(3, pessoa.getCelularPessoa());
            stmt.setString(4, pessoa.getLoginPessoa());
            stmt.setString(5, pessoa.getSenhaPessoa());
            stmt.setString(6, pessoa.getTipoPessoa() != null ? pessoa.getTipoPessoa().getTipo() : "U");
            stmt.setInt(7, pessoa.getIdPessoa());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println("Problemas ao alterar usuários!Erro:" + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.err.println("Problemas ao fechar conexão!ERRO:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public Pessoa logarPessoa(String login, String senha) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pessoa pessoa = null;

        String sql = "select p.id_pessoa, p.nome_pessoa, p.telefone_pessoa, p.celular_pessoa, p.login_pessoa, p.senha_pessoa, p.tipo_pessoa \n"
                + "from pessoa p\n"
                + "where p.login_pessoa = ?\n"
                + "and p.senha_pessoa = ?\n"
                + "order by p.nome_pessoa;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("id_pessoa"));
                pessoa.setNomePessoa(rs.getString("nome_pessoa"));
                pessoa.setTelefonePessoa(rs.getString("telefone_pessoa"));
                pessoa.setCelularPessoa(rs.getString("celular_pessoa"));
                pessoa.setTipoPessoa(Pessoa.Tipo.parse(rs.getString("tipo_pessoa")));
                pessoa.setLoginPessoa(rs.getString("login_pessoa"));
                pessoa.setSenhaPessoa(rs.getString("senha_pessoa"));
            }

        } catch (SQLException ex) {
            System.out.println("Problemas ao logar pessoa! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pessoa;
    }
}
