package com.example.nacho.mannadrawe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nacho.mannadrawe.R;
import com.example.nacho.mannadrawe.crud.CrudOrden;
import com.example.nacho.mannadrawe.pojos.OrdenDeTrabajo;
import com.example.nacho.mannadrawe.proveedorDeContenido.Contrato;

public class EditOrdenActivity extends AppCompatActivity {
    Activity contexto;
    Intent intent;

    RadioGroup radioGroupTipo;
    CheckBox checkBoxEstado0;
    CheckBox checkBoxEstado1;
    CheckBox checkBoxEstado2;

    EditText editTextUbicacion;
    EditText editTextDescripcion;

    String estado0 = "";
    String estado1 = "";
    String estado2 = "";

    Boolean siEstado0 = false;
    Boolean siEstado1 = false;
    Boolean siEstado2 = false;

    String prioridad = "";
    Boolean siPrioridad = false;

    Boolean validadarOk;

    int xOffset;
    int yOffset;

    int ordenId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_orden);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contexto = this;
        xOffset = 100;
        yOffset = 660;

        //ActionBar----------------------------
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.app_name);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setSubtitle(getResources().getText(R.string.string_editar));
        //----------------------------

        //Floating Button-----------------------
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_activity_edit_orden);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });
        //-----------------------
        radioGroupTipo = (RadioGroup) findViewById(R.id.radioGroupPrioridadEdit);
        checkBoxEstado0 = (CheckBox) findViewById(R.id.checkBoxEstado0Edit);
        checkBoxEstado1 = (CheckBox) findViewById(R.id.checkBoxEstado1Edit);
        checkBoxEstado2 = (CheckBox) findViewById(R.id.checkBoxEstado2Edit);

        editTextUbicacion = (EditText) findViewById(R.id.editTextUbicacionEdit);
        editTextDescripcion = (EditText) findViewById(R.id.editTextDescripcionEdit);


        ordenId = this.getIntent().getExtras().getInt(Contrato.Orden._ID);
        OrdenDeTrabajo orden = CrudOrden.readRecord(getContentResolver(), ordenId);

        editTextUbicacion.setText(orden.getUbicacion());
        editTextDescripcion.setText(orden.getDescripcion());

        prioridadRadioButton(orden);
        estadoCheckBox(orden);

        radioGroupTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkId) {

                switch (checkId) {
                    case R.id.radioButtonPrioridad0Edit:
                        prioridad = (String) getResources().getText(R.string.prioridad_muy_alta);
                        siPrioridad = true;
                        break;
                    case R.id.radioButtonPrioridad1Edit:
                        prioridad = (String) getResources().getText(R.string.prioridad_alta);
                        siPrioridad = true;
                        break;
                    case R.id.radioButtonPrioridad2Edit:
                        prioridad = (String) getResources().getText(R.string.prioridad_media);
                        siPrioridad = true;
                        break;
                    case R.id.radioButtonPrioridad3Edit:
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

    }
    //Menús-----------------------------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, R.integer.indice_icono_ayuda, Menu.NONE, R.string.string_ayuda)
                .setIcon(R.drawable.ic_help_outline_black_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
                        | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add(Menu.NONE, R.integer.indice_icono_ir_atras, Menu.NONE, R.string.string_ir_atras)
                .setIcon(R.drawable.ic_ir_atras)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
                        | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add(Menu.NONE, R.integer.indice_icono_guardar, Menu.NONE, R.string.string_guardar)
                .setIcon(R.drawable.ic_save)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
                        | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.integer.indice_icono_ir_atras:
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.string_ir_atras),
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.integer.indice_icono_ayuda:
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.string_ayuda),
                        Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), AyudaActivity.class);
                startActivity(intent);
                break;
            case R.integer.indice_icono_guardar:
                guardar();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //Metodos actulizan estados de checkBox y Radiobutton con respecto a la orden editada-----------

    private void estadoCheckBox(OrdenDeTrabajo orden) {
        String estadoExtraido = orden.getEstado();
        String estadoParado = getResources().getString(R.string.estado_parado);
        String estadoRotura = getResources().getString(R.string.estado_rotura);
        String estadoRuido = getResources().getString(R.string.estado_ruido);
        String estadoParadoRotura = getResources().getString(R.string.estado_parado_rotura);
        String estadoParadoRoturaRuido = getResources().getString(R.string.estado_parado_rotura_ruido);
        String estadoParadoRuido = getResources().getString(R.string.estado_parado_ruido);
        String estadoRoturaRuido = getResources().getString(R.string.estado_rotura_ruido);

        if (estadoExtraido.equals(estadoParado)) {
            checkBoxEstado0.setChecked(true);
            estado0 = estadoParado;
            siEstado0 = true;
        } else if (estadoExtraido.equals(estadoRotura)) {
            checkBoxEstado1.setChecked(true);
            estado1 = estadoRotura;
            siEstado1 = true;
        } else if (estadoExtraido.equals(estadoRuido)) {
            checkBoxEstado2.setChecked(true);
            estado2 = estadoRuido;
            siEstado2 = true;
        } else if (estadoExtraido.equals(estadoParadoRotura)) {
            checkBoxEstado0.setChecked(true);
            checkBoxEstado1.setChecked(true);
            estado0 = estadoParado;
            estado1 = estadoRotura;
            siEstado0 = true;
            siEstado1 = true;
        } else if (estadoExtraido.equals(estadoParadoRoturaRuido)) {
            checkBoxEstado0.setChecked(true);
            checkBoxEstado1.setChecked(true);
            checkBoxEstado2.setChecked(true);
            estado0 = estadoParado;
            estado1 = estadoRotura;
            estado2 = estadoRuido;
            siEstado0 = true;
            siEstado1 = true;
            siEstado2 = true;
        } else if (estadoExtraido.equals(estadoParadoRuido)) {
            checkBoxEstado0.setChecked(true);
            checkBoxEstado2.setChecked(true);
            estado0 = estadoParado;
            estado2 = estadoRuido;
            siEstado0 = true;
            siEstado2 = true;

        } else if (estadoExtraido.equals(estadoRoturaRuido)) {
            checkBoxEstado1.setChecked(true);
            checkBoxEstado2.setChecked(true);
            estado1 = estadoRotura;
            estado2 = estadoRuido;
            siEstado1 = true;
            siEstado2 = true;
        }
    }

    private void prioridadRadioButton(OrdenDeTrabajo orden) {
        String prioridadExtraida = orden.getPrioridad();

        String prioridadMuyalta = getResources().getString(R.string.prioridad_muy_alta);
        String prioridadAlta = getResources().getString(R.string.prioridad_alta);
        String prioridadMadia = getResources().getString(R.string.prioridad_media);
        String prioridadBaja = getResources().getString(R.string.prioridad_baja);

        if (prioridadExtraida.equals(prioridadMuyalta)) {
            RadioButton radioButton = (RadioButton) findViewById(R.id.radioButtonPrioridad0Edit);
            radioButton.setChecked(true);
            prioridad = prioridadExtraida;
            siPrioridad = true;

        } else if (prioridadExtraida.equals(prioridadAlta)) {
            RadioButton radioButton = (RadioButton) findViewById(R.id.radioButtonPrioridad1Edit);
            radioButton.setChecked(true);
            prioridad = prioridadExtraida;
            siPrioridad = true;

        } else if (prioridadExtraida.equals(prioridadMadia)) {
            RadioButton radioButton = (RadioButton) findViewById(R.id.radioButtonPrioridad2Edit);
            radioButton.setChecked(true);
            prioridad = prioridadExtraida;
            siPrioridad = true;

        } else if (prioridadExtraida.equals(prioridadBaja)) {
            RadioButton radioButton = (RadioButton) findViewById(R.id.radioButtonPrioridad3Edit);
            radioButton.setChecked(true);
            prioridad = prioridadExtraida;
            siPrioridad = true;
        }

    }

    //----------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------------
    private boolean validarCampo() {
        validadarOk = true;
        editTextUbicacion.setError(null);
        editTextDescripcion.setError(null);
        String ubicacion = editTextUbicacion.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();

        if (TextUtils.isEmpty(ubicacion)) {
            editTextUbicacion.setError(getString(R.string.campo_vacio));
            editTextUbicacion.requestFocus();
            validadarOk = false;
            return validadarOk;

        }
        if (TextUtils.isEmpty(descripcion)) {
            editTextDescripcion.setError(getString(R.string.campo_vacio));
            editTextDescripcion.requestFocus();
            validadarOk = false;
            return validadarOk;
        }

        return validadarOk;

    }

    private void guardar() {
        if (validarCampo()) {
            OrdenDeTrabajo orden = new OrdenDeTrabajo();
            orden.setId(ordenId);

            if (siPrioridad && (siEstado0 || siEstado1 || siEstado2)) {
                String estado = estadoElegido();
                orden.setEstado(estado);
                orden.setPrioridad(prioridad);
                orden.setUbicacion(editTextUbicacion.getText().toString());
                orden.setDescripcion(editTextDescripcion.getText().toString());
                CrudOrden.update(getContentResolver(), orden);
                intent = new Intent(contexto, VerOrdenesActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast toast = Toast.makeText(contexto, getResources().getText(R.string.elegir_tipo_ciudad), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, xOffset, yOffset);
                toast.show();
            }
        }

    }


}
