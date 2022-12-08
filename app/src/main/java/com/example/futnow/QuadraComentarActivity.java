package com.example.futnow;
import java.util.ArrayList;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_comentar);

        titulo = findViewById(R.id.EditTextTituloComentario);
        descricao = findViewById(R.id.EditTextDescricaoComentario);
        buttonComentar = findViewById(R.id.TextViewButtonComentar);

        buttonComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarComentario();
            }
        });


    }

    public void realizarComentario() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                .child("usuarios")
                .child(FirebaseHelper.getIdFirebase());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
//                validaDados();

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

//    private void validaDados() {
//        if (comentario == null) comentario = new Comentario();
//        comentario.setIdUser(FirebaseHelper.getIdFirebase());
//
//        comentario.setTitle(nome.getText().toString());
//        comentario.setEndereco(endereco.getText().toString());
//        comentario.setCidade(cidade.getText().toString());
//        comentario.setTipoQuadra(tipo.getText().toString());
//        comentario.setValor(valor.getText().toString());
//        comentario.setDescricao(descricao.getText().toString());
//
//        comentario.salvar();
//    }
}