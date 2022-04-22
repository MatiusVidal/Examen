package com.example.examen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.examen.entidades.Empleado;
import com.example.examen.modelo.DBempleado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Editar extends AppCompatActivity {

    EditText txtCedula,txtNombre, txtApellido, txtfecha, txtSalario,txtDiscapacidad,txtHorario;
    FloatingActionButton fabEditar, fabEliminar;
    int id = 0;
    Empleado emp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        txtCedula.findViewById(R.id.TxtCedula);
        txtNombre.findViewById(R.id.TxtNombre);
        txtApellido.findViewById(R.id.TxtApellido);
        txtfecha.findViewById(R.id.TxtFecha);
        txtSalario.findViewById(R.id.TxtSalario);
        txtDiscapacidad.findViewById(R.id.TxtDiscapacidad);
        txtHorario.findViewById(R.id.TxtHorario);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DBempleado dbEmpleados = new DBempleado(Editar.this);
        emp = dbEmpleados.ver(id);

        if(emp != null){
            txtCedula.setText(emp.getCedula());
            txtNombre.setText(emp.getNombre());
            txtApellido.setText(emp.getApellido());
            txtfecha.setText(emp.getFecha_contrato());
            txtSalario.setText(String.valueOf(emp.getSalario()));
            txtDiscapacidad.setText(emp.getDiscapacidad());
            txtHorario.setText(emp.getHorario());
            txtCedula.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtApellido.setInputType(InputType.TYPE_NULL);
            txtfecha.setInputType(InputType.TYPE_NULL);
            txtSalario.setInputType(InputType.TYPE_NULL);
            txtDiscapacidad.setInputType(InputType.TYPE_NULL);
            txtHorario.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Editar.this, VerActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Editar.this);
                builder.setMessage("¿Desea eliminar este empleado?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbEmpleados.eliminar(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}