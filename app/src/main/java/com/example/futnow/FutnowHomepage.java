package com.example.futnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.futnow.model.Quadra;
import com.example.futnow.view.CustomAdapterQuadras;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FutnowHomepage extends AppCompatActivity implements CustomAdapterQuadras.Onclick {

    private Quadra quadra;
    private List<Quadra> quadrasList = new ArrayList<>();
    private CustomAdapterQuadras adapter;
    RecyclerView recyclerView;

    Button buttonLogout, buttonIrCadrastarQuadra;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futnow_homepage);

        recyclerView = findViewById(R.id.RecyclerViewQuadras);
        buttonIrCadrastarQuadra = findViewById(R.id.ButtonIrCadrastarQuadra);
        buttonLogout = findViewById(R.id.ButtonLogout);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        adapter = new CustomAdapterQuadras(quadrasList, this);
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

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(FutnowHomepage.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonIrCadrastarQuadra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FutnowHomepage.this, CadastrarQuadraActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        recuperaQuadras();

    }

    private void recuperaQuadras() {
        DatabaseReference databaseReference = FirebaseHelper.getDatabaseReference()
                .child("quadras");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                quadrasList.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        quadra = snap.getValue(Quadra.class);
                        quadrasList.add(quadra);
                    }
                }
                Collections.reverse(quadrasList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClickListener(Quadra quadra) {
        Intent intent = new Intent(this, QuadraPrincipalActivity.class);
        intent.putExtra("quadra", quadra.getId());
        startActivity(intent);
    }

}