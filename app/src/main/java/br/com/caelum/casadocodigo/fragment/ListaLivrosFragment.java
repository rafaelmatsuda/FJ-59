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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.listener.EndlessListListener;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.server.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {

    private List<Livro> livros = new ArrayList<>();
    private final int qtd = 10;

    @BindView(R.id.lista_livros)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        ButterKnife.bind(this,view);



        recyclerView.setAdapter(new LivroAdapter(livros));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }



    public void populaListaCom(final List<Livro> listalivros) {
        //        this.livros.clear();

        this.livros.addAll(listalivros);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.addOnScrollListener(new EndlessListListener(){

            @Override
            protected void carregaMaisItens() {
                new WebClient().getLivros(livros.size(), qtd);
                Toast.makeText(getContext(), "Carregando Livros...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
