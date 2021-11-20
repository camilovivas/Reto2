package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private Button ingresarBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        ingresarBtn = findViewById(R.id.ingresarBtn);
        ingresarBtn.setOnClickListener((v) -> {
            Intent intent = new Intent(this, ListPokemon.class);
            intent.putExtra("entrenador", username.getText().toString());
            startActivity(intent);
        });
    }


}
