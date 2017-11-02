package com.example.nacho.mannadrawe.proveedorDeContenido;

import android.net.Uri;
import android.provider.BaseColumns;


public class Contrato {

    public final static String AUTHORITY = "com.example.nacho.mannadrawe" +
            ".proveedorDeContenido.ProveedorContenido";

    public static class Orden implements BaseColumns {

        public final static String NOMBRE_TABLA = "Orden";

        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + NOMBRE_TABLA);
        // Table column
        public final static String CODIGO_EMPLEADO = "Codigo_empleado";
        public final static String FECHA = "Fecha";
        public final static String CODIGO = "Código";
        public final static String PRIORIDAD = "Prioridad";
        public final static String ESTADO = "Estado";
        public final static String UBICACION = "Ubicación";
        public final static String DESCRIPCION = "Descripción";
    }

}
