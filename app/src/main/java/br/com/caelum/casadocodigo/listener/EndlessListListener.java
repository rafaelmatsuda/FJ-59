package br.com.caelum.casadocodigo.listener;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public abstract class EndlessListListener extends RecyclerView.OnScrollListener {
    int quantidadeTotalItens;
    int primeiroItemVisivel;
    int quantidadeItensVisiveis;
    boolean carregando = true;
    int totalAnterior = 0;
    int limiteParaCarregar =0;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int qtdScrollHorizontal, int qtdScrollVertical) {

        super.onScrolled(recyclerView, qtdScrollHorizontal, qtdScrollVertical);

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        quantidadeTotalItens = layoutManager.getItemCount();
        primeiroItemVisivel = layoutManager.findFirstVisibleItemPosition();


        quantidadeItensVisiveis = recyclerView.getChildCount();

        limiteParaCarregar = quantidadeTotalItens - quantidadeItensVisiveis -5;

        if (carregando) {
            if (quantidadeTotalItens > totalAnterior) {
                Log.d("Carga Antes:","Total Anterior:" + totalAnterior +". Total agora: "+quantidadeTotalItens);
                totalAnterior = quantidadeTotalItens;
                carregando = false;
            }
        }

        if (!carregando && primeiroItemVisivel >= limiteParaCarregar) {
            carregaMaisItens();
            Log.d("Carga ELSE:","Total Anterior:" + totalAnterior +". Total agora: "+quantidadeTotalItens);
            carregando = true;
        }

    }

    protected abstract void carregaMaisItens();
}
