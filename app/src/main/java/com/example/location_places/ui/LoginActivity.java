package com.example.location_places.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.location_places.R;
import com.example.location_places.model.Usuario;
import com.example.location_places.model.UsuarioViewModel;
import com.orhanobut.hawk.Hawk;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView textViewNovoCadastro;
    private UsuarioViewModel usuarioViewModel;
    private Usuario usuarioCorrente;
    private EditText editTextEmail;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Hawk.init(this).build();

        editTextEmail = findViewById(R.id.editTextUsuario);
        editTextSenha = findViewById(R.id.editTextSenha);

        btnLogin = findViewById(R.id.btnLogin);
        textViewNovoCadastro = findViewById(R.id.textViewNovoCadastro);

        usuarioCorrente = new Usuario();

        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                updateUsuario(usuario);
            }
        });



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


    private void updateUsuario(Usuario usuario){
        this.usuarioCorrente = usuario;
    }

    @Override
    public void onResume(){
        super.onResume();
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
    btnLogin.setBackgroundColor(getColor(R.color.cinzaLetra));
    btnLogin.setTextColor(getColor(R.color.branco));
    textViewNovoCadastro.setVisibility(View.VISIBLE);

    }

    private void desbloquear(){
        btnLogin.setEnabled(true);
        btnLogin.setBackgroundColor(getColor(R.color.fundoFaixa));
        textViewNovoCadastro.setVisibility(View.GONE);
    }

    public void novoUsuario(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void login(View view) {

        if(usuarioCorrente != null){
            String usuario = editTextEmail.getText().toString();
            String senha = editTextSenha.getText().toString();
                    if(usuario.equalsIgnoreCase(usuarioCorrente.getEmail())
                    && senha.equals(usuarioCorrente.getSenha())){
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        editTextSenha.setText("");
                    } else{
                        Toast.makeText(this, "Ops...Usúario ou Senha Inválidos!",
                                Toast.LENGTH_SHORT).show();
                    }



        }


    }
}