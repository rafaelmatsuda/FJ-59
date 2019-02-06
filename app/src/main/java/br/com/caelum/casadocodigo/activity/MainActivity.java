package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.delegate.LivrosDelegate;
import br.com.caelum.casadocodigo.fragment.DetalheLivroFragment;
import br.com.caelum.casadocodigo.fragment.ListaLivrosFragment;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.server.WebClient;

public class MainActivity extends AppCompatActivity implements LivrosDelegate {

    private ListaLivrosFragment listaLivrosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        listaLivrosFragment = new ListaLivrosFragment();
        transaction.replace(R.id.frame_principal, listaLivrosFragment);
        transaction.commit();

        new WebClient(this).getLivros();
        }

    @Override
    public void lidaComLivroSelecionado(Livro livro) {
        //Toast.makeText(this, "Livro selecionado: " + livro.getNome(), Toast.LENGTH_LONG).show();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetalheLivroFragment detalhesLivroFragment = criaDetalhesCom(livro);
        transaction.replace(R.id.frame_principal, detalhesLivroFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void lidaComSucesso(List<Livro> livros) {
        listaLivrosFragment.populaListaCom(livros);
    }

    @Override
    public void lidaComErro(Throwable erro) {
        Toast.makeText(this, "Não foi possível carregar os livros...", Toast.LENGTH_SHORT).show();
    }

    private DetalheLivroFragment criaDetalhesCom(Livro livro) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("livro", livro);
        DetalheLivroFragment detalhesLivroFragment = new DetalheLivroFragment();
        detalhesLivroFragment.setArguments(bundle);
        return detalhesLivroFragment;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
