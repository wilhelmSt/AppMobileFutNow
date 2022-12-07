package com.example.futnow;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.futnow.model.Quadra;
import com.example.futnow.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CadastrarQuadraActivity extends AppCompatActivity {

    Button button;
    EditText nome;
    EditText endereco;
    EditText cidade;
    EditText tipo;
    EditText valor;
    EditText descricao;
    Usuario usuario;
    Quadra quadra;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_quadra);

        Constructor();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperaDadosUsuario();
            }
        });
    }

    private void recuperaDadosUsuario() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                .child("usuarios")
                .child(FirebaseHelper.getIdFirebase());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                validaDados();

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    private void validaDados() {
        if (quadra == null) quadra = new Quadra();
        quadra.setIdUser(FirebaseHelper.getIdFirebase());

        quadra.setTitle(nome.getText().toString());
        quadra.setEndereco(endereco.getText().toString());
        quadra.setCidade(cidade.getText().toString());
        quadra.setTipoQuadra(tipo.getText().toString());
        quadra.setValor(valor.getText().toString());
        quadra.setDescricao(descricao.getText().toString());

        quadra.salvar();
    }

    private void Constructor() {
        button = findViewById(R.id.ButtonCadastrarQuadra);
        nome = findViewById(R.id.EditTextCadastrarQuadraNome);
        endereco = findViewById(R.id.EditTextCadastrarQuadraEndereco);
        cidade = findViewById(R.id.EditTextCadastrarQuadraCidade);
        tipo = findViewById(R.id.EditTextCadastrarQuadraTipo);
        valor = findViewById(R.id.EditTextCadastrarQuadraValor);
        descricao = findViewById(R.id.EditTextCadastrarQuadraDescricao);
    }
}