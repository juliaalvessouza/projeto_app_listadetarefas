package com.example.listwork.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listwork.R;
import com.example.listwork.model.Tarefa;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Tarefa> listaTarefas;

    public Adapter(List<Tarefa> lista) {
       this.listaTarefas = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.lista_tarefa_adapter,parent,false);


        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Tarefa tarefa = listaTarefas.get(position);
        holder.tarefa.setText(tarefa.getNomeTarefa());

    }

    @Override
    public int getItemCount() {
        return this.listaTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefa = itemView.findViewById(R.id.textViewTarefa);
        }
    }
}
