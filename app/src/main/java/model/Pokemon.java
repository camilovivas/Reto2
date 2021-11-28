package model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String nombre;
    private String velocidad;
    private String defensa;
    private String ataque;
    private String uri;
    private String vida;

    public Pokemon(String nombre, String velocidad, String defensa, String ataque, String uri , String vida) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.defensa = defensa;
        this.ataque = ataque;
        this.uri = uri;
        this.vida=vida;
    }

    public Pokemon() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public String getDefensa() {
        return defensa;
    }

    public String getAtaque() {
        return ataque;
    }

    public String getUri() {
        return uri;
    }

    public String getVida() {
        return vida;
    }
}
