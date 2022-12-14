package com.example.futnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.futnow.model.Quadra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.Objects;

public class QuadraPrincipalActivity extends AppCompatActivity {

    TextView arenaTitle;
    TextView arenaTipo;
    TextView arenaValor;
    TextView arenaDescricao;
    TextView arenaCidade;
    TextView arenaEndereco;
    Button buttonUpdate;
    Button buttonVerComentarios;
    Button buttonVerAgenda;
    Button buttonMapa;

    Quadra quadra;

    Button buttonLogout;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_principal);

        Constructor();

        recuperarId();



        buttonMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuadraPrincipalActivity.this, MapsActivity.class);
                intent.putExtra("lat", quadra.getLatitude());
                intent.putExtra("log", quadra.getLongitude());

                startActivity(intent);
            }
        });

        buttonVerAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuadraPrincipalActivity.this, QuadraAgendaActivity.class);
                intent.putExtra("idQuadra", quadra.getId());
                intent.putExtra("title", quadra.getTitle());
                startActivity(intent);
            }
        });

        buttonVerComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuadraPrincipalActivity.this, QuadraComentariosActivity.class);
                intent.putExtra("idQuadra", quadra.getId());
                startActivity(intent);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(QuadraPrincipalActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( QuadraPrincipalActivity.this, QuadraUpdateActivity.class);
                intent.putExtra("idQuadra", quadra.getId());
                startActivity(intent);
            }
        });
    }

    private void Constructor() {
        arenaTitle = findViewById(R.id.TextViewArenaPrincipalTitle);
        arenaEndereco = findViewById(R.id.TextViewArenaPrincipalEndereco);
        arenaValor = findViewById(R.id.TextViewArenaPrincipalValor);
        arenaTipo = findViewById(R.id.TextViewArenaPrincipalTipo);
        arenaDescricao = findViewById(R.id.TextViewArenaPrincipalDescricao);
        arenaCidade = findViewById(R.id.TextViewArenaPrincipalCidade);
        buttonVerComentarios = findViewById(R.id.ButtonVerComentarios);
        buttonVerAgenda = findViewById(R.id.ButtonVerAgenda);
        buttonMapa = findViewById(R.id.ButtonMapa);
        buttonLogout = findViewById(R.id.ButtonLogout);

        buttonUpdate = findViewById(R.id.textViewQuadraUpdate);
    }

    public void configDados(Quadra quadra) {
        if(quadra == null) {
            Intent intent = new Intent(QuadraPrincipalActivity.this, FutnowHomepage.class);
            startActivity(intent);
        }
        arenaTitle.setText(quadra.getTitle());
        arenaCidade.setText(quadra.getCidade());
        arenaDescricao.setText(quadra.getDescricao());
        arenaEndereco.setText(quadra.getEndereco());
        arenaTipo.setText(quadra.getTipoQuadra());
        arenaValor.setText("R$ " + quadra.getValor());
    }

    public void recuperarId() {
        String id = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("quadra");
        }

        DatabaseReference databaseReference = FirebaseHelper.getDatabaseReference()
                .child("quadras")
                .child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                quadra = snapshot.getValue(Quadra.class);
                if(quadra == null) {
                    Intent intent = new Intent(QuadraPrincipalActivity.this, FutnowHomepage.class);
                    startActivity(intent);
                }
                configDados(quadra);

                if (!quadra.getIdUser().equals(auth.getUid())) {
                    buttonUpdate.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}