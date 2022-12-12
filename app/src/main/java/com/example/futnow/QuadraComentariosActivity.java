package com.example.futnow;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.futnow.model.Comentario;
import com.example.futnow.model.Quadra;
import com.example.futnow.view.CustomAdapterComentarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class QuadraComentariosActivity extends AppCompatActivity {

    private Comentario comentario;
    private List<Comentario> comentarioList = new ArrayList<>();
    private CustomAdapterComentarios adapter;
    RecyclerView recyclerView;
    TextView fazerComentario;
    String idQuadra = "";

    Button buttonLogout;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_comentarios);

        Bundle bundle = getIntent().getExtras();

        fazerComentario = findViewById(R.id.TextViewButtonFazerComentario);
        recyclerView = findViewById(R.id.recyclerViewComentarios);
        buttonLogout = findViewById(R.id.ButtonLogout);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        adapter = new CustomAdapterComentarios(comentarioList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                Toast.makeText(QuadraComentariosActivity.this, "Recall", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        fazerComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bundle != null) {
                    idQuadra = bundle.getString("idQuadra");
                }
                Intent intent = new Intent(QuadraComentariosActivity.this, QuadraComentarActivity.class);
                intent.putExtra("idQuadra", idQuadra);
                startActivity(intent);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(QuadraComentariosActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        recuperaComentarios();

    }

    private void recuperaComentarios() {
        DatabaseReference databaseReference = FirebaseHelper.getDatabaseReference()
                .child("comentarios")
                .child(idQuadra);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comentarioList.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        comentario = snap.getValue(Comentario.class);
                        comentarioList.add(comentario);
                    }
                }
                Collections.reverse(comentarioList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}