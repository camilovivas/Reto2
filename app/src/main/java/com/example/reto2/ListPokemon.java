package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ListPokemon extends AppCompatActivity {
    private EditText atrapar, buscar;
    private Button buscarBtn, atraparBtn;
    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pokemon);
        atrapar = findViewById(R.id.atrapar);
        buscar = findViewById(R.id.buscar);
        buscarBtn = findViewById(R.id.buscarBtn);
        atraparBtn = findViewById(R.id.atraparBtn);
        list = findViewById(R.id.list);
    }
}