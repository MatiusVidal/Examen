package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.examen.adaptadores.ListaEmpleadoAdapter;
import com.example.examen.entidades.Empleado;
import com.example.examen.modelo.DBempleado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ArrayList<Empleado> listaArrayEmpleado;
    FloatingActionButton fabNuevo;
    RecyclerView listaEmpleados;
    ListaEmpleadoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaEmpleados = findViewById(R.id.ListaEmpleado);
        fabNuevo = findViewById(R.id.fvNuevo);
        listaEmpleados.setLayoutManager(new LinearLayoutManager(this));
        DBempleado dbemp = new DBempleado(MainActivity.this);
        listaArrayEmpleado= new ArrayList<>();

        adapter = new ListaEmpleadoAdapter(dbemp.mostrarEmpleado());
        listaEmpleados.setAdapter(adapter);

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, Registrar.class);
        startActivity(intent);
    }
}