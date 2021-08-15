package br.com.interdisciplinar.model;

public class Pessoa {

    private Integer idPessoa;
    private String nomePessoa;
    private String telefonePessoa;
    private String celularPessoa;
    private String crmPessoa;
    private String loginPessoa;
    private String senhaPessoa;
    private Tipo tipoPessoa;

    public boolean isAdm() {
        return getTipoPessoa() == Tipo.MEDICO;
    }

    public boolean isUsu() {
        return getTipoPessoa() == Tipo.CLIENTE;
    }

    public Pessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Pessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public Pessoa(Integer idPessoa, String nomePessoa) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
    }

    public Pessoa() {
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getTelefonePessoa() {
        return telefonePessoa;
    }

    public void setTelefonePessoa(String telefonePessoa) {
        this.telefonePessoa = telefonePessoa;
    }

    public String getCelularPessoa() {
        return celularPessoa;
    }

    public void setCelularPessoa(String celularPessoa) {
        this.celularPessoa = celularPessoa;
    }

    public String getCrmPessoa() {
        return crmPessoa;
    }

    public void setCrmPessoa(String crmPessoa) {
        this.crmPessoa = crmPessoa;
    }

    public String getLoginPessoa() {
        return loginPessoa;
    }

    public void setLoginPessoa(String loginPessoa) {
        this.loginPessoa = loginPessoa;
    }

    public String getSenhaPessoa() {
        return senhaPessoa;
    }

    public void setSenhaPessoa(String senhaPessoa) {
        this.senhaPessoa = senhaPessoa;
    }

    public Tipo getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Tipo tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public enum Tipo {

        CLIENTE("U"),
        MEDICO("A");

        private final String tipo;

        private Tipo(String tipo) {
            this.tipo = tipo;
        }

        public String getTipo() {
            return tipo;
        }

        public static Tipo parse(String param) {
            for (Tipo value : Tipo.values()) {
                if (value.getTipo().equals(param)) {
                    return value;
                }
            }
            return null;
        }
    }
}
