package edu.eci.ieti.repository;

public class CPU extends Componente{

    private int nucleos;
    private double rendimiento_core_clock;
    private double rendimiento_boost_clock;
    private String TDP;
    private String SMT;

    public CPU(String nombre) {
        super(nombre);
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
