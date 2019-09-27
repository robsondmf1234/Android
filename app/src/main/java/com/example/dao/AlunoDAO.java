package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.modelo.Aluno;

/**
 * Created by Robson on 22/09/2019
 */
public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(@Nullable Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY,nome TEXT NOT NULL, endereco TEXT, telefone TEXT,site TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Aluno aluno) {
        //Referencia ao banco de dados
        SQLiteDatabase db = getWritableDatabase();

        //Guarda uma instancia da classe,para armazenar os valores
        ContentValues dados = new ContentValues();
        //Método put Insere valores no objeto dados, ex: dados.put( chave , valor)
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereço());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());

        //metodo que sera responsavel pelo insert into no banco, proprio método vai criar o comando sql
        db.insert("Alunos", null, dados);
    }
}
