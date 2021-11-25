package model;

public class Pokemon {
    private String nombre;
    private String velocidad;
    private String defensa;
    private String ataque;
    private String uri;

    public Pokemon(String nombre, String velocidad, String defensa, String ataque, String uri) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.defensa = defensa;
        this.ataque = ataque;
        this.uri = uri;
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
}
