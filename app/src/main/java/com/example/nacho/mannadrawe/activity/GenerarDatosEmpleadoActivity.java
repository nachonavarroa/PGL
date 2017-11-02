package com.example.nacho.mannadrawe.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nacho.mannadrawe.R;
import com.example.nacho.mannadrawe.pojos.DatosEmpleado;

public class GenerarDatosEmpleadoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Activity contexto;
    EditText editTextNombreEmpleado;
    EditText editTextCodigoEmpleado;
    Button buttoncContinuarEmpleado;
    DatosEmpleado datosEmpleado;
    Boolean validadarOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_datos_empleado);

        //--ActionBar-----------------------
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setSubtitle(getResources().getText(R.string.subtitulo_datos_empleado));
        actionBar.setHomeButtonEnabled (true);
        //------------------------------------------------
        contexto=this;
        buttoncContinuarEmpleado = (Button)   findViewById(R.id.buttonContiuarEmpleado);
        editTextNombreEmpleado   = (EditText) findViewById(R.id.editTextNombreEmpleado);
        editTextCodigoEmpleado   = (EditText) findViewById(R.id.editTextCodigoEmpleado);

        buttoncContinuarEmpleado.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String nombreEmpleado = editTextNombreEmpleado.getText().toString();
                String codigoEmpleadoString =editTextCodigoEmpleado.getText().toString();

                if( validarCampos(nombreEmpleado,codigoEmpleadoString) ){
                    datosEmpleado = crearDatosEmpleado(nombreEmpleado,codigoEmpleadoString);
                    Intent intent = new Intent(contexto,TipoDeOrdenActivity.class);
                    intent.putExtra("datosDeEmpleado", datosEmpleado);
                    startActivity(intent);
                }
            }
        });

    }
    //---------------------------------------------------------------------------------------------

    private DatosEmpleado crearDatosEmpleado
            (String nombreEmpleado,String codigoEmpleadoString ){

        int codigoEmpleado=Integer.parseInt(codigoEmpleadoString);
        datosEmpleado = new DatosEmpleado(nombreEmpleado, codigoEmpleado);

        return datosEmpleado;
    }
    //------------------------------------------------
    private boolean validarCampos(String nombreEmpleado, String codigoEmpleadoString) {
        validadarOk=true;
        editTextNombreEmpleado.setError(null);
        editTextCodigoEmpleado.setError(null);

        if(TextUtils.isEmpty(nombreEmpleado)){
            editTextNombreEmpleado.setError(getString(R.string.campo_vacio));
            editTextNombreEmpleado.requestFocus();
            validadarOk=false;
            return  validadarOk;
        }
        if(TextUtils.isEmpty(codigoEmpleadoString)){
            editTextCodigoEmpleado.setError(getString(R.string.campo_vacio));
            editTextCodigoEmpleado.requestFocus();
            validadarOk=false;
            return  validadarOk;
        }
        String codigoString =editTextCodigoEmpleado.getText().toString();
        int codigo =Integer.parseInt(codigoString);
        if(codigo==0){
            editTextCodigoEmpleado.setError(getString(R.string.valores_no_validos));
            editTextCodigoEmpleado.requestFocus();
            validadarOk=false;
            return  validadarOk;

        }
        return validadarOk;
    }


    //Este metodo crea los menús
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE,R.integer.indice_icono_ayuda,Menu.NONE,R.string.string_ayuda)
                .setIcon(R.drawable.ic_help_outline_black_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add(Menu.NONE,R.integer.indice_icono_ir_atras,Menu.NONE,R.string.string_ir_atras)
                .setIcon(R.drawable.ic_ir_atras)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.integer.indice_icono_ayuda:
                Toast.makeText (getApplicationContext (),
                        getResources ().getString (R.string.string_ayuda),
                        Toast.LENGTH_SHORT).show ();
                Intent intent = new Intent(getApplicationContext(),AyudaActivity.class);
                startActivity(intent);
                break;
            case R.integer.indice_icono_ir_atras:
                Toast.makeText (getApplicationContext (),
                        getResources ().getString (R.string.string_ir_atras),
                        Toast.LENGTH_SHORT).show ();
                finish ();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}