package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

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
        db = FirebaseFirestore.getInstance();
        entrenador = entrenadorExist(getIntent().getExtras().getString("entrenador"));
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

    public Entrenador entrenadorExist(String name){
        final Entrenador[] toReturn = {null};
        Query query = db.collection("entrenadores").whereEqualTo("nombre", name);
        query.get().addOnCompleteListener(
                task -> {

                    if(!task.getResult().isEmpty()){
                        DocumentSnapshot ds = task.getResult().getDocuments().get(0);
                        toReturn[0] = ds.toObject(Entrenador.class);
                    }
                    else{
                        Entrenador n = new Entrenador(name);
                        registrarEntrenador(n);
                        toReturn[0] = n;
                        Toast.makeText(this, "Has sido registrado, Bienvenido",Toast.LENGTH_LONG).show();
                    }
                }
        );
        return toReturn[0];
    }
}