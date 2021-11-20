package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import model.Entrenador;

public class ListPokemon extends AppCompatActivity implements View.OnClickListener {
    //elementos ui
    private EditText atrapar, buscar;
    private Button buscarBtn, atraparBtn;
    private RecyclerView list;
    //modelo
    private Entrenador entrenador;
    //firebase
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pokemon);
        entrenador = new Entrenador(getIntent().getExtras().getString("entrenador"));
        db = FirebaseFirestore.getInstance();
        atrapar = findViewById(R.id.atrapar);
        buscar = findViewById(R.id.buscar);
        buscarBtn = findViewById(R.id.buscarBtn);
        atraparBtn = findViewById(R.id.atraparBtn);
        list = findViewById(R.id.list);
        buscarBtn.setOnClickListener(this);
        atraparBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buscarBtn:


                break;
            case R.id.atraparBtn:


                break;
        }
    }

    public void registrarEntrenador(Entrenador entrenador) {
        db.collection("entrenadores").document(entrenador.getNombre()).set(entrenador);

    }
}