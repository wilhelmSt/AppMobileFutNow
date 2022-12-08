package com.example.futnow;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.futnow.model.Comentario;
import com.example.futnow.model.Quadra;
import com.example.futnow.view.CustomAdapterComentarios;
import com.example.futnow.view.CustomAdapterQuadras;

public class QuadraComentariosActivity extends AppCompatActivity {

    List<Comentario> comentarioList = new ArrayList<>();
    CustomAdapterComentarios adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_comentarios);

        recyclerView = findViewById(R.id.recycleViewComentarios);

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
    }
}