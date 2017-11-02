package com.example.nacho.mannadrawe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.nacho.mannadrawe.R;
import com.example.nacho.mannadrawe.adapter.DrawerAdapter;


public class DrawerMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final int RETARDO_SALIDA = 2200; // 2.2segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main_drawer);

        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        toolbar.setSubtitle (getText (R.string.subtititulo_menu));
        toolbar.setLogo (R.mipmap.ic_launcher);


        FloatingActionButton fab = (FloatingActionButton) findViewById (R.id.fab_app_bar_drawe);
        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Toast.makeText (getApplicationContext (), getResources ()
                        .getString (R.string.toast_app_close), Toast.LENGTH_SHORT).show ();
                new Handler ().postDelayed (new Runnable () {
                    public void run() {
                        // Cuando pasen los 2.2 segundo, se cierra la aplicación
                        finish ();
                    }

                    ;
                }, RETARDO_SALIDA);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById (R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener (toggle);
        toggle.syncState ();

        NavigationView navigationView = (NavigationView) findViewById (R.id.nav_view);
        navigationView.setNavigationItemSelectedListener (this);

        //TabLayout----------------------------------
        DrawerAdapter drawerAdapter = new DrawerAdapter (getSupportFragmentManager ());

        ViewPager viewPager = (ViewPager) findViewById (R.id.view_pager_activity_drawer);
        viewPager.setAdapter (drawerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById (R.id.tabs_layout_app_bar_drawer);
        tabLayout.setupWithViewPager (viewPager);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById (R.id.drawer_layout);
        if (drawer.isDrawerOpen (GravityCompat.START)) {
            drawer.closeDrawer (GravityCompat.START);
        } else {
            super.onBackPressed ();
        }
    }

    //Este metodo crea los menús
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add (Menu.NONE, R.integer.indice_icono_ayuda, Menu.NONE, R.string.string_ayuda)
                .setIcon (R.drawable.ic_help_outline_black_24dp)
                .setShowAsAction (MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add (Menu.NONE, R.integer.indice_icono_oup_app, Menu.NONE, R.string.out_app)
                .setIcon (R.drawable.ic_boton_apagado)
                .setShowAsAction (MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId ();

        switch (id) {

            case R.integer.indice_icono_ayuda:
                Toast.makeText (this, R.string.string_ayuda, Toast.LENGTH_LONG).show ();
                Intent intent = new Intent (getApplicationContext (), AyudaActivity.class);
                startActivity (intent);
                break;
            case R.integer.indice_icono_oup_app:
                Toast.makeText (getApplicationContext (), getResources ()
                        .getString (R.string.toast_app_close), Toast.LENGTH_SHORT).show ();
                new Handler ().postDelayed (new Runnable () {
                    public void run() {
                        // Cuando pasen los 2.2 segundo, se cierra la aplicación
                        finish ();
                    }

                    ;
                }, RETARDO_SALIDA);
                break;
        }
        return super.onOptionsItemSelected (item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId ();

        if (id == R.id.nav_generar_orden) {

            Intent intent = new Intent (getApplicationContext (), GenerarDatosEmpleadoActivity.class);
            startActivity (intent);

        } else if (id == R.id.nav_ver_ordenes) {

            Intent intent = new Intent (getApplicationContext (), VerOrdenesActivity.class);
            startActivity (intent);
        } else if (id == R.id.nav_ver_estado_ordenes) {

            Intent intent = new Intent (getApplicationContext (), VerEstadoOrdenesActivity.class);
            startActivity (intent);

        } else if (id == R.id.nav_autor) {

            Intent intent = new Intent (getApplicationContext (), AutorActivity.class);
            startActivity (intent);

        } else if (id == R.id.nav_ayuda) {

            Intent intent = new Intent (getApplicationContext (), AyudaActivity.class);
            startActivity (intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById (R.id.drawer_layout);
        drawer.closeDrawer (GravityCompat.START);
        return true;
    }
}
