package edu.eci.ieti.repository;

import org.json.JSONObject;

public class Carcasa extends Componente {

    private String tipo;
    private String fuente_de_alimentacion;
    private String panel_lateral;

    public Carcasa(String nombre) {
        super(nombre);
    }

    public Carcasa(String name, String tipo, String fuente, String panel) {
        super(name);
        this.tipo=tipo;
        this.fuente_de_alimentacion=fuente;
        this.panel_lateral = panel;
    }

    @Override
    public Componente createComponente(JSONObject jsonObject) {
        String name = jsonObject.get("name").toString();
        String tipo = jsonObject.get("tipo").toString();
        String fuente = jsonObject.get("fuente_de_alimentacion").toString();
        String panel = jsonObject.get("panel_lateral").toString();
        String precio = jsonObject.get("precio").toString();
        String valoracion = jsonObject.get("valoracion").toString();
        Carcasa carcasa = new Carcasa(name,tipo,fuente,panel);
        carcasa.setPrecio(Double.parseDouble(precio)); carcasa.setValoracion(Integer.parseInt(valoracion));
        return carcasa;
    }

}
