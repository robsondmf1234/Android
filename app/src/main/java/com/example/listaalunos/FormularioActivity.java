package com.example.listaalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dao.AlunoDAO;
import com.example.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        //Recuperando os valores que estão na Intent
        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if (aluno != null) {
            helper.preencheFormulario(aluno);
        }

    }

    @Override
    //Metodo utilizado para definir os itens que sera mostrado na barra de menu da Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater utilizado para pegar o xml e inserir no menu.
        MenuInflater inflater = getMenuInflater();
        //inflate utilizado para transformar o xml em view
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    //Método utilizado para tratar de cliques em icones do menu
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.pegaAluno();

                AlunoDAO dao = new AlunoDAO(this);
                if (aluno.getId() != null){
                    dao.altera(aluno);
                }else{
                    dao.insere(aluno);
                }

                dao.close();

                //Toast irá mostrar a mensagem "Botao Clicado ao usuario"
                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " Salvo.", Toast.LENGTH_SHORT).show();


                //Finaliza a Activity
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
