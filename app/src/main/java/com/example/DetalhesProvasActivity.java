package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listaalunos.R;
import com.example.modelo.Prova;

public class DetalhesProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_provas);

        Intent intent = getIntent();
        Prova prova = (Prova) intent.getSerializableExtra("prova");

        TextView materia = findViewById(R.id.detalhes_prova_materia);
        TextView data = findViewById(R.id.detalhes_prova_data);
        ListView lista = findViewById(R.id.detalhes_prova_topicos);

        materia.setText(prova.getMateria());
        data.setText(prova.getData());

        //Criando um adapter para popular o ListView dos tópicos
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, prova.getTopicos());
        lista.setAdapter(adapter);
    }
}
