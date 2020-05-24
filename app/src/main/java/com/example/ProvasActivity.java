package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listaalunos.R;
import com.example.modelo.Prova;

import java.util.Arrays;
import java.util.List;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        List<String> topicosPort = Arrays.asList("Objeto Indireto", "Sujeito", "Obejto Direto");
        Prova provaPortugues = new Prova("Portugues", "25/02/2020", topicosPort);

        List<String> topicosMat = Arrays.asList("Trigonometria", "Raiz Quadrada", "Equacao 2 grau");
        Prova provaMatematica = new Prova("Matematica", "26/05/2020", topicosMat);

        List<Prova> provas = Arrays.asList(provaPortugues, provaMatematica);
        ArrayAdapter<Prova> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, provas);
        ListView lista = findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Prova prova = (Prova) adapterView.getItemAtPosition(position);
                Toast.makeText(ProvasActivity.this, "Clicou na prova de " + prova, Toast.LENGTH_LONG).show();
                Intent vaiParaDetalhes = new Intent(ProvasActivity.this, DetalhesProvasActivity.class);
                vaiParaDetalhes.putExtra("prova",prova);
                startActivity(vaiParaDetalhes);
            }
        });
    }
}
