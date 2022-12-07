package com.example.futnow.model;

import com.example.futnow.FirebaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Usuario {
    String id;
    String Nome;
    String Email;
    String Senha;
    String CPF;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public Usuario(String nome, String email, String senha, String CPF) {
        this.Nome = nome;
        this.Email = email;
        this.Senha = senha;
        this.CPF = CPF;

        this.id = auth.getUid();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        this.Senha = senha;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Nome='" + Nome + '\'' +
                ", Email='" + Email + '\'' +
                ", Senha='" + Senha + '\'' +
                ", CPF='" + CPF + '\'' +
                '}';
    }

    public void salvar() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                .child("usuarios")
                .child(this.getId());
        reference.setValue(this);
    }
}
