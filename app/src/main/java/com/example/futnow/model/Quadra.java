package com.example.futnow.model;

import com.example.futnow.FirebaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Quadra {
    String id;
    String title;
    String descricao;
    String endereco;
    String cidade;
    String tipoQuadra;
    String valor;
    String idUser;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public Quadra() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference();
        this.setId(reference.push().getKey());
    }

    public Quadra(String title, String descricao) {
        this.title = title;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.title = descricao;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTipoQuadra() {
        return this.tipoQuadra;
    }

    public void setTipoQuadra(String tipoQuadra) {
        this.tipoQuadra = tipoQuadra;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void salvar() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                .child("quadras")
                .child(FirebaseHelper.getIdFirebase())
                .child(this.getId());
        reference.setValue(this);
    }

    @Override
    public String toString() {
        return "Quadra{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", descricao='" + descricao + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cidade='" + cidade + '\'' +
                ", tipoQuadra='" + tipoQuadra + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}