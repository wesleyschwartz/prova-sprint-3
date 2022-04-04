package com.sprint3.prova.modelo;

import javax.persistence.*;
import javax.validation.constraints.Min;


@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Regiao regiao;
    @Min(value = 0)
    private long populacao;

    private String capital;
    @Min(value = 1)
    private long area;

    public Estado() {
    }

    public Estado(String nome, Regiao regiao, long populacao, String capital, long area) {
        this.nome = nome;
        this.regiao = regiao;
        this.populacao = populacao;
        this.capital = capital;
        this.area = area;
    }

    public Estado(long id, String nome, Regiao regiao, long populacao, String capital, long area) {
        this.id = id;
        this.nome = nome;
        this.regiao = regiao;
        this.populacao = populacao;
        this.capital = capital;
        this.area = area;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(long populacao) {
        this.populacao = populacao;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }
}
