package com.example.nacho.mannadrawe.pojos;


import android.os.Parcel;
import android.os.Parcelable;

public class EstadoOrden   implements Parcelable {
    int id;
    int codigoEmpleado;
    long codigoOrden;
    String estado;

    public EstadoOrden() {
    }


    protected EstadoOrden(Parcel in) {
        id = in.readInt ();
        codigoEmpleado = in.readInt ();
        codigoOrden = in.readLong ();
        estado = in.readString ();
    }

    public static final Creator<EstadoOrden> CREATOR = new Creator<EstadoOrden> () {
        @Override
        public EstadoOrden createFromParcel(Parcel in) {
            return new EstadoOrden (in);
        }

        @Override
        public EstadoOrden[] newArray(int size) {
            return new EstadoOrden[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public long getCodigoOrden() {
        return codigoOrden;
    }

    public void setCodigoOrden(long codigoOrden) {
        this.codigoOrden = codigoOrden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt (id);
        dest.writeInt (codigoEmpleado);
        dest.writeLong (codigoOrden);
        dest.writeString (estado);
    }
}
