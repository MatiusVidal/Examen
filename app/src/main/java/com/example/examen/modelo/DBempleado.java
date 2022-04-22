package com.example.examen.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.examen.entidades.Empleado;

import java.util.ArrayList;

public class DBempleado extends DBHelper{

    Context context;

    public DBempleado(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertar(String cedula,String nombre,String apellido,String fecha_contrato,Double salario,String discapacidad,String horario) {

        long id = 0;

        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("cedula", cedula);
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("fecha_contrato", fecha_contrato);
            values.put("salario", salario);
            values.put("discapacidad", discapacidad);
            values.put("horario", horario);

            id = db.insert(TABLE_EMPLEADO, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Empleado> mostrarEmpleado() {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Empleado> listaEmpleados = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EMPLEADO + " ORDER BY nombre ASC", null);

        if (cursor.moveToFirst()) {
            do {
                Empleado em = new Empleado();
                em.setId_empleado(cursor.getInt(0));
                em.setCedula(cursor.getString(1));
                em.setNombre(cursor.getString(2));
                em.setApellido(cursor.getString(3));
                em.setFecha_contrato(cursor.getString(4));
                em.setSalario(cursor.getDouble(5));
                em.setDiscapacidad(cursor.getString(6));
                em.setHorario(cursor.getString(7));
                listaEmpleados.add(em);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listaEmpleados;
    }

    public Empleado ver(int id_empleado) {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Empleado em =null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EMPLEADO + " WHERE id = " + id_empleado + " LIMIT 1", null);

        if (cursor.moveToFirst()) {
            em = new Empleado();
            em.setId_empleado(cursor.getInt(0));
            em.setCedula(cursor.getString(1));
            em.setNombre(cursor.getString(2));
            em.setApellido(cursor.getString(3));
            em.setFecha_contrato(cursor.getString(4));
            em.setSalario(cursor.getDouble(5));
            em.setDiscapacidad(cursor.getString(6));
            em.setHorario(cursor.getString(7));
        }

        cursor.close();

        return em;
    }

    public boolean editar(int id_empleado,String cedula,String nombre,String apellido,String fecha_contrato,Double salario,String discapacidad,String horario) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_EMPLEADO + " SET cedula = '" + cedula + "', nombre = '" + nombre + "', apellido = '" + apellido + "', fecha_contrato = '" + fecha_contrato + "', salario = '" + salario + "', discapacidad = '" + discapacidad + "', horario = '" + horario + "' WHERE id_empleado='" + id_empleado + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminar(int id_empleado) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_EMPLEADO + " WHERE id_empleado = '" + id_empleado + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

}
