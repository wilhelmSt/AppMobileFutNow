package com.example.futnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.futnow.model.Quadra;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class QuadraPrincipalActivity extends AppCompatActivity {

    TextView arenaTitle;
    TextView arenaTipo;
    TextView arenaValor;
    TextView arenaDescricao;
    TextView arenaCidade;
    TextView arenaEndereco;
    Button buttonComentarios;
    Button buttonAgenda;
    Button buttonMapa;

    Quadra quadra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_principal);

        Constructor();

        recuperarId();

    }

    private void Constructor() {
        arenaTitle = findViewById(R.id.TextViewArenaPrincipalTitle);
        arenaEndereco = findViewById(R.id.TextViewArenaPrincipalEndereco);
        arenaValor = findViewById(R.id.TextViewArenaPrincipalValor);
        arenaTipo = findViewById(R.id.TextViewArenaPrincipalTipo);
        arenaDescricao = findViewById(R.id.TextViewArenaPrincipalDescricao);
        arenaCidade = findViewById(R.id.TextViewArenaPrincipalCidade);
        buttonComentarios = findViewById(R.id.ButtonComentarios);
        buttonAgenda = findViewById(R.id.ButtonAgenda);
        buttonMapa = findViewById(R.id.ButtonMapa);
    }

    public void configDados(Quadra quadra) {
        arenaTitle.setText(quadra.getTitle());
        arenaCidade.setText(quadra.getCidade());
        arenaDescricao.setText(quadra.getDescricao());
        arenaEndereco.setText(quadra.getEndereco());
        arenaTipo.setText(quadra.getTipoQuadra());
        arenaValor.setText(quadra.getValor());
    }

    public void recuperarId() {
        String id = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("quadra");
        }

        System.out.println("ID" + id);

        DatabaseReference databaseReference = FirebaseHelper.getDatabaseReference()
                .child("quadras")
                .child(FirebaseHelper.getIdFirebase())
                .child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                quadra = snapshot.getValue(Quadra.class);
                System.out.println("Quadra: " + quadra);
                configDados(quadra);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}