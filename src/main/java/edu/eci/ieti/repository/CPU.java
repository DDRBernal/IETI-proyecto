package edu.eci.ieti.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.json.JSONObject;

public class CPU extends Componente{

    private int nucleos;
    private double rendimiento_core_clock;
    private double rendimiento_boost_clock;
    private String TDP;
    private String SMT;

    public CPU(String nombre) {
        super(nombre);
    }

    public CPU(String name, int nucleos, double rendimiento_core_clock, double rendimiento_boost_clock,
               String tdp, String smt) {
        super(name);
        this.nucleos=nucleos;
        this.rendimiento_core_clock=rendimiento_core_clock;
        this.rendimiento_boost_clock=rendimiento_boost_clock;
        this.TDP=tdp;
        this.SMT=smt;
    }

    @Override
    public Componente createComponente(JSONObject jsonObject) {
        String name = jsonObject.get("name").toString();
        double rendimiento_core_clock = Double.parseDouble(jsonObject.get("rendimiento_core_clock").toString());
        double rendimiento_boost_clock = Double.parseDouble(jsonObject.get("rendimiento_boost_clock").toString());
        int nucleos = Integer.parseInt(jsonObject.get("nucleos").toString());
        String precio = jsonObject.get("precio").toString();
        String TDP = jsonObject.get("TDP").toString();
        String SMT = jsonObject.get("SMT").toString();
        String valoracion = jsonObject.get("valoracion").toString();
        CPU cpu = new CPU(name,nucleos,rendimiento_core_clock,rendimiento_boost_clock,TDP,SMT);
        cpu.setPrecio(Double.parseDouble(precio)); cpu.setValoracion(Integer.parseInt(valoracion));
        return cpu;
    }


    public double getRendimiento_boost_clock() {
        return rendimiento_boost_clock;
    }

    public int getNucleos() {
        return nucleos;
    }

    public double getRendimiento_core_clock() {
        return rendimiento_core_clock;
    }

    public String getSMT() {
        return SMT;
    }

    public String getTDP() {
        return TDP;
    }

}
