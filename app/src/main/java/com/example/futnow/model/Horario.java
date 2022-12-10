package com.example.futnow.model;

import com.example.futnow.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class Horario {

    String id;
    String idQuadra;
    ArrayList<String> horarios = new ArrayList<>();

    public Horario() {
        horarios.add("07:00 AM");
        horarios.add("08:00 AM");
        horarios.add("09:00 AM");
        horarios.add("10:00 AM");
        horarios.add("11:00 AM");
        horarios.add("12:00 PM");
        horarios.add("13:00 PM");
        horarios.add("14:00 PM");
        horarios.add("15:00 PM");
        horarios.add("16:00 PM");
        horarios.add("17:00 PM");
        horarios.add("18:00 PM");
        horarios.add("19:00 PM");
        horarios.add("20:00 PM");
        horarios.add("21:00 PM");
        horarios.add("22:00 PM");


        DatabaseReference reference = FirebaseHelper.getDatabaseReference();
        this.setId(reference.push().getKey());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getHorarios() {
        return horarios;
    }

    public String getIdQuadra() {
        return idQuadra;
    }

    public void setIdQuadra(String idQuadra) {
        this.idQuadra = idQuadra;
    }
}
