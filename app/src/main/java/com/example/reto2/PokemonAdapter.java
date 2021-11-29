package com.example.reto2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import model.Entrenador;
import model.Pokemon;


public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private ArrayList<Pokemon> pokemons;
    private Activity activity;

    private Entrenador entrenador;

    public PokemonAdapter(Activity activity){
        this.activity = activity;
        pokemons=new ArrayList<>();
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
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
        holder.setPokemon(pokemons.get(position));
        holder.setEntrenador(entrenador);
        holder.setActivity(activity);
        holder.create();
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void  addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
        notifyItemInserted(pokemons.size()-1);
    }

    public void clear(){
        notifyItemRangeRemoved(0, pokemons.size());
        pokemons.clear();
    }
}
