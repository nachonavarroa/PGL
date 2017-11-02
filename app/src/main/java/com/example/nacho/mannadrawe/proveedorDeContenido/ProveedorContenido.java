package com.example.nacho.mannadrawe.proveedorDeContenido;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;


public class ProveedorContenido extends ContentProvider {

    private final  static  int ORDENES_ONE_REG =1;
    private final  static  int ORDENES_ALL_REGS =2;

    private SQLiteDatabase sqlDB;
    public DBHelperOrden dbHelper;

    final  String DATABASE_NAME = "Manna.db";
    final  int DATABASE_VERSION = 1;
    private static final String ORDEN_TABLE_NAME = "Orden";

    private  static  final  UriMatcher mUriMatcher = new UriMatcher(0);
    private  static  final SparseArray <String>  sMimeTypes;


    static {
        mUriMatcher.addURI(Contrato.AUTHORITY,Contrato.Orden.NOMBRE_TABLA, ORDENES_ALL_REGS);
        mUriMatcher.addURI(Contrato.AUTHORITY,Contrato.Orden.NOMBRE_TABLA+"/#", ORDENES_ONE_REG);

        sMimeTypes = new SparseArray <String>();

        sMimeTypes.put(ORDENES_ALL_REGS,"vnd.android.cursor.dir/vnd."+Contrato.AUTHORITY +"."
                +Contrato.Orden.NOMBRE_TABLA);
        sMimeTypes.put(ORDENES_ONE_REG,"vnd.android.cursor.item/vnd."+Contrato.AUTHORITY +"."
                +Contrato.Orden.NOMBRE_TABLA);
    }
    public ProveedorContenido() {

            }


    @Override
    public boolean onCreate() {
        dbHelper = new DBHelperOrden(getContext(),DATABASE_NAME,null,DATABASE_VERSION);

        return (dbHelper == null ? false :true);
    }

//    @Override
//    public int delete(Uri uri, String selection, String[] selectionArgs) {
//        // Implement this to handle requests to delete one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
    sqlDB = dbHelper.getWritableDatabase();
    // insert record in user table and get the row number of recently inserted record

    String table = "";
    switch (mUriMatcher.match(uri)) {
        case ORDENES_ONE_REG:
            if (null == selection) selection = "";
            selection += Contrato.Orden._ID + " = "
                    + uri.getLastPathSegment();
            table = ORDEN_TABLE_NAME;
            break;
        case ORDENES_ALL_REGS:
            table = ORDEN_TABLE_NAME;
            break;
    }
    int rows = sqlDB.delete(table, selection, selectionArgs);
    if (rows > 0) {
        getContext().getContentResolver().notifyChange(uri, null);
        return rows;
    }
    throw new SQLException("Failed to delete row into " + uri);
}

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        sqlDB = dbHelper.getWritableDatabase();

        String table = "";
        switch (mUriMatcher.match(uri)) {
            case ORDENES_ALL_REGS:
                table = Contrato.Orden.NOMBRE_TABLA;
                break;
        }

        long rowId = sqlDB.insert(table, "", values);

        if (rowId > 0) {
            Uri rowUri = ContentUris.appendId(
                    uri.buildUpon(), rowId).build();
            getContext().getContentResolver().notifyChange(rowUri, null);
            return rowUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }



    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = null;

        switch (mUriMatcher.match(uri)) {
            case ORDENES_ONE_REG:
                if(null==selection) selection="";
                selection += Contrato.Orden._ID+" = "+ uri.getLastPathSegment();
                qb.setTables (Contrato.Orden.NOMBRE_TABLA);

                break;
            case ORDENES_ALL_REGS:
                if(TextUtils.isEmpty(sortOrder)) sortOrder = Contrato.Orden._ID+" desc";
                qb.setTables (Contrato.Orden.NOMBRE_TABLA);

                break;
        }

        Cursor c;
        c= qb.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        c.setNotificationUri(getContext().getContentResolver(),uri);

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        sqlDB = dbHelper.getWritableDatabase();
        // insert record in user table and get the row number of recently inserted record

        String table = "";
        switch (mUriMatcher.match(uri)) {
            case ORDENES_ONE_REG:
                if (null == selection) selection = "";
                selection += Contrato.Orden._ID + " = "
                        + uri.getLastPathSegment();
                table = ORDEN_TABLE_NAME;
                break;
            case ORDENES_ALL_REGS:
                table = ORDEN_TABLE_NAME;
                break;
        }

        int rows = sqlDB.update(table, values, selection, selectionArgs);
        if (rows > 0) {
            getContext().getContentResolver().notifyChange(uri, null);

            return rows;
        }
        throw new SQLException("Failed to update row into " + uri);
    }
}
