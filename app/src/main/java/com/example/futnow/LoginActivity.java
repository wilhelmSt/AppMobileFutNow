package com.example.futnow;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail;
    EditText loginSenha;
    TextView buttonIrCadastro1;
    TextView buttonIrCadastro2;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.EditTextLoginEmail);
        loginSenha = findViewById(R.id.EditTextLoginSenha);
        loginButton = findViewById(R.id.ButtonLogin);

        buttonIrCadastro1 = findViewById(R.id.TextViewButtonCadastraActivity1);
        buttonIrCadastro2 = findViewById(R.id.TextViewButtonCadastraActivity2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference();
        userReference.child("usuarios").child(FirebaseHelper.getAuth().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Intent intent = new Intent(LoginActivity.this, FutnowHomepage.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void validaDados(View view) {

        String email = loginEmail.getText().toString();
        String senha = loginSenha.getText().toString();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {

                logar(email, senha);

            } else {
                loginSenha.requestFocus();
                loginSenha.setError("Informe sua senha.");
            }

        } else {
            loginEmail.requestFocus();
            loginEmail.setError("Informe seu email.");
        }

    }

    private void logar(String email, String senha) {
        FirebaseHelper.getAuth().signInWithEmailAndPassword(
                email, senha).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                startActivity(new Intent(this, FutnowHomepage.class));
            } else {
                String error = FirebaseHelper.validaErros(task.getException().getMessage()) ;
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }


        });
    }

    public void RedirecionarCadastro(View view) {
        startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
    }
}