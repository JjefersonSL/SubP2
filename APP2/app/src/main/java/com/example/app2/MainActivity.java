package com.example.app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    DatabaseReference reference;

    EditText edNome;
    EditText edUser;
    EditText edSenha;
    EditText edData;
    ListView Lista;
    public List<String> itens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNome = findViewById(R.id.Nome);
        edUser = findViewById(R.id.User);
        edSenha = findViewById(R.id.Senha);
        edData = findViewById(R.id.Data);
        Lista = findViewById(R.id.Lista);



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

    public void CarregarListView()
    {
        reference = FirebaseDatabase.getInstance().getReference().child("Pessoa");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Pessoa a = dataSnapshot1.getValue(Pessoa.class);
                    itens.add("Nome:" + a.getNome() +", Usuário: " + a.getUser() +", Senha: " + a.getSenha() +", Data: " + a.getNasc());
                }

                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        itens
                );
                Lista.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                
            }
        }