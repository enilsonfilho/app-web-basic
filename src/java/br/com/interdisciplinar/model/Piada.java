package br.com.interdisciplinar.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Enilson Filho
 */
public class Piada {

    private Integer id;
    private String conteudo;
    private String autor;
    private Date data;

    public Piada() {
    }

    public Piada(Integer id, String conteudo, String autor, Date data) {
        this.id = id;
        this.conteudo = conteudo;
        this.autor = autor;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDataFormatada() {
        SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
        return formata.format(getData());
    }

}
