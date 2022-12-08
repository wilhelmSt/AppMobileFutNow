package com.example.futnow;

import androidx.annotation.NonNull;

import com.example.futnow.model.Quadra;
import com.example.futnow.view.CustomAdapterQuadras;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FirebaseHelper {


    private static FirebaseAuth auth;
    private static DatabaseReference databaseReference;

    public static String getIdFirebase(){
        return getAuth().getUid();
    }

    public static DatabaseReference getDatabaseReference(){
        if(databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return databaseReference;
    }

    public static FirebaseAuth getAuth(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    public static String validaErros(String erro){
        String mensagem = "";

        if(erro.contains("There is no user record corresponding to this identifier")){
            mensagem = "Nenhuma conta encontrada com este e-mail.";
        }else if(erro.contains("The email address is badly formatted")){
            mensagem = "Insira um e-mail válido.";
        }else if(erro.contains("The password is invalid or the user does not have a password")){
            mensagem = "Senha inválida, tente novamente.";
        }else if(erro.contains("The email address is already in use by another account")){
            mensagem = "Este e-mail já está em uso.";
        }else if(erro.contains("Password should be at least 6 characters")){
            mensagem = "Insira uma senha mais forte.";
        }

        return mensagem;
    }

    public static boolean getAutenticado(){
        return getAuth().getCurrentUser() != null;
    }

}
