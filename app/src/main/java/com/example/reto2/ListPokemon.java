package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import model.Entrenador;
import model.Pokemon;

public class ListPokemon extends AppCompatActivity implements View.OnClickListener {
    //elementos ui
    private EditText atrapar, buscar;
    private Button buscarBtn, atraparBtn;
    private RecyclerView list;
    //modelo
    private Entrenador entrenador;
    private ArrayList<Pokemon> pokemones;
    //firebase
    private FirebaseFirestore db;


    //recycler
    private  LinearLayoutManager manager;
    private PokemonAdapter adapter;

    public ListPokemon(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pokemon);
        db = FirebaseFirestore.getInstance();
        pokemones = new ArrayList<>();
        entrenadorExist(getIntent().getExtras().getString("entrenador"));
        atrapar = findViewById(R.id.atrapar);
        buscar = findViewById(R.id.buscar);
        buscarBtn = findViewById(R.id.buscarBtn);
        atraparBtn = findViewById(R.id.atraparBtn);
        list = findViewById(R.id.list);
        manager = new LinearLayoutManager(this);
        adapter = new PokemonAdapter(this);
        list.setLayoutManager(manager);
        list.setAdapter(adapter);
        list.setHasFixedSize(true);
        buscarBtn.setOnClickListener(this);
        atraparBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buscarBtn:
                adapter.clear();
                pokemonSearch(buscar.getText().toString());

                break;
            case R.id.atraparBtn:
                getPokemonFromApi(atrapar.getText().toString());
                break;
        }
    }

    public void registrarEntrenador(Entrenador entrenador) {
        db.collection("entrenadores").document(entrenador.getNombre()).set(entrenador);

    }

    public void atraparPokemon(Pokemon pokemon, Entrenador entrenador){
        db.collection("entrenadores").document(entrenador.getNombre())
                .collection("pokemones").document(pokemon.getNombre()).set(pokemon);
        pokemones.add(pokemon);
        adapter.addPokemon(pokemon);
    }

    public void getPokemonFromApi(String pokemonName){
        HTTPS https = new HTTPS();
        new Thread(
                ()->{
                    String response  = https.GETrequest("https://pokeapi.co/api/v2/pokemon/"+pokemonName);
                    if (response != ""){
                        try {
                            JSONObject pokemon = new JSONObject(response);
                            String name = pokemon.getString("name");

                            //imagen
                            String uri = pokemon.getJSONObject("sprites").getString("front_default");

                            //estadisticas
                            JSONArray stats = pokemon.getJSONArray("stats");
                            String vida = stats.getJSONObject(0).getString("base_stat");
                            String ataque = stats.getJSONObject(1).getString("base_stat");
                            String defensa = stats.getJSONObject(2).getString("base_stat");
                            String velocidad = stats.getJSONObject(5).getString("base_stat");

                            Pokemon p = new Pokemon(name, velocidad, defensa, ataque, uri, vida);
                            atraparPokemon(p, entrenador);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            runOnUiThread(()->Toast.makeText(this, "Pokemon no encontrado, verifica el nombre",Toast.LENGTH_LONG).show());
                        }
                    }
                    else{
                        runOnUiThread(()->Toast.makeText(this, "Pokemon no encontrado, verifica el nombre",Toast.LENGTH_LONG).show());
                    }
                }
        ).start();
    }

    public void getPokemonFromFirestore(){
        db.collection("entrenadores").document(entrenador.getNombre())
                .collection("pokemones").get().addOnCompleteListener(
                        task -> {
                            if(!task.getResult().isEmpty()){
                                for (DocumentSnapshot ds : task.getResult()) {
                                    Pokemon poke = ds.toObject(Pokemon.class);
                                    pokemones.add(poke);
                                    adapter.addPokemon(poke);
                                }
                            }
                        }
                );
    }



    public void entrenadorExist(String name){
        Query query = db.collection("entrenadores").whereEqualTo("nombre", name);
        query.get().addOnCompleteListener(
                task -> {

                    if(!task.getResult().isEmpty()){
                        DocumentSnapshot ds = task.getResult().getDocuments().get(0);
                        entrenador = ds.toObject(Entrenador.class);
                        Toast.makeText(this, "Bienvenido",Toast.LENGTH_LONG).show();
                        getPokemonFromFirestore();
                        adapter.setEntrenador(entrenador);
                    }
                    else{
                        Entrenador n = new Entrenador(name);
                        registrarEntrenador(n);
                        entrenador = n;
                        Toast.makeText(this, "Has sido registrado, Bienvenido",Toast.LENGTH_LONG).show();
                        adapter.setEntrenador(entrenador);
                    }
                }
        );
    }



    public void pokemonSearch(String nombreDelPokemon){
        Query query = db.collection("entrenadores").document(entrenador.getNombre())
                .collection("pokemones").whereEqualTo("nombre", nombreDelPokemon);
        query.get().addOnCompleteListener(
                task -> {
                    if(!task.getResult().isEmpty()){
                        DocumentSnapshot ds = task.getResult().getDocuments().get(0);
                        Pokemon pokefound = ds.toObject(Pokemon.class);
                        Toast.makeText(this, "",Toast.LENGTH_LONG).show();
                        adapter.addPokemon(pokefound);
                    }
                    else{

                        Toast.makeText(this, "No esta en la Pokedex",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}