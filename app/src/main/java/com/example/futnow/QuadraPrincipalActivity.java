package com.example.futnow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuadraPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_principal);
    }

    public void RedirecionarAgendaQuadra(View view) {
        startActivity(new Intent(QuadraPrincipalActivity.this, QuadraAgendaActivity.class));
    }
}