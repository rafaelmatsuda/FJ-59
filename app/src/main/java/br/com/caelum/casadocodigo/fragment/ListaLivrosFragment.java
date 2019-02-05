package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.modelo.Autor;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {

    @BindView(R.id.lista_livros)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        ButterKnife.bind(this,view);

        List<Livro> livros = new ArrayList<>();
        for(int i = 0; i < 6 ; i++ ){
            Autor autor = new Autor();
            autor.setNome("Autor "+i);
            Livro livro = new Livro("Livro "+i, "Descricao "+i, Arrays.asList(autor));
            livros.add(livro);
        }

        //RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.lista_livros);
        recyclerView.setAdapter(new LivroAdapter(livros));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
