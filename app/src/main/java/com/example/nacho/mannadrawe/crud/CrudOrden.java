package com.example.nacho.mannadrawe.crud;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.example.nacho.mannadrawe.pojos.DatosEmpleado;
import com.example.nacho.mannadrawe.proveedorDeContenido.Contrato;
import com.example.nacho.mannadrawe.pojos.OrdenDeTrabajo;


public class CrudOrden {

    static public void insert(ContentResolver resolvedor, OrdenDeTrabajo orden){

        Uri uri = Contrato.Orden.CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put(Contrato.Orden.CODIGO_EMPLEADO, orden.getCodigoEmpleado());
        values.put(Contrato.Orden.FECHA, orden.getFecha());
        values.put(Contrato.Orden.CODIGO, orden.getCodigo());
        values.put(Contrato.Orden.PRIORIDAD, orden.getPrioridad());
        values.put(Contrato.Orden.ESTADO, orden.getEstado());
        values.put(Contrato.Orden.UBICACION, orden.getUbicacion());
        values.put(Contrato.Orden.DESCRIPCION, orden.getDescripcion());

        resolvedor.insert(uri, values);
    }

    static public void delete(ContentResolver resolver, int OrdenId){

        Uri uri = Uri.parse(Contrato.Orden.CONTENT_URI+ "/" +OrdenId) ;

        resolver.delete(uri, null, null);
    }


    static public void update(ContentResolver resolver,OrdenDeTrabajo orden){

        Uri uri = Uri.parse(Contrato.Orden.CONTENT_URI+ "/" +orden.getId()) ;

        ContentValues values = new ContentValues();
        values.put(Contrato.Orden.PRIORIDAD,orden.getPrioridad());
        values.put(Contrato.Orden.ESTADO,orden.getEstado());
        values.put(Contrato.Orden.UBICACION,orden.getUbicacion());
        values.put(Contrato.Orden.DESCRIPCION,orden.getDescripcion());

        resolver.update(uri, values, null,null);

    }

    static public OrdenDeTrabajo readRecord(ContentResolver resolver,int ordenId){

        Uri uri = Uri.parse(Contrato.Orden.CONTENT_URI+ "/" +ordenId) ;

        String[] projection ={
                Contrato.Orden.PRIORIDAD,
                Contrato.Orden.ESTADO,
                Contrato.Orden.UBICACION,
                Contrato.Orden.DESCRIPCION
        };

         Cursor cursor= resolver.query(uri, projection, null, null, null);

        if(cursor.moveToFirst()){

            OrdenDeTrabajo  orden = new OrdenDeTrabajo();

            orden.setId(ordenId);
            orden.setPrioridad(cursor.getString(cursor.getColumnIndex(Contrato.Orden.PRIORIDAD)));
            orden.setEstado(cursor.getString(cursor.getColumnIndex(Contrato.Orden.ESTADO)));
            orden.setUbicacion(cursor.getString(cursor.getColumnIndex(Contrato.Orden.UBICACION)));
            orden.setDescripcion(cursor.getString(cursor.getColumnIndex(Contrato.Orden.DESCRIPCION)));

            return orden;
        }
        return  null;
   }


}
