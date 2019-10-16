package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

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

        ContentValues dados = pegaDadosAluno(aluno);

        //metodo que sera responsavel pelo insert into no banco, proprio método vai criar o comando sql
        db.insert("Alunos", null, dados);
    }

    private ContentValues pegaDadosAluno(Aluno aluno) {
        //Guarda uma instancia da classe,para armazenar os valores
        ContentValues dados = new ContentValues();
        //Método put Insere valores no objeto dados, ex: dados.put( chave , valor)
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereço());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());
        return dados;
    }

    //Método para buscar alunos
    public List<Aluno> buscaAlunos() {
        String sql = "SELECT * FROM Alunos";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();

        while (c.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereço(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno);
        }
        c.close();

        return alunos;
    }

    //Método para deletar aluno
    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {aluno.getId().toString()};
        db.delete("Alunos", "id=?", params);
    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosAluno(aluno);

        String[] params = {aluno.getId().toString()};
        db.update("Alunos", dados, "id=?", params);
    }
}
