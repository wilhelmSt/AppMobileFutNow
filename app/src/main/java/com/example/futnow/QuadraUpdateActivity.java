package com.example.futnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futnow.model.Quadra;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuadraUpdateActivity extends AppCompatActivity {

    Button buttonLogout, buttonUpdate, buttonDelete;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Quadra quadra;
    EditText nomeUpdate, enderecoUpdate, valorUpdate, tipoUpdate, cidadeUpdate, descricaoUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadra_update);

        nomeUpdate = findViewById(R.id.EditTextUpdateQuadraNome);
        enderecoUpdate = findViewById(R.id.EditTextUpdateQuadraEndereco);
        valorUpdate = findViewById(R.id.EditTextUpdateQuadraValor);
        cidadeUpdate = findViewById(R.id.EditTextUpdateQuadraCidade);
        descricaoUpdate = findViewById(R.id.EditTextUpdateQuadraDescricao);
        tipoUpdate = findViewById(R.id.EditTextUpdateQuadraTipo);

        buttonUpdate = findViewById(R.id.ButtonUpdateQuadra);
        buttonLogout = findViewById(R.id.ButtonLogout);
        buttonDelete = findViewById(R.id.ButtonDeleteQuadra);

        String id = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("idQuadra");

            DatabaseReference databaseReference = FirebaseHelper.getDatabaseReference()
                    .child("quadras")
                    .child(id);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    quadra = snapshot.getValue(Quadra.class);

                    nomeUpdate.setText(quadra.getTitle());
                    enderecoUpdate.setText(quadra.getEndereco());
                    valorUpdate.setText(quadra.getValor());
                    cidadeUpdate.setText(quadra.getCidade());
                    descricaoUpdate.setText(quadra.getTipoQuadra());
                    tipoUpdate.setText(quadra.getTipoQuadra());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Quadra novaQuadra = new Quadra();
                novaQuadra.setTitle(nomeUpdate.getText().toString());
                novaQuadra.setEndereco(enderecoUpdate.getText().toString());
                novaQuadra.setTipoQuadra(tipoUpdate.getText().toString());
                novaQuadra.setDescricao(descricaoUpdate.getText().toString());
                novaQuadra.setCidade(cidadeUpdate.getText().toString());
                novaQuadra.setValor(valorUpdate.getText().toString());
                novaQuadra.setIdUser(auth.getUid());
                novaQuadra.setId(quadra.getId());
                novaQuadra.setLatitude(quadra.getLatitude());
                novaQuadra.setLongitude(quadra.getLongitude());
                novaQuadra.setHorario();

                DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                        .child("quadras")
                        .child(quadra.getId());

                reference.setValue(novaQuadra).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(
                                QuadraUpdateActivity.this,
                                "Editado com sucesso!",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(QuadraUpdateActivity.this, FutnowHomepage.class);
                        startActivity(intent);
                    }
                });
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(QuadraUpdateActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                        .child("quadras")
                        .child(quadra.getId());

                reference.removeValue().addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Toast.makeText(
                                QuadraUpdateActivity.this,
                                "Deletado com sucesso!",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(QuadraUpdateActivity.this, FutnowHomepage.class);
                        startActivity(intent);
                    }
                });


            }
        });
    }
}