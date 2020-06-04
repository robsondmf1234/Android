package com.example;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listaalunos.R;
import com.example.modelo.Prova;

//29/05/2020
public class DetalhesProvaFragment extends Fragment {

    private TextView campoMateria;
    private TextView campoData;
    private ListView listaTopicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);

        //Recuperando as referencias dos textview e ListView
        campoMateria = view.findViewById(R.id.detalhes_prova_materia);
        campoData = view.findViewById(R.id.detalhes_prova_data);
        listaTopicos = view.findViewById(R.id.detalhes_prova_topicos);

        //Classe utilizada para recupear os argumentos passados
        Bundle parametros = getArguments();
        if (parametros != null) {
            //Recuperando a prova que foi passada como parametro no putSerializable
            Prova prova = (Prova) parametros.getSerializable("prova");
            populaCampos(prova);
        }
        return view;
    }

    public void populaCampos(Prova prova) {
        //Preenchendo os valores dos campos
        campoMateria.setText(prova.getMateria());
        campoData.setText(prova.getData());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, prova.getTopicos());
        listaTopicos.setAdapter(adapter);
    }
}
