package com.gms.projetocrudsqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gms.projetocrudsqlite.Model.Tarefa;
import com.gms.projetocrudsqlite.R;

import java.util.List;

public class AdapterTarefa extends RecyclerView.Adapter<AdapterTarefa.MyViewHolder> {
    List<Tarefa> list;
    Context context;

    public AdapterTarefa(List<Tarefa> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista_tarefas, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarefa tarefa = list.get(position);
        holder.txtTarefa.setText(tarefa.getDescricao());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtTarefa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTarefa = itemView.findViewById(R.id.txtTarefa);
        }
    }
}
