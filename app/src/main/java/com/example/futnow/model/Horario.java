package com.example.futnow.model;

import com.example.futnow.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;


public class Horario {

    String id, idQuadra;
    String title, descricao;
    HashMap<String, Boolean> horarios = new HashMap<String, Boolean>();

    public Horario() {
        horarios.put("07:00 AM", false);
        horarios.put("08:00 AM", false);
        horarios.put("09:00 AM", false);
        horarios.put("10:00 AM", false);
        horarios.put("11:00 AM", false);
        horarios.put("12:00 PM", false);
        horarios.put("13:00 PM", false);
        horarios.put("14:00 PM", false);
        horarios.put("15:00 PM", false);
        horarios.put("16:00 PM", false);
        horarios.put("17:00 PM", false);
        horarios.put("18:00 PM", false);
        horarios.put("19:00 PM", false);
        horarios.put("20:00 PM", false);
        horarios.put("21:00 PM", false);
        horarios.put("22:00 PM", false);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Boolean> getHorarios() {
        return horarios;
    }

    public String getIdQuadra() {
        return idQuadra;
    }

    public void setIdQuadra(String idQuadra) {
        this.idQuadra = idQuadra;
    }
}
