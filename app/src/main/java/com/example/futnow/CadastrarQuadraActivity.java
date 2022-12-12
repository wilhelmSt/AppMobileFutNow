package com.example.futnow;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Toast;

import com.example.futnow.helper.Permission;
import com.example.futnow.model.Quadra;
import com.example.futnow.model.Usuario;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
// import com.google.android.gms.location.FusedLocationProviderClient;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CadastrarQuadraActivity extends AppCompatActivity {

    Button button;
    String lat;
    String log;
    EditText nome;
    EditText endereco;
    EditText cidade;
    EditText tipo;
    EditText valor;
    EditText descricao;
    Button buttonMap;
    Usuario usuario;
    Quadra quadra;
    FusedLocationProviderClient fusedLocationProviderClient;
    private String[] necessaryPermissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    Button buttonLogout;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_quadra);

        Constructor();

        // pedindo permissions para o android
        Permission.validatePermissions(necessaryPermissions, this, 1);

        fusedLocationProviderClient = new FusedLocationProviderClient(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperaDadosUsuario();
                Intent intent = new Intent(CadastrarQuadraActivity.this, FutnowHomepage.class);
                startActivity(intent);
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(CadastrarQuadraActivity.this, LoginActivity.class);
                startActivity(intent);
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
        quadra.setLatitude(lat);
        quadra.setLongitude(log);
        quadra.setHorario();

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
        buttonMap = findViewById(R.id.ButtonGetLocation);
        buttonLogout = findViewById(R.id.ButtonLogout);
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(location -> {

                        // ativar localização é necessário
                        if (location != null) {
                            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                            List<Address> addresses;
                            try {
                                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                lat = Double.toString(addresses.get(0).getLatitude());
                                log = Double.toString(addresses.get(0).getLongitude());

                                Toast.makeText(this, "localização encontrada!", Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText(this, "Não foi possível obter a localização!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}