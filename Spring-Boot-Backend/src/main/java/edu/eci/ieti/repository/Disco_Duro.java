package edu.eci.ieti.repository;

import org.json.JSONObject;

public class Disco_Duro extends Componente{

    private int capacidad;
    private String tipo;
    private int cache;
    private String interfaz;
    private String factor_de_forma;

    public Disco_Duro(String nombre){
        super(nombre);
    }

    public Disco_Duro(String name, int capacidad, String tipo, int cache,
                      String interfaz, String factor_de_forma) {
        super(name);
        this.cache=cache;
        this.capacidad=capacidad;
        this.tipo=tipo;
        this.interfaz=interfaz;
        this.factor_de_forma=factor_de_forma;
    }

    @Override
    public Componente createComponente(JSONObject jsonObject) {
        String name = jsonObject.get("name").toString();
        int capacidad = Integer.parseInt(jsonObject.get("capacidad").toString());
        int cache = Integer.parseInt(jsonObject.get("cache").toString());
        String tipo = jsonObject.get("tipo").toString();
        String interfaz = jsonObject.get("interfaz").toString();
        String factor_de_forma = jsonObject.get("factor_de_forma").toString();

        String precio = jsonObject.get("precio").toString();
        String valoracion = jsonObject.get("valoracion").toString();
        Disco_Duro disco_duro = new Disco_Duro(name,capacidad,tipo,cache,interfaz,factor_de_forma);
        disco_duro.setPrecio(Double.parseDouble(precio)); disco_duro.setValoracion(Integer.parseInt(valoracion));
        return disco_duro;
    }


}
