package com.example.listwork.necessary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.net.PortUnreachableException;

public class DBHelper extends SQLiteOpenHelper {
    public static int VERSION =1;
    public static String NOME_DB = "DB_TAREFAS";
    public static String NOME_TABELA_TAREFA = "tarefas";


    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA_TAREFA
                    + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " nome TEXT NOT NULL ) ; ";
            db.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar tabela_tarefa");

        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar DB" + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            String sql = "DROP TABLE IF EXISTS " + NOME_TABELA_TAREFA + " ;";
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB", "Sucesso na atualização");

        }catch (Exception e){
            Log.i("INFO DB", "Erro na atualização" + e.getMessage());
        }

    }
}
