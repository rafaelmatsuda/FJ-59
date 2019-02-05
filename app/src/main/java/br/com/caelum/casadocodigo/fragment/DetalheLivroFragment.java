package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Autor;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalheLivroFragment extends Fragment {

    @BindView(R.id.detalhes_livro_foto)
    ImageView foto;

    @BindView(R.id.detalhes_livro_nome)
    TextView nome;

    @BindView(R.id.detalhes_livro_autores)
    TextView autores;

    @BindView((R.id.detalhes_livro_comprar_fisico))
    Button btnComprarFisico;

    @BindView(R.id.detalhes_livro_comprar_ebook)
    Button btnComprarEbook;

    @BindView(R.id.detalhes_livro_comprar_ambos)
    Button btnComprarAmbos;

    @BindView(R.id.detalhes_livro_descricao)
    TextView descricao;

    @BindView(R.id.detalhes_livro_num_paginas)
    TextView numPaginas;

    @BindView(R.id.detalhes_livro_data_publicacao)
    TextView dataPublicacao;

    @BindView(R.id.detalhes_livro_isbn)
    TextView isbn;

    private Livro livro;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_livro, container, false);

        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();

        livro = (Livro) arguments.getSerializable("livro");
        populaCamposComLivro(livro);

        return  view;
    }

    private void populaCamposComLivro(Livro livro) {

        nome.setText(livro.getNome());

        String listaDeAutores = "";
        for (Autor autor : livro.getAutores()) {
            if (!listaDeAutores.isEmpty()) {
                listaDeAutores += ", ";
            }
            listaDeAutores += autor.getNome();
        }
        autores.setText(listaDeAutores);
        descricao.setText(livro.getDescricao());
        numPaginas.setText(String.valueOf(livro.getNumPaginas()));
        isbn.setText(livro.getISBN());
        dataPublicacao.setText(livro.getDataPublicacao());
        String textoComprarFisico = String.format("Comprar Livro Físico - R$ %.2f", livro.getValorFisico());
        btnComprarFisico.setText(textoComprarFisico);
        String textoComprarEbook = String.format("Comprar E-book - R$ %.2f", livro.getValorVirtual());
        btnComprarEbook.setText(textoComprarEbook);
        String textoComprarAmbos = String.format("Comprar Ambos - R$ %.2f", livro.getValorDoisJuntos());
        btnComprarAmbos.setText(textoComprarAmbos);


    }

}
