package com.example.futnow.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.futnow.FirebaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Quadra {
    String id;
    String title;
    String descricao;
    String endereco;
    String cidade;
    String tipoQuadra;
    String Latitude;
    String Longitude;
    String valor;
    String idUser;
    Horario horario;

    HashMap<String, Boolean> horarios = new HashMap<String, Boolean>();
    ArrayList<Horario> horariosList = new ArrayList<Horario>();

//    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public Quadra() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference();
        this.setId(reference.push().getKey());
    }

    public Quadra(String title, String descricao) {
        this.title = title;
        this.descricao = descricao;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
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

    public void setHorario() {
        this.horario = new Horario();
        System.out.println("meu horario: " + horario);
    }

    public void salvar() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                .child("quadras")
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
