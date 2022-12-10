package com.example.futnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;

public class QuadraAgendaActivity extends AppCompatActivity {

    TextView dataAgenda, titleAgenda;
    RecyclerView recyclerView;
    Calendar calendar;
    int dayWeek;
    int dayMonth;
    HashMap<Integer, String> dias = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_agenda);

        Constructor();

        RecuperarDados();
    }

    private void Constructor() {
        dias.put(0, "Segunda");
        dias.put(1, "Terça");
        dias.put(2, "Quarta");
        dias.put(3, "Quinta");
        dias.put(4, "Sexta");
        dias.put(5, "Sábado");
        dias.put(6, "Domingo");

        dataAgenda = findViewById(R.id.TextViewArenaAgendaData);
        titleAgenda = findViewById(R.id.TextViewArenaAgendaTitle);
        recyclerView = findViewById(R.id.RecyclerViewHorarios);

        calendar = Calendar.getInstance();
        dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @SuppressLint("SetTextI18n")
    private void RecuperarDados() {
        String title = null, id = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("idQuadra");
            title = bundle.getString("title");
        }

        titleAgenda.setText(title);
        dataAgenda.setText(dias.get(dayWeek) + ", " + dayMonth);
    }
}