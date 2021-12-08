package com.gms.projetocrudsqlite.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.gms.projetocrudsqlite.Adapter.AdapterTarefa;
import com.gms.projetocrudsqlite.DAO.TarefasDAO;
import com.gms.projetocrudsqlite.Helper.RecyclerItemClickListener;
import com.gms.projetocrudsqlite.Model.Tarefa;
import com.gms.projetocrudsqlite.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gms.projetocrudsqlite.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private RecyclerView recyclerListaTerefas;
    private AdapterTarefa adapterTarefa;
    private List<Tarefa> listaTarefa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerListaTerefas = findViewById(R.id.recyclerListaTarefas);
        recyclerListaTerefas.addOnItemTouchListener(new RecyclerItemClickListener(
                getBaseContext(), recyclerListaTerefas, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Tarefa tarefa = listaTarefa.get(position);
                Intent intent = new Intent(getBaseContext(), ActivityAdicionarTarefa.class);
                intent.putExtra("tarefaSelecionada", tarefa);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Tarefa tarefa = listaTarefa.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirmar exclusão");
                builder.setMessage("Deseja remover a tarefa " + tarefa.getDescricao() + "?");
                builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        (new TarefasDAO(getBaseContext())).remover(tarefa);
                        recuperarTarefas();
                    }
                });
                builder.setNegativeButton("NÃO", null);
                builder.create();
                builder.show();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ActivityAdicionarTarefa.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarTarefas();
    }

    private void recuperarTarefas() {
        listaTarefa = (new TarefasDAO(getBaseContext())).listar();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        adapterTarefa = new AdapterTarefa(listaTarefa, getBaseContext());
        recyclerListaTerefas.setLayoutManager(layoutManager);
        recyclerListaTerefas.setHasFixedSize(true);
        recyclerListaTerefas.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.HORIZONTAL));
        recyclerListaTerefas.setAdapter(adapterTarefa);
    }
}