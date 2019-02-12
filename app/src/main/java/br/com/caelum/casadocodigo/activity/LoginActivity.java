package br.com.caelum.casadocodigo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.caelum.casadocodigo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email)
    EditText campoEmail;

    @BindView(R.id.login_senha)
    EditText campoSenha;

    private FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener listener;

    private boolean usuarioLogado = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        firebaseAuth = FirebaseAuth.getInstance();

        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null && !usuarioLogado){
                    usuarioLogado = true;
                    goToMain();
                }
            }
        };

        firebaseAuth.addAuthStateListener(listener);

    }

    @OnClick(R.id.login_logar)
    void login(){
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if (!email.isEmpty() && !senha.isEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Snackbar.make(campoEmail, "Acesso não autorizado!",
                                        Snackbar.LENGTH_INDEFINITE).show();
                            }
                        }
                    });
        }

        else{
            Snackbar.make(campoEmail, "Preencha todos os campos !",
                    Snackbar.LENGTH_INDEFINITE).show();
        }
    }


    @OnClick(R.id.login_novo)
    public void novoUsuario() {
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();
        if (!email.isEmpty() || !senha.isEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, new
                    OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Snackbar.make(campoEmail, "Acesso não autorizado, verifique suas informações"
                                        ,
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Snackbar.make(campoSenha, "Por favor complete todos os campos", Snackbar.LENGTH_SHORT)
                    .show();
        }
    }



    private void goToMain(){
        Intent vaiParaMain = new Intent(this, MainActivity.class);
        startActivity(vaiParaMain);
        finish();
    }
}
