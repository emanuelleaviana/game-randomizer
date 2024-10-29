package entidades;

import java.util.Date;

public class Jogo {
    private Integer id;
    private String nomeJogador;
    private Integer numeroAposta;
    private Integer numeroSecreto;
    private String resultado;
    private Date data;

    public Jogo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public Integer getNumeroAposta() {
        return numeroAposta;
    }

    public void setNumeroAposta(Integer numeroAposta) {
        this.numeroAposta = numeroAposta;
    }

    public Integer getNumeroSecreto() {
        return numeroSecreto;
    }

    public void setNumeroSecreto(Integer numeroSecreto) {
        this.numeroSecreto = numeroSecreto;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}