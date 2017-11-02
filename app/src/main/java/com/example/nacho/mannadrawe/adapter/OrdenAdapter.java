package com.example.nacho.mannadrawe.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.nacho.mannadrawe.proveedorDeContenido.Contrato;
import com.example.nacho.mannadrawe.R;



public class OrdenAdapter extends CursorAdapter{

    public OrdenAdapter(Context context) {
        super(context, null, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.orden_item, parent, false);
        bindView(v, context, cursor);

        return v;
    }

    @Override

    public void bindView(View view, Context context, Cursor cursor) {

        int ID = cursor.getInt(cursor.getColumnIndex(Contrato.Orden._ID));
        int codigoEmpleado = cursor.getInt(cursor.getColumnIndex(Contrato.Orden.CODIGO_EMPLEADO));
        String fecha = cursor.getString(cursor.getColumnIndex(Contrato.Orden.FECHA));
        long codigo = cursor.getLong(cursor.getColumnIndex(Contrato.Orden.CODIGO));
        String prioridad = cursor.getString(cursor.getColumnIndex(Contrato.Orden.PRIORIDAD));
        String esdtado = cursor.getString(cursor.getColumnIndex(Contrato.Orden.ESTADO));
        String ubicacion = cursor.getString(cursor.getColumnIndex(Contrato.Orden.UBICACION));
        String descripcion = cursor.getString(cursor.getColumnIndex(Contrato.Orden.DESCRIPCION));


        TextView textViewcodigoEmpleado =(TextView ) view.findViewById(R.id.
                textView_item_orden_codigo_empleado);
        textViewcodigoEmpleado.setText("Código de empleado: "+codigoEmpleado);
        TextView textViewFecha = (TextView) view.findViewById(R.id.textView_item_orden_fecha);
        textViewFecha.setText(Contrato.Orden.FECHA +": "+fecha);
        TextView textViewCodigo = (TextView) view.findViewById(R.id.textView_item_orden_codigo);
        textViewCodigo.setText(Contrato.Orden.CODIGO +": "+Long.toString(codigo));
        TextView textViewPrioridad = (TextView) view.findViewById(R.id.textView_item_orden_prioridad);
        textViewPrioridad.setText(Contrato.Orden.PRIORIDAD +": "+prioridad);
        TextView textViewEstado = (TextView) view.findViewById(R.id.textView_item_orden_estado);
        textViewEstado.setText(Contrato.Orden.ESTADO+": "+esdtado);
        TextView textViewUbicacion = (TextView) view.findViewById(R.id.textView_item_orden_ubicacion);
        textViewUbicacion.setText(Contrato.Orden.UBICACION+": "+ubicacion);
        TextView textViewDescripcion = (TextView) view.findViewById(R.id.textView_item_orden_descrripcion);
        textViewDescripcion.setText(Contrato.Orden.DESCRIPCION+": "+descripcion);

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int color = generator.getColor(fecha); //Genera un color según el nombre

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(Long.toString(codigo).substring(9,13), color);

        ImageView image = (ImageView) view.findViewById(R.id.image_view_item_orden);
        image.setImageDrawable(drawable);

        view.setTag(ID);



    }
}
