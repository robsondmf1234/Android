package com.example.listaalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dao.AlunoDAO;
import com.example.modelo.Aluno;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);
                Intent vaiProFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                vaiProFormulario.putExtra("aluno", aluno);
                startActivity(vaiProFormulario);
            }
        });

        Button novoAluno = (Button) findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        //informando que listaAlunos terá um menu de contexto
        registerForContextMenu(listaAlunos);
    }

    private void carregaLista() {

        AlunoDAO dao = new AlunoDAO(this);
        //Busca alunos do banco de dados.
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        //Armazenando a referencia da instancia da lista de alunos
        listaAlunos = (findViewById(R.id.lista_alunos));
        //ArrayAdpter , responsavel por converter as String da lista em Views,para ser armazenado no ListView
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        //Setando o arrayadpter na ListView
        listaAlunos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    //Criando o menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        //Recuperando a posição do tem seleciona no contextMenuInfo
        final Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

        MenuItem itemSMS = menu.add("Enviar SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:" + aluno.getTelefone()));
        itemSMS.setIntent(intentSMS);
        
        MenuItem itemMapa = menu.add("Visualizar no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?q=" + aluno.getEndereço()));
        itemMapa.setIntent(intentMapa);

        MenuItem itemSite = menu.add("Visitar Site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);

        String site = aluno.getSite();

        if (!site.startsWith("http://")) {
            site = "http://" + site;
        }

        intentSite.setData(Uri.parse(site));
        itemSite.setIntent(intentSite);


        //Adicionando um item ao menu de contexto e pegando a referencia a esse menu
        MenuItem deletar = menu.add("Deletar");
        //Método semelhante ao setOnclickListener
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deleta(aluno);
                dao.close();

                carregaLista();

                //Mostrando o nome do aluno selecionado
                //Toast.makeText(ListaAlunosActivity.this, "Deletar o aluno " + aluno.getNome(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
