
package br.com.interdisciplinar.DAO;

import java.util.List;

public interface GenericDAO {

    public Boolean cadastrar(Object object);

    public List<Object> listar();

    public void excluir(Integer idObject);

    public Boolean alterar(Object object);

}
