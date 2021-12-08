package com.gms.projetocrudsqlite.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "db_tarefas";
    public static String TABELA_TAREFAS = "tarefas";

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descricao text NOT NULL);");
        }catch (Exception e){
            Log.i("INFO_DB", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
         * CASO PRECISE ATUALIZAR SEU BD, BASTA ALTERAR A VERSÃO
         * EX: "1.1", "2"
         * O PRÓPRIO APP ENTENDERÁ O QUE DEVE SER FEITO
         *
         * EXEMPLO DE CÓDIGO
         *
         *  String sql = "DROP TABLE IF EXISTS " + TABELA_TAREFAS + " ;" ;
         *
         * try {
         *     db.execSQL( sql );
         *     onCreate(db);
         *     Log.i("INFO DB", "Sucesso ao atualizar App" );
         * }catch (Exception e){
         *     Log.i("INFO DB", "Erro ao atualizar App" + e.getMessage() );
         * }
         */
    }
}
