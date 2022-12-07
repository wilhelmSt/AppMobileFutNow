package com.example.futnow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.futnow.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class CadastroActivity extends AppCompatActivity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    EditText textNome, textEmail, textSenha, textSenhaConfirma, textCpf;
    Button buttonCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        textNome = findViewById(R.id.EditTextCadastrarNome);
        textEmail = findViewById(R.id.EditTextCadastrarEmail);
        textSenha = findViewById(R.id.EditTextCadastrarSenha);
        textSenhaConfirma = findViewById(R.id.EditTextCadastrarSenhaConfirma);
        textCpf = findViewById(R.id.EditTextCadastrarCpf);
        buttonCadastro = findViewById(R.id.ButtonCadastrarUsuario);

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(textSenha.getText().toString().equals(textSenhaConfirma.getText().toString())) {

                    auth.createUserWithEmailAndPassword(
                            textEmail.getText().toString(),
                            textSenha.getText().toString()
                    ).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Usuario user = new Usuario(
                                    textNome.getText().toString(),
                                    textEmail.getText().toString(),
                                    textSenha.getText().toString(),
                                    textCpf.getText().toString()
                            );

                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                            reference.child("usuarios").child(user.getId()).setValue(user);

                            startActivity(new Intent(CadastroActivity.this, FutnowHomepage.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("Exception: " + e.toString());
                        }
                    });

                }
            }
        });
    }


}