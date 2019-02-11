package br.com.caelum.casadocodigo.interfaces;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.activity.CarrinhoActivity;
import br.com.caelum.casadocodigo.fragment.DetalheLivroFragment;
import br.com.caelum.casadocodigo.modules.CasaDoCodigoModule;
import dagger.Component;

@Singleton
@Component(modules = CasaDoCodigoModule.class)
public interface CasaDoCodigoComponent {
    void inject(DetalheLivroFragment fragment);

    void inject(CarrinhoActivity activity);
}
