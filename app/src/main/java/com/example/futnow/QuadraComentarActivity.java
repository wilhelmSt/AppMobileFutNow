package com.example.futnow;
import java.util.ArrayList;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.futnow.model.Comentario;
import com.example.futnow.model.Quadra;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class QuadraComentarActivity extends AppCompatActivity {

    EditText titulo;
    EditText descricao;
    TextView buttonComentar;
    Comentario comentario;
    String idQuadra = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_comentar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            idQuadra = bundle.getString("idQuadra");
        }

        titulo = findViewById(R.id.EditTextTituloComentario);
        descricao = findViewById(R.id.EditTextDescricaoComentario);
        buttonComentar = findViewById(R.id.TextViewButtonComentar);

        buttonComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validaDados()) {
                    realizarComentario();
                } else {
                    Toast.makeText(QuadraComentarActivity.this, "Preencha os campos!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void realizarComentario() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                .child("comentarios")
                .child(idQuadra)
                .child(comentario.getId());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                reference.setValue(comentario);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    private boolean validaDados() {
        if (comentario == null) comentario = new Comentario();

        comentario.setTitle(titulo.getText().toString());
        comentario.setDescricao(descricao.getText().toString());
        comentario.setIdQuadra(idQuadra);

        if (comentario != null) return true;
        return false;
    }
}