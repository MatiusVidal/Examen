package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examen.modelo.DBempleado;

public class Registrar extends AppCompatActivity {

    EditText txtCedula,txtNombre, txtApellido, txtfecha, txtSalario,txtDiscapacidad,txtHorario;
    Button btnGuarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtCedula.findViewById(R.id.TxtCedula);
        txtNombre.findViewById(R.id.TxtNombre);
        txtApellido.findViewById(R.id.TxtApellido);
        txtfecha.findViewById(R.id.TxtFecha);
        txtSalario.findViewById(R.id.TxtSalario);
        txtDiscapacidad.findViewById(R.id.TxtDiscapacidad);
        txtHorario.findViewById(R.id.TxtHorario);
        btnGuarda = findViewById(R.id.Btn_empleado);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txtApellido.getText().toString().equals("")) {

                    DBempleado dbem = new DBempleado(Registrar.this);
                    long id = dbem.insertar(txtCedula.getText().toString(), txtNombre.getText().toString(), txtApellido.getText().toString(), txtfecha.getText().toString(), Double.parseDouble(txtSalario.getText().toString()), txtDiscapacidad.getText().toString(), txtHorario.getText().toString());

                    if (id > 0) {
                        Toast.makeText(Registrar.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(Registrar.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Registrar.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtfecha.setText("");
        txtSalario.setText("");
        txtDiscapacidad.setText("");
        txtHorario.setText("");
    }
}