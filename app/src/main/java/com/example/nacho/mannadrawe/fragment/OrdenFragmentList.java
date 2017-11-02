package com.example.nacho.mannadrawe.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.example.nacho.mannadrawe.activity.EditOrdenActivity;
import com.example.nacho.mannadrawe.proveedorDeContenido.Contrato;
import com.example.nacho.mannadrawe.R;
import com.example.nacho.mannadrawe.crud.CrudOrden;
import com.example.nacho.mannadrawe.adapter.OrdenAdapter;

public class OrdenFragmentList extends ListFragment
        implements LoaderManager.LoaderCallbacks <Cursor> {

    OrdenAdapter mAdapter;
    LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

    ActionMode mActionMode;
    View viewselect;

    public OrdenFragmentList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
     }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mCallbacks = this;
        getLoaderManager().initLoader(0, null, mCallbacks);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(mActionMode!=null){
                    return false;
                }
                mActionMode = getActivity().startActionMode(mActionModeCallback);
                view.setSelected(true);
                viewselect = view;
                mActionMode.setTitle(getResources().getText(R.string.app_name));
                mActionMode.setSubtitle(getResources().getText(R.string.subtitulo_ver));

                view.setBackgroundColor
                        (ContextCompat.getColor(getContext(),R.color.colorPrimary));

                return true;
            }
        });
    }

    ActionMode.Callback  mActionModeCallback =new ActionMode.Callback(){

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

            menu.add(Menu.NONE,R.integer.indice_icono_borrar,Menu.NONE,R.string.string_borrar)
                    .setIcon(R.drawable.ic_delete)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

            menu.add(Menu.NONE,R.integer.indice_icono_editar,Menu.NONE,R.string.string_editar)
                    .setIcon(R.drawable.ic_edit)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);


            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

            switch (menuItem.getItemId()){
                case  R.integer.indice_icono_borrar:
                    int ordenId = (Integer) viewselect.getTag();
                    CrudOrden.delete(getActivity().getContentResolver(),ordenId);
                    mActionMode.finish();

                    break;
                case  R.integer.indice_icono_editar:
                    Intent intent = new Intent(getActivity(), EditOrdenActivity.class);
                    ordenId = (Integer) viewselect.getTag();
                    intent.putExtra(Contrato.Orden._ID,ordenId);
                    startActivity(intent);
                    break;


            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            viewselect.setBackgroundColor
                    (ContextCompat.getColor(getContext(),R.color.colorFondo));

            mActionMode= null;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_orden_list,container,false);

        mAdapter = new OrdenAdapter(getActivity());
        setListAdapter(mAdapter);

        return v;
    }

    @Override
    public Loader <Cursor> onCreateLoader(int id, Bundle args) {

        String [] campos= {
                Contrato.Orden._ID,
                Contrato.Orden.CODIGO_EMPLEADO,
                Contrato.Orden.FECHA,
                Contrato.Orden.CODIGO,
                Contrato.Orden.PRIORIDAD,
                Contrato.Orden.ESTADO,
                Contrato.Orden.UBICACION,
                Contrato.Orden.DESCRIPCION,

        };

        Uri baseUri = Contrato.Orden.CONTENT_URI;
        String selection =null;

        return new CursorLoader(getActivity(),baseUri,campos,selection,null,null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Uri laUriBase = Contrato.Orden.CONTENT_URI;
        data.setNotificationUri(getActivity().getContentResolver(),laUriBase);

        mAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);

    }

}
