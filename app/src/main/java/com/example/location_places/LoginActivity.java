package com.example.location_places;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView textViewNovoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Hawk.init(this).build();

        btnLogin = findViewById(R.id.btnLogin);
        textViewNovoCadastro = findViewById(R.id.textViewNovoCadastro);

        if (Hawk.contains("temCadastro")){
            if (Hawk.get("temCadastro")){
                desbloquear();
            }else{
                bloquear();
            }
        }else{
            bloquear();
        }
    }


    private void bloquear(){
    btnLogin.setEnabled(false);
    btnLogin.setBackgroundColor(getResources().getColor(R.color.cinzaLetra));
    btnLogin.setTextColor(getResources().getColor(R.color.branco));
    textViewNovoCadastro.setVisibility(View.VISIBLE);

    }

    private void desbloquear(){
        btnLogin.setEnabled(true);
        btnLogin.setBackgroundColor(getResources().getColor(R.color.fundoFaixa));
        textViewNovoCadastro.setVisibility(View.GONE);
    }

    public void novoUsuario(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}