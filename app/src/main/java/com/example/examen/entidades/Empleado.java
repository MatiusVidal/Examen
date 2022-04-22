package com.example.examen.entidades;

import android.content.Context;
import android.database.Cursor;

import com.example.examen.modelo.DBHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Empleado {
    public int id_empleado;
    public String cedula;
    public String nombre;
    public String apellido;
    public String fecha_contrato ;
    public Double salario;
    public String discapacidad;
    public String horario;

    ArrayList<Empleado> list;

    public Empleado() {
    }

    public Empleado(int id_empleado, String cedula, String nombre, String apellido, String fecha_contrato, Double salario, String discapacidad, String horario) {
        this.id_empleado = id_empleado;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_contrato = fecha_contrato;
        this.salario = salario;
        this.discapacidad = discapacidad;
        this.horario = horario;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_contrato() {
        return fecha_contrato;
    }

    public void setFecha_contrato(String fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void guardar(Context contexto){
        DBHelper dbDatos = new DBHelper(contexto);
        String nsql="INSERT INTO t_empleado (id_empleado, cedula, nombre, apellido, fecha_contrato, salario, discapacidad, horario) "+
                "VALUES ("+getId_empleado()+",'"+getCedula()+"','"+getNombre()+"' ,'"+getApellido()+"','"+getFecha_contrato()+"','"+getSalario()+"','"+getDiscapacidad()+"',"+getHorario()+")";
        dbDatos.noQuery(nsql);
        dbDatos.close();
    }

    public void editar(Context context) {
        DBHelper db = new DBHelper(context);
        String noSql = "UPDATE t_empleado set cedula= '" + getCedula() + "',nombre= '" + getNombre() + "',apellido= '" + getApellido() + "', fecha_contrato= '" + getFecha_contrato() + "', salario = '" + getSalario() + "', discapacidad= '" + getDiscapacidad() + "', horario =  '" + getHorario() + "'" +
                " where id_empleado= " + getId_empleado() + ";";
        db.noQuery(noSql);
        db.close();
    }

    public void eliminar(Context context) {
        DBHelper db = new DBHelper(context);
        String noSql = "DELETE FROM t_empleado where id_empleado= " + getId_empleado();
        db.noQuery(noSql);
        db.close();
    }

    public ArrayList<Empleado> lista_Empleado(Context context){
        DBHelper db = new DBHelper(context);
        String sql = "SELECT * FROM t_empleado";
        list = new ArrayList<Empleado>();
        Cursor cursor = db.query(sql);
        while(cursor.moveToNext()){
            Empleado em = new Empleado();
            em.setId_empleado(cursor.getInt(0));
            em.setCedula(cursor.getString(1));
            em.setNombre(cursor.getString(2));
            em.setApellido(cursor.getString(3));
            em.setFecha_contrato(cursor.getString(4));
            em.setSalario(cursor.getDouble(5));
            em.setDiscapacidad(cursor.getString(6));
            em.setHorario(cursor.getString(7));
            list.add(em);
        }
        return list;
    }

}
