package com.example.examen.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.Editar;
import com.example.examen.R;
import com.example.examen.entidades.Empleado;

import java.util.ArrayList;

public class ListaEmpleadoAdapter extends RecyclerView.Adapter<ListaEmpleadoAdapter.EmpleadoViewHolder>{
    ArrayList<Empleado> listaEmpleado;
    ArrayList<Empleado> listaOriginalEm;

    public ListaEmpleadoAdapter(ArrayList<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
        listaOriginalEm = new ArrayList<>();
        listaOriginalEm.addAll(listaEmpleado);
    }

    @NonNull
    @Override
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_empleado, null, false);
        return new EmpleadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder holder, int position) {
        holder.viewCedula.setText(listaEmpleado.get(position).getCedula());
        holder.viewNombre.setText(listaEmpleado.get(position).getNombre());
        holder.viewApellido.setText(listaEmpleado.get(position).getApellido());
        holder.viewFecha.setText(listaEmpleado.get(position).getFecha_contrato());
        holder.viewSalario.setText(String.valueOf(listaEmpleado.get(position).getSalario()));
        holder.viewDiscapacidad.setText(listaEmpleado.get(position).getDiscapacidad());
        holder.viewHorario.setText(listaEmpleado.get(position).getHorario());
    }

    @Override
    public int getItemCount() {
        return listaEmpleado.size();
    }



    public class EmpleadoViewHolder extends RecyclerView.ViewHolder {

        TextView viewCedula, viewNombre, viewApellido,viewFecha, viewSalario, viewDiscapacidad, viewHorario ;

        public EmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewCedula=itemView.findViewById(R.id.ViewCedula);
            viewNombre = itemView.findViewById(R.id.ViewNombre);
            viewApellido = itemView.findViewById(R.id.ViewApellido);
            viewFecha = itemView.findViewById(R.id.ViewFecha);
            viewSalario = itemView.findViewById(R.id.ViewSalario);
            viewDiscapacidad = itemView.findViewById(R.id.ViewDiscapacidad);
            viewHorario = itemView.findViewById(R.id.ViewHorario);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, Editar.class);
                    intent.putExtra("ID", listaEmpleado.get(getAdapterPosition()).getId_empleado());
                    context.startActivity(intent);
                }
            });
        }
    }
}
