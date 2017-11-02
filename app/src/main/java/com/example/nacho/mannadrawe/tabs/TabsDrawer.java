package com.example.nacho.mannadrawe.tabs;

public class TabsDrawer {
    final   String generarOt;
    final   String autor;
    final   String ayuda;
    final   String ver;
    final   String verEstado;

    public TabsDrawer() {
        generarOt = "Ir generar Orden";
        autor     = "Ir autor";
        ayuda     = "Ir ayuda";
        ver       = "Ir ver ordenes";
        verEstado = "Ir ver estado ordenes";
    }

    public String getVerEstado() {
        return verEstado;
    }
    public String getGenerarOt() {
        return generarOt;
    }

    public String getAutor() {
        return autor;
    }

    public String getAyuda() {
        return ayuda;
    }

    public String getVer() {
        return ver;
    }
}
