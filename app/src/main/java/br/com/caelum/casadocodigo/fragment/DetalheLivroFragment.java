package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.application.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo.modelo.Autor;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.modelo.Item;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.modelo.TipoDeCompra;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Inject
    Carrinho carrinho;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_livro, container, false);

        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();

        livro = (Livro) arguments.getSerializable("livro");
        populaCamposComLivro(livro);

        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getActivity().getApplication();
        app.getComponent().inject(this);


        return  view;
    }


    @OnClick(R.id.detalhes_livro_comprar_fisico)
    public void comprarFisico() {
        Toast.makeText(getActivity(), "Livro adicionado ao carrinho!", Toast.LENGTH_SHORT).show();

        carrinho.adiciona(new Item(livro, TipoDeCompra.FISICO));
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

        Picasso.with(getContext()).load(livro.getUrlFoto()).placeholder(R.drawable.livro).into(foto);


    }

}
