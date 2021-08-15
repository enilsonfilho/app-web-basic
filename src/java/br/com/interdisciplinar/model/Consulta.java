package br.com.interdisciplinar.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Enilson Filho
 */
public class Consulta {

    private Integer id;
    private Date data;
    private String horario;
    private Pessoa pessoa;

    public Consulta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public String getDataFormatada() {
        SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
        return formata.format(getData());
    }
    
}
