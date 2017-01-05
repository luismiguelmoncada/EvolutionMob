package com.promedan.evolutionmob.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.promedan.evolutionmob.ApiRest.ApiClient;
import com.promedan.evolutionmob.ApiRest.ServerResponse;
import com.promedan.evolutionmob.Model.Usuario;
import com.promedan.evolutionmob.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    String nombre,nombreusuario,email,contraseña,message,result;

    @BindView(R.id.editTextName)
    EditText Nombre;

    @BindView(R.id.editTextUsername)
    EditText NombreUsuario;

    @BindView(R.id.editTextPassword)
    EditText Contraseña;

    @BindView(R.id.editTextEmail)
    EditText Email;

    @BindView(R.id.buttonRegister)
    Button BotonRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        verToolbar(getResources().getString(R.string.toolbar_titulo),true);
        ButterKnife.bind(this);

    }

    public  void verToolbar(String titulo,Boolean UpButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(UpButton);
    }

    @OnClick(R.id.buttonRegister)
    public void Registro(){
        RegistrarUsuario();
    }

    private void RegistrarUsuario(){

        nombre = Nombre.getText().toString();
        nombreusuario = NombreUsuario.getText().toString();
        contraseña = Contraseña.getText().toString();
        email = Email.getText().toString();

        Usuario usuario = new Usuario(nombre,nombreusuario,contraseña,email);
        Call<ServerResponse> call = ApiClient.get().createUser(usuario);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                message = response.body().getMessage();
                result = response.body().getResult();
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Server Error : "+ t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
