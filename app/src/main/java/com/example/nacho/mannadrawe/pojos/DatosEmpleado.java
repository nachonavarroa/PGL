package com.example.nacho.mannadrawe.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class DatosEmpleado  implements Parcelable{
    String nombre;
    String apellido1;
    String apellido2;
    String dni;
    String departamento;
    String categoria;
    int codigo;

    public DatosEmpleado() {
    }

    public DatosEmpleado(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public DatosEmpleado(String nombre, String apellido1, String apellido2,
                         String dni, String departamento, String categoria, int codigo) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.departamento = departamento;
        this.categoria = categoria;
        this.codigo = codigo;
    }

    protected DatosEmpleado(Parcel in) {
        nombre = in.readString();
        apellido1 = in.readString();
        apellido2 = in.readString();
        dni = in.readString();
        departamento = in.readString();
        categoria = in.readString();
        codigo = in.readInt();
    }

    public static final Creator<DatosEmpleado> CREATOR = new Creator<DatosEmpleado>() {
        @Override
        public DatosEmpleado createFromParcel(Parcel in) {
            return new DatosEmpleado(in);
        }

        @Override
        public DatosEmpleado[] newArray(int size) {
            return new DatosEmpleado[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellido1);
        dest.writeString(apellido2);
        dest.writeString(dni);
        dest.writeString(departamento);
        dest.writeString(categoria);
        dest.writeInt(codigo);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
