package com.example.nacho.mannadrawe.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nacho.mannadrawe.R;
import com.example.nacho.mannadrawe.activity.AutorActivity;
import com.example.nacho.mannadrawe.activity.AyudaActivity;
import com.example.nacho.mannadrawe.activity.GenerarDatosEmpleadoActivity;
import com.example.nacho.mannadrawe.activity.VerEstadoOrdenesActivity;
import com.example.nacho.mannadrawe.activity.VerOrdenesActivity;


public class DrawerFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int mPosition;
    private OnFragmentInteractionListener mListener;
    public DrawerFragment() {

    }

    public static DrawerFragment newInstance(int position) {
        DrawerFragment fragment = new DrawerFragment ();
        Bundle args = new Bundle ();
        args.putInt (ARG_POSITION, position);
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        if (getArguments () != null) {
            mPosition = getArguments ().getInt (ARG_POSITION);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =null;

        switch (mPosition){
            case 0:
                view =inflater.inflate (R.layout.fragment_drawer_orden, container, false);
                TextView textViewfragmentoTituloOt =
                        view.findViewById(R.id.textView_fragmen_drawer_titulo_orden);
                ImageButton imageButtonOt =
                        view.findViewById (R.id.imageButton_fragment_ot);
                textViewfragmentoTituloOt .setText(getResources().getString(R.string.generar_ordende_trabajo));
                imageButtonOt.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (getContext (), GenerarDatosEmpleadoActivity.class);
                        startActivity (intent);
                    }
                });
                break;
            case 1:
                view =inflater.inflate (R.layout.fragment_drawer_ver, container, false);
                TextView textViewfragmentoTituloVerOt =
                        view.findViewById(R.id.textView_fragmen_drawer_titulo_ver);
                ImageButton imageButtonVerOt =
                        view.findViewById (R.id.imageButton_fragment_ver);
                textViewfragmentoTituloVerOt.setText(getResources().getString(R.string.ver_ordenes));
                imageButtonVerOt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent (getContext (), VerOrdenesActivity.class);
                        startActivity (intent);

                    }
                });


                break;
            case 2:
                view =inflater.inflate (R.layout.fragment_drawer_ver_estado, container, false);
                TextView textViewfragmentoTituloVerEstadoOt =
                        view.findViewById(R.id.textView_fragmen_drawer_titulo_ver_estado);
                ImageButton imageButtonVerEstadoOt =
                        view.findViewById (R.id.imageButton_fragment_ver_estado);
                textViewfragmentoTituloVerEstadoOt.setText(getResources().getString(R.string.ver_estado_ordenes));
                imageButtonVerEstadoOt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent (getContext (), VerEstadoOrdenesActivity.class);
                        startActivity (intent);

                    }
                });


                break;
            case 3:
                view =inflater.inflate (R.layout.fragment_drawer_autor, container, false);
                TextView textViewfragmentoTituloautor =
                        view.findViewById(R.id.textView_fragmen_drawer_titulo_autor);
                ImageButton imageButtonAutor =
                        view.findViewById (R.id.imageButton_fragment_autor);
                textViewfragmentoTituloautor .setText(getResources().getString(R.string.subtitulo_autor));
                imageButtonAutor.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (getContext (), AutorActivity.class);
                        startActivity (intent);
                    }
                });


                break;
            case 4:
                view =inflater.inflate (R.layout.fragment_drawer_ayuda, container, false);
                TextView textViewfragmentoTituloAyuda =
                        view.findViewById(R.id.textView_fragmen_drawer_titulo_ayuda);
                ImageButton imageButtonAyuda =
                        view.findViewById (R.id.imageButton_fragment_ayuda);
                textViewfragmentoTituloAyuda .setText(getResources().getString(R.string.string_ayuda));
                imageButtonAyuda.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (getContext (), AyudaActivity.class);
                        startActivity (intent);
                    }
                });

                break;
        }


        return  view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction (uri);
        }
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach (context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException (context.toString ()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }
*/

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
