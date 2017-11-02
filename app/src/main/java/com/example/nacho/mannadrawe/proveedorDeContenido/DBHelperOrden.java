package com.example.nacho.mannadrawe.proveedorDeContenido;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelperOrden extends SQLiteOpenHelper{

    public DBHelperOrden(Context context, String name,
                         SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "
                +Contrato.Orden.NOMBRE_TABLA
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contrato.Orden.CODIGO_EMPLEADO+  " INTEGER ,"
                + Contrato.Orden.FECHA+  " TEXT ,"
                + Contrato.Orden.CODIGO + " LONG , "
                + Contrato.Orden.PRIORIDAD + " TEXT , "
                + Contrato.Orden.ESTADO + " TEXT , "
                + Contrato.Orden.UBICACION + " TEXT , "
                + Contrato.Orden.DESCRIPCION + " TEXT "
                +"); "
        );

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+Contrato.Orden.NOMBRE_TABLA);

        onCreate(db);
    }
}
