package com.example.reto2;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    private TextView pokemonRow;
    private ImageButton image;

    public PokemonViewHolder( View itemView) {
        super(itemView);
        pokemonRow = itemView.findViewById(R.id.pokemonRow);
        image = itemView.findViewById(R.id.pokeimage);
    }

    public TextView getPokemonRow() {
        return pokemonRow;
    }

    public ImageButton getImage() {
        return image;
    }
}
