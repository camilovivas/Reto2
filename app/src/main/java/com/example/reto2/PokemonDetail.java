package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import model.Pokemon;

public class PokemonDetail extends AppCompatActivity {

    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
    }

    //TODO
    public void deletePokemon(String name){

    }

}