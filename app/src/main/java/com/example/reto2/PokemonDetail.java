package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import model.Entrenador;
import model.Pokemon;

public class PokemonDetail extends AppCompatActivity {

    private Pokemon pokemon;
    private Entrenador entrenador;

    private Button soltar;
    private TextView name, defensa, vida, ataque, velocidad;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        soltar = findViewById(R.id.soltarBtn);
        name = findViewById(R.id.pokename);
        defensa = findViewById(R.id.vdefensaTxt);
        vida = findViewById(R.id.vidaTxt);
        ataque = findViewById(R.id.ataqueTxt);
        velocidad = findViewById(R.id.velocidadTxt);
        imageView = findViewById(R.id.imageView);

        pokemon = (Pokemon) getIntent().getExtras().get("pokemon");
        entrenador = (Entrenador) getIntent().getExtras().get("trainer");

        init();
        soltar.setOnClickListener((v)->deletePokemon());

    }

    public void init(){
        name.setText(pokemon.getNombre());
        defensa.setText(pokemon.getDefensa());
        vida.setText(pokemon.getVida());
        ataque.setText(pokemon.getAtaque());
        velocidad.setText(pokemon.getVelocidad());
        Glide.with(this).load(pokemon.getUri()).fitCenter().into(imageView);
    }

    //TODO
    public void deletePokemon(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("entrenadores").document(entrenador.getNombre())
                .collection("pokemones").document(pokemon.getNombre()).delete();
        Intent intent = new Intent(this, ListPokemon.class);
        intent.putExtra("entrenador", entrenador.getNombre());
        startActivity(intent);
        finish();
    }

}