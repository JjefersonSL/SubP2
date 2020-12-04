package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    DatabaseReference reference;

    EditText edNome;
    EditText edUser;
    EditText edSenha;
    EditText edData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNome = findViewById(R.id.Nome);
        edUser = findViewById(R.id.User);
        edSenha = findViewById(R.id.Senha);
        edData = findViewById(R.id.Data);

        IniciarFirebase();

    }

    private void IniciarFirebase()
    {
        db = FirebaseDatabase.getInstance();
        db.setPersistenceEnabled(true);
        FirebaseApp.initializeApp(this);
        reference = db.getReference();
    }
    public void CadastrarPessoa(View view)
    {
        Pessoa pes = new Pessoa();
        pes.setSenha(Integer.parseInt(edSenha.getText().toString()));
        pes.setNome(edNome.getText().toString());
        pes.setUser(edUser.getText().toString());
        pes.setNasc((edData.getText().toString()));
        reference.child("Pessoa").child(pes.getNome()).setValue(pes);
        Toast.makeText(getApplicationContext(),"Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
    }
}