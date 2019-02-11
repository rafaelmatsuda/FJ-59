package br.com.caelum.casadocodigo.application;

import android.app.Application;

import br.com.caelum.casadocodigo.interfaces.CasaDoCodigoComponent;
import br.com.caelum.casadocodigo.interfaces.DaggerCasaDoCodigoComponent;

public class CasaDoCodigoApplication extends Application {

    private CasaDoCodigoComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerCasaDoCodigoComponent.builder().build();
    }

    
    public CasaDoCodigoComponent getComponent() {

        return component;
    }
}
