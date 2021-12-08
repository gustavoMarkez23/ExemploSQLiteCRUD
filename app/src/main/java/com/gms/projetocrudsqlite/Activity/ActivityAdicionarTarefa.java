package com.gms.projetocrudsqlite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.gms.projetocrudsqlite.DAO.TarefasDAO;
import com.gms.projetocrudsqlite.Model.Tarefa;
import com.gms.projetocrudsqlite.R;

public class ActivityAdicionarTarefa extends AppCompatActivity {
    EditText edtDescricao;
    Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        edtDescricao = findViewById(R.id.edtDescricao);

        tarefaSelecionada = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");
        if (tarefaSelecionada != null) {
            edtDescricao.setText(tarefaSelecionada.getDescricao());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_salvar) {
            TarefasDAO tarefasDAO = new TarefasDAO(getBaseContext());
            Tarefa tarefa = new Tarefa();
            tarefa.setDescricao(edtDescricao.getText().toString());
            if (tarefaSelecionada != null) {
                tarefa.setId(tarefaSelecionada.getId());
                tarefasDAO.alterar(tarefa);
            }else{
                tarefasDAO.salvar(tarefa);
            }
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}