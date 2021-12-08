package com.gms.projetocrudsqlite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gms.projetocrudsqlite.Helper.DbHelper;
import com.gms.projetocrudsqlite.Model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefasDAO {
    private SQLiteDatabase write, read;

    public TarefasDAO(Context context) {
        DbHelper db = new DbHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    public Boolean salvar(Tarefa tarefa) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("descricao", tarefa.getDescricao());
            write.insert(DbHelper.TABELA_TAREFAS, null, cv);
        } catch (Exception e) {
            Log.e("INFO_TAREFASDAO", e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean alterar(Tarefa tarefa) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("id", tarefa.getId());
            cv.put("descricao", tarefa.getDescricao());
            String[] args = {String.valueOf(tarefa.getId())};
            write.update(DbHelper.TABELA_TAREFAS, cv, "id = ?", args);
        } catch (Exception e) {
            Log.e("INFO_TAREFASDAO", e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean remover(Tarefa tarefa) {
        try {
            String[] args = {String.valueOf(tarefa.getId())};
            write.delete(DbHelper.TABELA_TAREFAS, "id = ?", args);
        } catch (Exception e) {
            Log.e("INFO_TAREFASDAO", e.getMessage());
            return false;
        }
        return true;
    }

    public List<Tarefa> listar() {
        List<Tarefa> list = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + ";";
        Cursor cursor = read.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(new Tarefa(
                    cursor.getInt((int) cursor.getColumnIndex("id")),
                    cursor.getString((int) cursor.getColumnIndex("descricao"))
            ));
        }
        return list;
    }
}
