package com.example.listwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listwork.R;
import com.example.listwork.model.Tarefa;
import com.example.listwork.necessary.TarefaDAO;
import com.google.android.material.textfield.TextInputEditText;

public class AddTarefasActivity extends AppCompatActivity {
    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarefas);

        editTarefa = findViewById(R.id.textTarefa);

        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaselecionada");
        if (tarefaAtual != null) {
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemmenusalvar:
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                String nomeTarefa = editTarefa.getText().toString();
                if (!nomeTarefa.isEmpty()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setNomeTarefa(nomeTarefa);
                    if (tarefaDAO.salvar(tarefa)) {
                        finish();
                        Toast.makeText(getApplicationContext(),
                                "Tarefa Salva com Sucesso",
                                Toast.LENGTH_SHORT).show();
                    }
//                if (tarefaAtual != null){//editando
//                    String nomeTarefa = editTarefa.getText().toString();
//                    if ( !nomeTarefa.isEmpty()){
//                        Tarefa tarefa = new Tarefa();
//                        tarefa.setNomeTarefa(nomeTarefa);
//                        tarefa.setId(tarefaAtual.getId());
//
//                        if(tarefaDAO.atualizar(tarefa)){
//                            finish();
//                            Toast.makeText(getApplicationContext(),
//                                    "Tarefa Atualizada com Sucesso",
//                                    Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(getApplicationContext(),
//                                    "Tarefa Não Atualizada",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                }else {//salvando
//                    String nomeTarefa = editTarefa.getText().toString();
//                    if ( !nomeTarefa.isEmpty()){
//                        Tarefa tarefa = new Tarefa();
//                        tarefa.setNomeTarefa(nomeTarefa);
//                        if(tarefaDAO.salvar(tarefa)){
//                            finish();
//                            Toast.makeText(getApplicationContext(),
//                                    "Tarefa Salva com Sucesso",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
                }
                    break;
                }
                return super.onOptionsItemSelected(item);
        }
    }