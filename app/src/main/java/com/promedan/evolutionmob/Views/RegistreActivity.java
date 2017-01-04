package com.promedan.evolutionmob.Views;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.promedan.evolutionmob.ApiRest.ApiClient;
import com.promedan.evolutionmob.ApiRest.ServerResponse;
import com.promedan.evolutionmob.ApiRest.Usuario;
import com.promedan.evolutionmob.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistreActivity extends AppCompatActivity {

    String nombre,nombreusuario,email,contraseña;

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
        setContentView(R.layout.activity_registre);
        verToolbar(getResources().getString(R.string.toolbar_titulo),true);
        ButterKnife.bind(this);
    }

    public  void verToolbar(String titulo,Boolean UpButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar6);
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

        Usuario user = new Usuario(nombre,nombreusuario,contraseña,email);
        Call<Usuario> call = ApiClient.get().createUser(user);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario resp = response.body();
                int statusCode = response.code();
                Toast.makeText(RegistreActivity.this, "Usuario Ingresado con Exito "+ resp, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(RegistreActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


/*
        Usuario user = new Usuario(nombre,nombreusuario,contraseña,email);

         Call<ServerResponse> response = ApiClient.get().operation(user);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                //Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                Toast.makeText(RegistreActivity.this, "Error " + resp.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {


                Toast.makeText(RegistreActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                //Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
*/
    }
}
