package edu.eci.ieti.repository;

import org.json.JSONObject;

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
}
