package com.example.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Robson on 22/09/2019
 */
public class Aluno implements Serializable {

    private Long id;
    private String nome;
    private String endereço;
    private String telefone;
    private String site;
    private Double nota;
    private String caminhoFoto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }


    @NonNull
    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
