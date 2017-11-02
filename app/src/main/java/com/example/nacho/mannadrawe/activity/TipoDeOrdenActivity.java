package com.example.nacho.mannadrawe.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.nacho.mannadrawe.R;
import com.example.nacho.mannadrawe.pojos.DatosEmpleado;
import com.example.nacho.mannadrawe.pojos.OrdenDeTrabajo;

public class TipoDeOrdenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Activity contexto;
    String prioridad = "";
    String estado0 = "";
    String estado1 = "";
    String estado2 = "";
    Boolean siPrioridad = false;
    Boolean siEstado0 = false;
    Boolean siEstado1 = false;
    Boolean siEstado2 = false;
    DatosEmpleado datosEmpleado;
    RadioGroup radioGroupTipo;
    CheckBox checkBoxEstado0;
    CheckBox checkBoxEstado1;
    CheckBox checkBoxEstado2;
    Button buttonCrear;
    int xOffset;
    int yOffset;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_de_orden);

        //----ActionBar---------------------------------------
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setSubtitle(getResources().getText(R.string.subtitulo_titulo_tipo_orden));
        //---------------------------------------------------

        contexto = this;
        xOffset = 100;
        yOffset = 260;
        intent = this.getIntent();
        datosEmpleado = intent.getParcelableExtra("datosDeEmpleado");
        radioGroupTipo = (RadioGroup) findViewById(R.id.radioGroupPrioridad);
        checkBoxEstado0 = (CheckBox) findViewById(R.id.checkBoxEstado0);
        checkBoxEstado1 = (CheckBox) findViewById(R.id.checkBoxEstado1);
        checkBoxEstado2 = (CheckBox) findViewById(R.id.checkBoxEstado2);

        radioGroupTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkId) {

                switch (checkId) {
                    case R.id.radioButtonPrioridad0:
                        prioridad = (String) getResources().getText(R.string.prioridad_muy_alta);
                        siPrioridad = true;
                        break;
                    case R.id.radioButtonPrioridad1:
                        prioridad = (String) getResources().getText(R.string.prioridad_alta);
                        siPrioridad = true;
                        break;
                    case R.id.radioButtonPrioridad2:
                        prioridad = (String) getResources().getText(R.string.prioridad_media);
                        siPrioridad = true;
                        break;
                    case R.id.radioButtonPrioridad3:
                        prioridad = (String) getResources().getText(R.string.prioridad_baja);
                        siPrioridad = true;
                        break;
                }
                mensajeSeleccionado(prioridad);

            }
        });

        checkBoxEstado0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String estado = (String) getResources().getText(R.string.estado_parado);
                if (checkBoxEstado0.isChecked()) {
                    siEstado0 = true;
                    estado0 = estado;
                    mensajeSeleccionado(estado);
                } else {
                    siEstado0 = false;
                    estado0 = "";
                    mensajeDeseleccionado(estado);
                }
            }
        });

        checkBoxEstado1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String estado = (String) getResources().getText(R.string.estado_rotura);
                if (checkBoxEstado1.isChecked()) {
                    siEstado1 = true;
                    estado1 = estado;
                    mensajeSeleccionado(estado);
                } else {
                    siEstado1 = false;
                    estado1 = "";
                    mensajeDeseleccionado(estado);
                }

            }
        });

        checkBoxEstado2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String estado = (String) getResources().getText(R.string.estado_ruido);
                if (checkBoxEstado2.isChecked()) {

                    siEstado2 = true;
                    estado2 = estado;
                    mensajeSeleccionado(estado);
                } else {
                    siEstado2 = false;
                    estado2 = "";
                    mensajeDeseleccionado(estado);
                }

            }
        });

        buttonCrear = (Button) findViewById(R.id.buttonCrear);
        buttonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (siPrioridad && (siEstado0 || siEstado1 || siEstado2)) {
                    String estado = estadoElegido();
                    Intent intent = new Intent(contexto, DescripcionOrdenActivity.class);

                    intent.putExtra("datosEmpleado", datosEmpleado);
                    intent.putExtra("ordenDeTrabajo", setPrioridadEstadoOrdenDeTrabajo(prioridad, estado));

                    startActivity(intent);

                } else {
                    Toast toast = Toast.makeText(contexto, getResources().getText(R.string.elegir_tipo_ciudad), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, xOffset, yOffset);
                    toast.show();
                }
            }
        });

    }
    //---------------------------------------------------------

    private OrdenDeTrabajo setPrioridadEstadoOrdenDeTrabajo(String prioridad, String estado) {
        OrdenDeTrabajo ordenDeTrabajo = new OrdenDeTrabajo();
        ordenDeTrabajo.setPrioridad(prioridad);
        ordenDeTrabajo.setEstado(estado);

        return ordenDeTrabajo;
    }

    private String estadoElegido() {
        String estadoElegido = null;
        String coma1 = ", ";
        String coma2 = ", ";
        if (estado0.isEmpty()) coma1 = "";
        if (estado1.isEmpty()) coma1 = "";
        if (estado1.isEmpty()) coma2 = "";
        if (estado2.isEmpty()) coma2 = "";
        if (!estado0.isEmpty() && !estado2.isEmpty()) coma2 = ", ";
        estadoElegido = estado0 + coma1 + estado1 + coma2 + estado2;
        return estadoElegido;
    }

    private void mensajeSeleccionado(String estado) {
        Toast toast = Toast.makeText(contexto, estado + " " + getText(R.string.seleccionado), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, xOffset, yOffset);
        toast.show();
    }

    private void mensajeDeseleccionado(String estado) {
        Toast toast = Toast.makeText(contexto, estado + " " + getText(R.string.deseleccionado), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, xOffset, yOffset);
        toast.show();
    }

    //Este metodo crea los menús------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, R.integer.indice_icono_ayuda, Menu.NONE, R.string.string_ayuda)
                .setIcon(R.drawable.ic_help_outline_black_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add(Menu.NONE, R.integer.indice_icono_ir_atras, Menu.NONE, R.string.string_ir_atras)
                .setIcon(R.drawable.ic_ir_atras)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.integer.indice_icono_ayuda:
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.string_ayuda),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AyudaActivity.class);
                startActivity(intent);
                break;
            case R.integer.indice_icono_ir_atras:
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.string_ir_atras),
                        Toast.LENGTH_SHORT).show();
                finish();
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
