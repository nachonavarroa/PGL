package com.example.nacho.mannadrawe.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class OrdenDeTrabajo implements Parcelable {
    int id;
    int codigoEmpleado;
    String fecha;
    long codigo;
    String prioridad;
    String estado;
    String ubicacion;
    String descripcion;

    public OrdenDeTrabajo() {

    }

    public OrdenDeTrabajo(String fecha, long codigo, String prioridad, String estado,
                          String ubicacion, String descripcion) {
        this.fecha = fecha;
        this.codigo = codigo;
        this.prioridad = prioridad;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    protected OrdenDeTrabajo(Parcel in) {
        id = in.readInt();
        fecha = in.readString();
        codigo = in.readLong();
        prioridad = in.readString();
        estado = in.readString();
        ubicacion = in.readString();
        descripcion = in.readString();
    }

    public static final Creator<OrdenDeTrabajo> CREATOR = new Creator<OrdenDeTrabajo>() {
        @Override
        public OrdenDeTrabajo createFromParcel(Parcel in) {
            return new OrdenDeTrabajo(in);
        }

        @Override
        public OrdenDeTrabajo[] newArray(int size) {
            return new OrdenDeTrabajo[size];
        }
    };

    public OrdenDeTrabajo(String prioridad, String estado, String ubicacion, String descripcion) {
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fecha);
        dest.writeLong(codigo);
        dest.writeString(prioridad);
        dest.writeString(estado);
        dest.writeString(ubicacion);
        dest.writeString(descripcion);
    }
}
