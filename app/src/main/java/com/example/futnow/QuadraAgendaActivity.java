package com.example.futnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futnow.model.Horario;
import com.example.futnow.model.Quadra;
import com.example.futnow.view.CustomAdapterHorarios;
import com.example.futnow.view.CustomAdapterQuadras;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class QuadraAgendaActivity extends AppCompatActivity {

    TextView dataAgenda, titleAgenda;
    RecyclerView recyclerView;
    Calendar calendar;
    int dayWeek;
    int dayMonth;
    HashMap<Integer, String> dias = new HashMap<Integer, String>();
    String title = null, idQuadra = null;
    Bundle bundle = getIntent().getExtras();
    List<Horario> horarios = new ArrayList<>();
    Horario horario;
    CustomAdapterHorarios adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_agenda);

        Constructor();

        recyclerView = findViewById(R.id.recyclerViewComentarios);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        adapter = new CustomAdapterHorarios(horarios);
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
                Toast.makeText(QuadraAgendaActivity.this, "Recall", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        RecuperarDados();
    }

    private void Constructor() {
        dias.put(1, "Domingo");
        dias.put(2, "Segunda");
        dias.put(3, "Terça");
        dias.put(4, "Quarta");
        dias.put(5, "Quinta");
        dias.put(6, "Sexta");
        dias.put(7, "Sábado");

        dataAgenda = findViewById(R.id.TextViewArenaAgendaData);
        titleAgenda = findViewById(R.id.TextViewArenaAgendaTitle);
        recyclerView = findViewById(R.id.RecyclerViewHorarios);

        calendar = Calendar.getInstance();
        dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @SuppressLint("SetTextI18n")
    private void RecuperarDados() {
        if (bundle != null) {
            idQuadra = bundle.getString("idQuadra");
            title = bundle.getString("title");
        }

        titleAgenda.setText(title);
        dataAgenda.setText(dias.get(dayWeek) + ", " + dayMonth);
    }

    @Override
    public void onStart() {
        super.onStart();
        recuperaHorarios();
    }

    private void recuperaHorarios() {
        DatabaseReference databaseReference = FirebaseHelper.getDatabaseReference()
                .child("quadras")
                .child(FirebaseHelper.getIdFirebase())
                .child(idQuadra)
                .child("horarios");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                horarios.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        horario = snap.getValue(Horario.class);
                        horarios.add(horario);
                    }
                }
                Collections.reverse(horarios);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}