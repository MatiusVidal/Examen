package com.example.examen.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "empleado.db";
    public static final String TABLE_EMPLEADO = "t_empleado";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EMPLEADO + "(" +
                "id_empleado INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cedula TEXT NOT NULL," +
                "nombre TEXT," +
                "apellido TEXT," +
                "fecha_contrato TEXT," +
                "salario NUMERIC," +
                "discapacidad TEXT," +
                "horario TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void noQuery(String nsql){
        this.getWritableDatabase().execSQL(nsql);
    }

    public Cursor query(String sql){
        return this.getReadableDatabase().rawQuery(sql,null);

    }
}
