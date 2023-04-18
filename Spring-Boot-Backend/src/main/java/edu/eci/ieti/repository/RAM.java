package edu.eci.ieti.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.json.JSONObject;

public class RAM extends Componente{

    private int velocidad;
    private int modulos;
    private String color;
    private int latencia;
    private int CAS_latencia;

    public RAM(String nombre) {
        super(nombre);
    }

    public RAM(String name, int velocidad, int modulos, String color, int latencia, int cas_latencia) {
        super(name);
        this.velocidad=velocidad;
        this.modulos=modulos;
        this.color=color;
        this.latencia=latencia;
        this.CAS_latencia=cas_latencia;
    }

    @Override
    public Componente createComponente(JSONObject jsonObject) {
        String name = jsonObject.get("name").toString();
        int velocidad = Integer.parseInt(jsonObject.get("velodidad").toString());
        int modulos = Integer.parseInt(jsonObject.get("modulos").toString());
        int latencia = Integer.parseInt(jsonObject.get("latencia").toString());
        int CAS_latencia = Integer.parseInt(jsonObject.get("CAS_latencia").toString());
        String precio = jsonObject.get("precio").toString();
        String color = jsonObject.get("color").toString();
        String valoracion = jsonObject.get("valoracion").toString();
        RAM ram = new RAM(name,velocidad,modulos,color,latencia,CAS_latencia);
        ram.setPrecio(Double.parseDouble(precio)); ram.setValoracion(Integer.parseInt(valoracion));
        return ram;
    }


}
