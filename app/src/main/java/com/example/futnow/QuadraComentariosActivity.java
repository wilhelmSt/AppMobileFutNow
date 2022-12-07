package com.example.futnow;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import com.example.futnow.model.Comentario;
import com.example.futnow.view.CustomAdapterComentarios;

public class QuadraComentariosActivity extends AppCompatActivity {

    ArrayList<Comentario> lista;
//    ListView listViewComentarios;
    CustomAdapterComentarios adapter;
    RecyclerView recyclerViewContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_comentarios);

        lista = new ArrayList<Comentario>();

        adapter = new CustomAdapterComentarios( lista );

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewContatos.setLayoutManager( layoutManager );
        recyclerViewContatos.setAdapter( adapter );
        recyclerViewContatos.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}