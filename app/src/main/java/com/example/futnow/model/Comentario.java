package com.example.futnow.model;

public class Comentario {
    static int geradorIds = 0;
    int id;
    String title;
    String descricao;

    public Comentario(String title, String descricao) {
        this.title = title;
        this.descricao = descricao;

        this.id = geradorIds++;
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

    @Override
    public String toString() {
        return "Titulo: " + title;
    }
}
