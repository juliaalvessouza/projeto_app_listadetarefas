package com.example.listwork.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.listwork.R;
import com.example.listwork.necessary.RecyclerItemClickListener;
import com.example.listwork.adapter.Adapter;
import com.example.listwork.model.Tarefa;
import com.example.listwork.necessary.TarefaDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Tarefa> listaTarefa = new ArrayList<>();
    private Tarefa tarefaselecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddTarefasActivity.class);
                startActivity(intent);
            }
        });
    }

    public void carregarListaTarefas (){
//lista de tarefas relacionado ao banco de dados
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listaTarefa = tarefaDAO.listar();


//configuração do Adapter
        adapter = new Adapter(listaTarefa);

//configuração do Recycler View
        recyclerView = findViewById(R.id.RecyclerViewList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

//evento de clique, sendo possível instanciando a classe.
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                        Tarefa tarefaselecionada = listaTarefa.get(position);
                        tarefaDAO.deletar(tarefaselecionada);
                        carregarListaTarefas();
                        Toast.makeText(getApplicationContext(), "Tarefa Excluída", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                })
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarListaTarefas();
    }

}
