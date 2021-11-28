package com.example.reto2;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import model.Entrenador;
import model.Pokemon;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    private TextView pokemonRow;
    private ImageButton image;
    private Entrenador entrenador;
    private Pokemon pokemon;
    private Activity activity;

    public PokemonViewHolder( View itemView) {
        super(itemView);
        pokemonRow = itemView.findViewById(R.id.pokemonRow);
        image = itemView.findViewById(R.id.pokeimage);
        image.setOnClickListener(v->actionbtn());
    }

    private void actionbtn() {
        Intent intent = new Intent(activity, PokemonDetail.class);
        intent.putExtra("entrenador", entrenador);
        intent.putExtra("pokemon", pokemon);
        activity.startActivity(intent);
    }


    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public TextView getPokemonRow() {
        return pokemonRow;
    }

    public ImageButton getImage() {
        return image;
    }

    public void create(){
        pokemonRow.setText(pokemon.getNombre());
        Glide.with(activity).load(pokemon.getUri()).fitCenter().into(image);
    }


    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
