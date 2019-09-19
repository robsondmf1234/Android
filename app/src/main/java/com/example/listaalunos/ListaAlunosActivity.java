package com.example.listaalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaAlunosActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        //Lista de Alunos
        String[] alunos = {"Daniel", "Ronaldo", "Jeferson", "Felipe"};
        //Armazenando a referencia da instancia da lista de alunos
        ListView listaAlunos = (findViewById(R.id.lista_alunos));
        //ArrayAdpter , responsavel por converter as String da lista em Views,para ser armazenado no ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
        //Setando o arrayadpter na ListView
        listaAlunos.setAdapter(adapter);
    }
}
