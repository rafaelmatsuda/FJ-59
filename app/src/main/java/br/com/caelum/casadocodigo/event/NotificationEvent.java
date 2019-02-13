package br.com.caelum.casadocodigo.event;

import android.widget.Toast;

import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.Subscribe;

public class NotificationEvent {
    RemoteMessage remotemsg;

    NotificationEvent(RemoteMessage remoteMessage){
    remotemsg = remoteMessage;

}
    @Subscribe
    public void recebeNotificacao(RemoteMessage event){
       // Toast.makeText(context, "Recebeu uma notificação do servidor", Toast.LENGTH_SHORT).show();
    }
}
