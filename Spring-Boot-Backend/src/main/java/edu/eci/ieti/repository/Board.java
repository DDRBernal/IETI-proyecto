package edu.eci.ieti.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class Board extends Componente{

    private String socket_CPU;
    private String especificaciones;
    private int memoria_maxima;
    private int espacio_de_memoria;

    public Board(String nombre) {
        super(nombre);
    }

    public Board(String name, String socket, String especificaciones1,
                 int memoria, int espacio_de_memoria1) {
        super(name);
        this.socket_CPU = socket;
        this.especificaciones = especificaciones1;
        this.memoria_maxima = memoria;
        this.espacio_de_memoria = espacio_de_memoria1;
    }

    @Override
    public Componente createComponente(JSONObject jsonObject) {
        String name = jsonObject.get("name").toString();
        int memoria = Integer.parseInt(jsonObject.get("memoria_maxima").toString());
        int espacio_de_memoria1 = Integer.parseInt(jsonObject.get("espacio_de_memoria").toString());
        String socket = jsonObject.get("socket_CPU").toString();
        String especificaciones1 = jsonObject.get("especificaciones").toString();
        String precio = jsonObject.get("precio").toString();
        String procesador = jsonObject.get("procesador").toString();
        String valoracion = jsonObject.get("valoracion").toString();
        Board board = new Board(name,socket,especificaciones1,memoria,espacio_de_memoria1);
        board.setPrecio(Double.parseDouble(precio)); board.setValoracion(Integer.parseInt(valoracion));
        return board;
    }

    public void setEspacio_de_memoria(int espacio_de_memoria) {
        this.espacio_de_memoria = espacio_de_memoria;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public void setMemoria_maxima(int memoria_maxima) {
        this.memoria_maxima = memoria_maxima;
    }

    public void setSocket_CPU(String socket_CPU) {
        this.socket_CPU = socket_CPU;
    }

    public Componente insertComponente(LinkedHashMap<String, String> component, MongoCollection<Document> componentes, Document data_collection) {
        JSONObject jsonComponent = new JSONObject(component);
        Board board = new Board(component.get("nombre"));
        board.setPrecio(Double.parseDouble(jsonComponent.get("precio").toString()));
        board.setValoracion(Integer.parseInt(jsonComponent.get("valoracion").toString()));
        board.setEspacio_de_memoria(Integer.parseInt(component.get("espacio_de_memoria")));
        board.setEspecificaciones(jsonComponent.get("especificaciones").toString());
        board.setMemoria_maxima(Integer.parseInt(jsonComponent.get("memoria_maxima").toString()));
        board.setSocket_CPU(jsonComponent.get("socket_cpu").toString());
        return board;
    }
}
