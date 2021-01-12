package com.example.listwork.necessary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import com.example.listwork.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO{
    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TarefaDAO(Context context){
        DBHelper db = new DBHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", tarefa.getNomeTarefa());
        try {
            write.insert(DBHelper.NOME_TABELA_TAREFA, null, contentValues);
            Log.i("INFO","Tarefa salva com sucesso");
        }catch (Exception e){
            Log.i("INFO", "Erro ao salvar a tarefa" + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.NOME_TABELA_TAREFA + " ;";
        Cursor cursor = read.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Tarefa tarefa = new Tarefa();
                tarefa.setId(cursor.getLong(cursor.getColumnIndex("id")));
                tarefa.setNomeTarefa(cursor.getString(cursor.getColumnIndex("nome")));

                tarefas.add(tarefa);
            }while (cursor.moveToNext());
        }else{
            Log.i("Info DB", "Sem Registro");
        }

        return tarefas;
    }
}
