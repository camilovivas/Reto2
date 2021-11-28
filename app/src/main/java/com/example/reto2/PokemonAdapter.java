package com.example.reto2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private ArrayList<Pokemon> pokemons;

    public PokemonAdapter(){
        pokemons=new ArrayList<>();
    }
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pokemonrow, parent,false);
        PokemonViewHolder holder = new PokemonViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.getPokemonRow().setText(pokemon.getNombre() +" "+ pokemon.getAtaque() +" "+ pokemon.getDefensa() +" "+pokemon.getVida() +" "+  pokemon.getVelocidad());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void  addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
    }
}
