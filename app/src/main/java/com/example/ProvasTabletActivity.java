package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.listaalunos.R;

//Tela utilizada para utilizar os fragments das telas(activity_provas,activity_detalhes_provas)
public class ProvasTabletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas_tablet);
    }
}
