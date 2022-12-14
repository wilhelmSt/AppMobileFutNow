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

import com.example.futnow.model.Horario;
import com.example.futnow.model.Quadra;
import com.example.futnow.view.CustomAdapterHorarios;
import com.example.futnow.view.CustomAdapterQuadras;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class QuadraAgendaActivity extends AppCompatActivity implements CustomAdapterHorarios.Onclick {

    Calendar calendar;
    int dayWeek;
    int dayMonth;
    HashMap<Integer, String> dias = new HashMap<Integer, String>();

    TextView dataAgenda, titleAgenda;
    String title = null, idQuadra = null;
    Bundle bundle;

    private Horario horario;
    private List<String> horarios = new ArrayList<>();
    private CustomAdapterHorarios adapter;
    RecyclerView recyclerView;

    Button buttonLogout;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    HashMap<String, Boolean> horariosMap = new HashMap<String, Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_agenda);

        bundle = getIntent().getExtras();
        Constructor();
        RecuperarDados();

        recyclerView = findViewById(R.id.RecyclerViewHorarios);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        adapter = new CustomAdapterHorarios(horarios, this);
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

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(QuadraAgendaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
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
        buttonLogout = findViewById(R.id.ButtonLogout);

        calendar = Calendar.getInstance();
        dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayMonth = calendar.get(Calendar.DAY_OF_MONTH);

        if (bundle != null) {
            bundle = getIntent().getExtras();
            System.out.println(bundle);
        }

        this.horariosMap.put("07:00 AM", false);
        this.horariosMap.put("08:00 AM", false);
        this.horariosMap.put("09:00 AM", false);
        this.horariosMap.put("10:00 AM", false);
        this.horariosMap.put("11:00 AM", false);
        this.horariosMap.put("12:00 PM", false);
        this.horariosMap.put("13:00 PM", false);
        this.horariosMap.put("14:00 PM", false);
        this.horariosMap.put("15:00 PM", false);
        this.horariosMap.put("16:00 PM", false);
        this.horariosMap.put("17:00 PM", false);
        this.horariosMap.put("18:00 PM", false);
        this.horariosMap.put("19:00 PM", false);
        this.horariosMap.put("20:00 PM", false);
        this.horariosMap.put("21:00 PM", false);
        this.horariosMap.put("22:00 PM", false);

        for (String i : horariosMap.keySet()) {
            horarios.add(i);
        }

        System.out.println("horarios: " + horarios);
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
    }


    @Override
    public void onClickListener(String hora) {

    }
}