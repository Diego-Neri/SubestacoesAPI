package com.subestacoes.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tb_subestacao")
public class SubestacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID_SUBESTACAO")
    private Long id;

    @Column(name = "CODIGO", length = 3)
    private String codigo;

    @Column(name = "NOME", length = 100)
    private String nome;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;


    @OneToMany(mappedBy = "subestacao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RedeMTModel> redes;

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<RedeMTModel> getRedes() {
        return redes;
    }


    public void setRedes(List<RedeMTModel> redes) {
        this.redes = redes;
    }
}
