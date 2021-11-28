package com.example.reto2;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    private TextView pokemonRow;

    public PokemonViewHolder( View itemView) {
        super(itemView);
        pokemonRow = itemView.findViewById(R.id.pokemonRow);
    }

    public TextView getPokemonRow() {
        return pokemonRow;
    }
}
