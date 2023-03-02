package edu.eci.ieti.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class Tarjeta_de_video extends Componente{

    private String procesador;
    private int memoria;
    private double core_clock;
    private double boost_clock;
    private int largo;

    public Tarjeta_de_video(String nombre,String procesador, int memoria, double core_clock
            , double boost_clock, int largo) {
        super(nombre);
        this.procesador=procesador;
        this.memoria = memoria;
        this.core_clock= core_clock;
        this.boost_clock = boost_clock;
        this.largo = largo;
    }

    public Tarjeta_de_video(String name) {
        super(name);
    }

    public String getProcesador() {
        return procesador;
    }

    public int getMemoria() {
        return memoria;
    }

    public double getBoost_clock() {
        return boost_clock;
    }

    public double getCore_clock() {
        return core_clock;
    }

    public void setBoost_clock(double boost_clock) {
        this.boost_clock = boost_clock;
    }

    public void setCore_clock(double core_clock) {
        this.core_clock = core_clock;
    }


    public void setLargo(int largo) {
        this.largo = largo;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    @Override
    public Componente createComponente(JSONObject jsonObject) {
        String name = jsonObject.get("name").toString();
        double boots = Double.parseDouble(jsonObject.get("boost_lock").toString());
        double core = Double.parseDouble(jsonObject.get("core_lock").toString());
        int largo = Integer.parseInt(jsonObject.get("largo").toString());
        int memoria = Integer.parseInt(jsonObject.get("memoria").toString());
        String precio = jsonObject.get("precio").toString();
        String procesador = jsonObject.get("procesador").toString();
        String valoracion = jsonObject.get("valoracion").toString();
        Tarjeta_de_video tarjeta_de_video = new Tarjeta_de_video(name,procesador,memoria,boots,core,largo);
        tarjeta_de_video.setPrecio(Double.parseDouble(precio)); tarjeta_de_video.setValoracion(Integer.parseInt(valoracion));
        return tarjeta_de_video;
    }

    public Tarjeta_de_video insertComponente(LinkedHashMap<String, String> component, MongoCollection<Document> componentes, Document data_collection){
        JSONObject jsonComponent = new JSONObject(component);
        Tarjeta_de_video tarjeta_de_video = new Tarjeta_de_video(jsonComponent.get("nombre").toString());
        tarjeta_de_video.setPrecio(Double.parseDouble(jsonComponent.get("precio").toString()));
        tarjeta_de_video.setValoracion(Integer.parseInt(jsonComponent.get("valoracion").toString()));
        tarjeta_de_video.setBoost_clock(Double.parseDouble(jsonComponent.get("boost_clock").toString()));
        tarjeta_de_video.setCore_clock(Double.parseDouble(jsonComponent.get("core_clock").toString()));
        tarjeta_de_video.setLargo(Integer.parseInt(jsonComponent.get("largo").toString()));
        tarjeta_de_video.setProcesador(jsonComponent.get("procesador").toString());
        tarjeta_de_video.setMemoria(Integer.parseInt(jsonComponent.get("memoria").toString()));

        data_collection.append("nombre",jsonComponent.get("nombre").toString());
        data_collection.append("precio",jsonComponent.get("precio").toString());
        data_collection.append("valoracion",jsonComponent.get("valoracion").toString());
        data_collection.append("boost_clock",jsonComponent.get("boost_clock").toString());
        data_collection.append("core_clock",jsonComponent.get("core_clock").toString());
        data_collection.append("largo",jsonComponent.get("largo").toString());
        data_collection.append("procesador",jsonComponent.get("procesador").toString());
        data_collection.append("memoria",jsonComponent.get("memoria").toString());
        componentes.insertOne(data_collection);
        System.out.println("procesado");
        return tarjeta_de_video;
    }
}
