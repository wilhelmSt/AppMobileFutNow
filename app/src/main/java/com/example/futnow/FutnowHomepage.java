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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.futnow.model.Quadra;
import com.example.futnow.view.CustomAdapterQuadras;

import java.util.ArrayList;
import java.util.List;

public class FutnowHomepage extends AppCompatActivity {

    private List<Quadra> quadraList = new ArrayList<>();
    private CustomAdapterQuadras adapter;
    RecyclerView recyclerView;
    LinearLayout linearLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futnow_homepage);

        Quadra quadra = new Quadra("Titulo", "Descricao");

        quadraList.add(quadra);
        recyclerView = findViewById(R.id.RecyclerViewQuadras);
        linearLayout = findViewById(R.id.LayoutEachQuadra);

        System.out.println("Quadras: "+ quadraList);

        adapter = new CustomAdapterQuadras((ArrayList<Quadra>) quadraList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
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
                Toast.makeText(FutnowHomepage.this, "Recall", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });



//        recyclerView = findViewById(R.id.RecyclerViewQuadras);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
    }

    public void RedirecionarQuadra(View view) {
        startActivity(new Intent(FutnowHomepage.this, QuadraPrincipalActivity.class));
    }

    public void RedirecionarCadastroQuadra(View view) {
        startActivity(new Intent(FutnowHomepage.this, CadastrarQuadraActivity.class));
    }
}