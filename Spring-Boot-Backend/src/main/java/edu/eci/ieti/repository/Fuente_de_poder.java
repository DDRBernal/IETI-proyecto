package edu.eci.ieti.repository;

import org.json.JSONObject;

public class Fuente_de_poder extends Componente{

    private String tipo;
    private int indice_de_eficiencia;
    private int potencia_en_watts;
    private String modular;

    public Fuente_de_poder(String nombre) {
        super(nombre);
    }

    public Fuente_de_poder(String name, String tipo, int indice_de_eficiencia, int potencia_en_watts, String modular) {
        super(name);
        this.tipo = tipo;
        this.indice_de_eficiencia = indice_de_eficiencia;
        this.potencia_en_watts = potencia_en_watts;
        this.modular = modular;
    }

    @Override
    public Componente createComponente(JSONObject jsonObject) {
        String name = jsonObject.get("name").toString();
        int indice_de_eficiencia = Integer.parseInt(jsonObject.get("indice_de_eficiencia").toString());
        int potencia_en_watts = Integer.parseInt(jsonObject.get("potencia_en_watts").toString());
        String modular = jsonObject.get("modular").toString();
        String precio = jsonObject.get("precio").toString();
        String tipo = jsonObject.get("tipo").toString();
        String valoracion = jsonObject.get("valoracion").toString();
        Fuente_de_poder fuente_de_poder = new Fuente_de_poder(name,tipo,indice_de_eficiencia,potencia_en_watts,modular);
        fuente_de_poder.setPrecio(Double.parseDouble(precio));
        fuente_de_poder.setValoracion(Integer.parseInt(valoracion));
        return fuente_de_poder;
    }

}
