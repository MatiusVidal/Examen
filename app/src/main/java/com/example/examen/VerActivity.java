package com.example.examen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.examen.entidades.Empleado;
import com.example.examen.modelo.DBempleado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtCedula,txtNombre, txtApellido, txtfecha, txtSalario,txtDiscapacidad,txtHorario;
    FloatingActionButton fabEditar, fabEliminar;
    int id = 0;
    Empleado emp;
    boolean correcto = false;
    Button btnGuarda;

    @SuppressLint("RestrictedApi")
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
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DBempleado dbEmpleados = new DBempleado(VerActivity.this);
        emp = dbEmpleados.ver(id);

        if(emp != null){
            txtCedula.setText(emp.getCedula());
            txtNombre.setText(emp.getNombre());
            txtApellido.setText(emp.getApellido());
            txtfecha.setText(emp.getFecha_contrato());
            txtSalario.setText(String.valueOf(emp.getSalario()));
            txtDiscapacidad.setText(emp.getDiscapacidad());
            txtHorario.setText(emp.getHorario());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtCedula.getText().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtApellido.getText().toString().equals("")) {
                    correcto = dbEmpleados.editar(id, txtCedula.getText().toString(), txtNombre.getText().toString(), txtApellido.getText().toString(), txtfecha.getText().toString(), Double.parseDouble(txtSalario.getText().toString()), txtDiscapacidad.getText().toString(), txtHorario.getText().toString());

                    if(correcto){
                        Toast.makeText(VerActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(VerActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(VerActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

}
