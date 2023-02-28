package edu.eci.ieti.repository;

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
}
