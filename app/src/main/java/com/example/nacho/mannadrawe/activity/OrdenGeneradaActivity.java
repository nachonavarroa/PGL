package com.example.nacho.mannadrawe.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nacho.mannadrawe.R;
import com.example.nacho.mannadrawe.crud.CrudOrden;
import com.example.nacho.mannadrawe.pojos.DatosEmpleado;
import com.example.nacho.mannadrawe.pojos.OrdenDeTrabajo;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdenGeneradaActivity extends AppCompatActivity {

    Activity contexto;
    TextView textViewFecha;
    TextView textViewCodigo;
    TextView textViewNombreEmpleado;
    TextView textViewCodigoEmpleado;
    TextView textViewPrioridad ;
    TextView textViewEstado ;
    TextView textViewDescripcion ;
    TextView textViewUbicacion ;
    Button buttonConfirmar;
    CheckBox checkBoxMostrarPdf;
    Intent intent;
    String fecha;
    long   codigo;
    String nombreOperario;
    String codigoOperarioString;
    String prioridad;
    String estado;
    String ubicacion;
    String descripcion;
    OrdenDeTrabajo ordenDeTrabajo;
    DatosEmpleado datosEmpleado;
    Boolean OrdenConfirmada=false;
    Boolean generarPdf =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_orden_generada);

        //ActionBar------------------------ -------
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setSubtitle(getResources().getText(R.string.subtitulo_orden_generada));
        //-----------------------------------------
        contexto=this;
        intent =this.getIntent();
        iniciarViews ();
        introducirFechaCodigo();
        extraeDatosEmpleado();
        extraeDatosOrdenDeTrabajo();
        generarOrden();

        checkBoxMostrarPdf.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(checkBoxMostrarPdf.isChecked()){
                    generarPdf=true;
                    Toast.makeText (contexto,getResources ().getString (R.string.toast_mostrarPdf),
                            Toast.LENGTH_LONG).show ();
                }
                else{generarPdf=false;
                    Toast.makeText (contexto,getResources ().getString (R.string.toast_no_mostrarPdf),
                            Toast.LENGTH_LONG).show ();
                }
            }
        });

        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(generarPdf){
                    muestraPdf( crearPdf(generarTexto(),ordenDeTrabajo) ,contexto);
                }
                cambioColor();

                if(!OrdenConfirmada){
                    ordenDeTrabajo.setCodigoEmpleado(datosEmpleado.getCodigo());
                    CrudOrden.insert(getContentResolver(),ordenDeTrabajo);
                    Toast.makeText(contexto,getResources().
                            getText(R.string.orden_generada_correcta),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),VerOrdenesActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(OrdenConfirmada){
                    Toast.makeText(contexto,getResources().
                            getText(R.string.orden_ya_generada), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),VerOrdenesActivity.class);
                    startActivity(intent);
                    finish();
                }


                OrdenConfirmada=true;

            }
        });
    }
    //------------------------------
    private void iniciarViews(){
        checkBoxMostrarPdf = (CheckBox)  findViewById (R.id.checkBoxMostrarPdf);
        textViewFecha = (TextView) findViewById(R.id.textViewFecha);
        textViewCodigo = (TextView) findViewById(R.id.textViewCodigo);
        textViewNombreEmpleado =(TextView) findViewById(R.id.textViewNombreEmpleado);
        textViewCodigoEmpleado =(TextView) findViewById(R.id.textViewCodigoEmpleado);
        textViewPrioridad = (TextView) findViewById(R.id.textViewPrioridad);
        textViewEstado =(TextView) findViewById(R.id.textViewEstado);
        textViewUbicacion=(TextView) findViewById(R.id.textViewUbicacion);
        textViewDescripcion =(TextView) findViewById(R.id.textViewDescripcion);
        buttonConfirmar =(Button) findViewById(R.id.buttonConfirmar) ;
    }

    private void introducirFechaCodigo(){
        ordenDeTrabajo= intent.getParcelableExtra("ordenDeTrabajo");
        ordenDeTrabajo.setCodigo(codigo());
        ordenDeTrabajo.setFecha(fecha());
    }

    private void extraeDatosOrdenDeTrabajo(){
        fecha = ordenDeTrabajo.getFecha();
        codigo= ordenDeTrabajo.getCodigo();
        prioridad =ordenDeTrabajo.getPrioridad();
        estado =ordenDeTrabajo.getEstado();
        ubicacion = ordenDeTrabajo.getUbicacion();
        descripcion =ordenDeTrabajo.getDescripcion();
    }
    private void extraeDatosEmpleado(){
        datosEmpleado = intent.getParcelableExtra("datosEmplea");
        nombreOperario =datosEmpleado.getNombre();
        int codigoOperario = datosEmpleado.getCodigo();
        codigoOperarioString = Integer.toString(codigoOperario);
    }
    private String fecha(){
        String fecha =  new SimpleDateFormat ("dd-MM-yyyy").format(new Date ());
        return fecha;
    }
    private long codigo(){
        long codigo=  System.currentTimeMillis();
        return codigo;
    }
    private void generarOrden(){
        textViewFecha.setText(getResources().getText(R.string.titulo1)+" "+fecha);
        textViewCodigo.setText(getResources().getText(R.string.titulo2)+" "+codigo);
        textViewNombreEmpleado.setText(getResources().getText(R.string.titulo3)+" "+nombreOperario);
        textViewCodigoEmpleado.setText(getResources().getText(R.string.titulo4)+" "+codigoOperarioString);
        textViewPrioridad.setText(getResources().getText(R.string.titulo5)+" "+prioridad);
        textViewEstado.setText(getResources().getText(R.string.titulo6)+" "+estado);
        textViewUbicacion.setText(getResources().getText(R.string.titulo7)+" "+ubicacion);
        textViewDescripcion.setText(getResources().getText(R.string.titulo8)+"\n"+descripcion);
    }

    private void cambioColor(){
        textViewFecha.setTextColor(ContextCompat.getColor(contexto,R.color.colorAzul));
        textViewCodigo.setTextColor(ContextCompat.getColor(contexto,R.color.colorAzul));
        textViewNombreEmpleado.setTextColor(ContextCompat.getColor(contexto,R.color.colorAzul));
        textViewCodigoEmpleado.setTextColor(ContextCompat.getColor(contexto,R.color.colorAzul));
        textViewPrioridad.setTextColor(ContextCompat.getColor(contexto,R.color.colorAzul));
        textViewEstado.setTextColor(ContextCompat.getColor(contexto,R.color.colorAzul));
        textViewUbicacion.setTextColor(ContextCompat.getColor(contexto,R.color.colorAzul));
        textViewDescripcion.setTextColor(ContextCompat.getColor(contexto,R.color.colorAzul));
    }


    //Este metodo crea los menús--------------------

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE,R.integer.indice_icono_camara,Menu.NONE,R.string.string_camara)
                .setIcon(R.drawable.ic_camera_primary_dark)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add(Menu.NONE,R.integer.indice_icono_ayuda,Menu.NONE,R.string.string_ayuda)
                .setIcon(R.drawable.ic_help_outline_black_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add (Menu.NONE,R.integer.indice_icono_ir_atras,Menu.NONE,R.string.string_ir_atras)
                .setIcon (R.drawable.ic_ir_atras)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;
        switch (id){
            case R.integer.indice_icono_ayuda:
                Toast.makeText (getApplicationContext (),
                        getResources ().getString (R.string.string_ayuda),
                        Toast.LENGTH_SHORT).show ();
                intent = new Intent(getApplicationContext(),AyudaActivity.class);
                startActivity(intent);
                break;
            case R.integer.indice_icono_camara:
                Toast.makeText (getApplicationContext (),
                        getResources ().getString (R.string.string_camara),
                        Toast.LENGTH_SHORT).show ();
                hacerFoto(ordenDeTrabajo);
                break;
            case R.integer.indice_icono_ir_atras:
                Toast.makeText (getApplicationContext (),
                        getResources ().getString (R.string.string_ir_atras),
                        Toast.LENGTH_SHORT).show ();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //--Generar texto para PDF-------------

    private String generarTexto()  {
        String nombreApp =(String) getResources().getText(R.string.app_name).toString();
        String fecha= textViewFecha.getText().toString();
        String codigo =textViewCodigo.getText().toString();
        String nombre =textViewNombreEmpleado.getText().toString();
        String codigoEmpl=textViewCodigoEmpleado.getText().toString();
        String prioridad =textViewPrioridad.getText().toString();
        String estado = textViewEstado.getText().toString();
        String ubicacion=textViewUbicacion.getText().toString();
        String descripcion =textViewDescripcion.getText().toString();
        String autor = (String) getResources().getText(R.string.copy).toString();

        String texto=nombreApp+"\n\n"+fecha+"\n"+codigo+"\n"+nombre+"\n"+codigoEmpl+"\n"
                +prioridad+"\n"+estado+"\n"+ubicacion+"\n"+descripcion+"\n\n\n\n"+autor;
        return texto;
    }
    //--Generar  PDF-------------

    public String crearPdf(String texto ,OrdenDeTrabajo ot) {

        String nombreApp=getResources().getText(R.string.app_name).toString();
        boolean errorPdf;
        Document document=new Document();
        File carpetaManNan = new File (Environment.getExternalStorageDirectory()+File.separator+nombreApp);

        if(!carpetaManNan.exists()){
            carpetaManNan.mkdir();
        }

        String path=Environment.getExternalStorageDirectory()+File.separator+nombreApp+
                File.separator+nombreApp+ot.getCodigo ()+".pdf";

        try {

            PdfWriter.getInstance(document, new FileOutputStream (path));
            document.open();
            document.add(new Paragraph (texto));
            document.close();

        } catch (FileNotFoundException e) {
            Toast.makeText(this,getResources ().getString (R.string.toas_error_crear_fichero)+path ,Toast.LENGTH_SHORT).show();
            errorPdf=true;

        } catch (DocumentException e) {
            Toast.makeText(this,getResources ().getString (R.string.toas_error_crear_fichero) +path,Toast.LENGTH_SHORT).show();
            errorPdf=true;
        }
        return path;
    }

    public void muestraPdf(String archivo ,Context contexto){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        File file = new File (archivo);
        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setDataAndType (Uri.fromFile (file),"application/pdf");
        intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            contexto.startActivity (intent);
        }catch(ActivityNotFoundException e){
            Toast.makeText (contexto,"no tiene apli para pdf",Toast.LENGTH_LONG).show ();
        }


    }

//---Hacer foto-------------------------------------------------------------------------------------

    private void hacerFoto(OrdenDeTrabajo ot){

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Intent intentCamara = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        String nombreApp=getResources().getText(R.string.app_name).toString();

        String pathCarpetacontenedora = Environment.getExternalStorageDirectory()
                + File.separator+nombreApp;
        File fileContenedora = new File (pathCarpetacontenedora);

        if(!fileContenedora.exists()){

            fileContenedora.mkdir();
        }

        String pathFoto=Environment.getExternalStorageDirectory()+File.separator+nombreApp+
                File.separator+nombreApp+ot.getCodigo ()+".jpg";

        File fileFoto= new File(pathFoto);

        Uri uriSavedImage = Uri.fromFile(fileFoto);

        intentCamara.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

        startActivityForResult(intentCamara, 1);

        intentCamara.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


    }

     @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}

