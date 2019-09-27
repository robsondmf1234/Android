package com.example.listaalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.dao.AlunoDAO;
import com.example.modelo.Aluno;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);


       // AlunoDAO dao = new AlunoDAO(this);
        //List<Aluno> alunos = dao.buscaAlunos();

        //Lista de Alunos
        String[] alunos = {"Daniel", "Ronaldo", "Jeferson", "Felipe"};
        //Armazenando a referencia da instancia da lista de alunos
        ListView listaAlunos = (findViewById(R.id.lista_alunos));
        //ArrayAdpter , responsavel por converter as String da lista em Views,para ser armazenado no ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
        //Setando o arrayadpter na ListView
        listaAlunos.setAdapter(adapter);

        Button novoAluno = (Button) findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });
    }
}
