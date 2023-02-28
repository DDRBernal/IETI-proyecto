package edu.eci.ieti.repository;

public abstract class Componente {

    private String id;
    private String nombre;

    private double precio;

    private int valoracion;

    public Componente(String nombre){
        this.nombre=nombre;
    }

    public String getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public int getValoracion() {
        return valoracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
}
