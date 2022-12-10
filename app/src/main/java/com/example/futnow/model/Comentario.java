package com.example.futnow.model;

import com.example.futnow.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;

public class Comentario {
    String id;
    String title;
    String descricao;
    String idQuadra;

    public Comentario() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference();
        this.setId(reference.push().getKey());
    }

    public Comentario(String title, String descricao) {
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
        this.descricao = descricao;
    }

    public String getIdQuadra() {
        return idQuadra;
    }

    public void setIdQuadra(String idQuadra) {
        this.idQuadra = idQuadra;
    }

    @Override
    public String toString() {
        return "Titulo: " + title;
    }
}
