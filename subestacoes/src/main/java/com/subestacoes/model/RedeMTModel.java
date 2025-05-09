package com.subestacoes.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tb_rede_mt")
public class RedeMTModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REDE_MT")
    private Long id;

    @Column(name = "CODIGO", length = 5)
    private String codigo;

    @Column(name = "NOME", length = 100)
    private String nome;

    @Column(name = "TENSAO_NOMINAL")
    private Double tensaoNominal;

    @ManyToOne
    @JoinColumn(name = "ID_SUBESTACAO")
    @JsonBackReference
    private SubestacaoModel subestacao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getTensaoNominal() {
        return tensaoNominal;
    }

    public void setTensaoNominal(Double tensaoNominal) {
        this.tensaoNominal = tensaoNominal;
    }

    public SubestacaoModel getSubestacao() {
        return subestacao;
    }

    public void setSubestacao(SubestacaoModel subestacao) {
        this.subestacao = subestacao;
    }
}
