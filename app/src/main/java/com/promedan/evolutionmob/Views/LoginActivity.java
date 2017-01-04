package com.promedan.evolutionmob.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.promedan.evolutionmob.ApiRest.ApiClient;
import com.promedan.evolutionmob.Model.Usuario;
import com.promedan.evolutionmob.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    //libreria para evitar usar tanto codigo especialmente con los onclic, puedo hacer injeccion de codigo
    @BindView(R.id.email) EditText Email;
    @BindView(R.id.password) EditText Password;

    @BindView(R.id.CreateAccount)
    TextView create_account;

    @BindView(R.id.login)
    Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.CreateAccount)
    public void NuevaCuenta(){
        //Toast.makeText(LoginActivity.this, "Prueba libreria JakeWharton", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this, RegistreActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.login)
    public void Ingresar(){
        Login();
    }

    private void Login() {

        LoginServer();

        Email.setError(null);
        Password.setError(null);

        // Store values at the time of the login attempt.
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            Password.setError(getString(R.string.error_invalid_password));
            focusView = Password;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            Password.setError(getString(R.string.error_field_required));
            focusView = Password;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            Email.setError(getString(R.string.error_field_required));
            focusView = Email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            Email.setError(getString(R.string.error_invalid_email));
            focusView = Email;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {



        }
    }

    private void LoginServer(){
        Call<List<Usuario>> call = ApiClient.get().getUsers();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                List<Usuario> users = response.body();
                // TextView name = (TextView)findViewById(R.id.textView4);
                //TextView email = (TextView)findViewById(R.id.textView5);
                for (Usuario user : users) {
                    Toast.makeText(LoginActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    //name.setText(user.getName());
                    //email.setText(user.getEmail());
                    Toast.makeText(LoginActivity.this, user.getName()+" , "+user.getEmail(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.d("my_tag", "ERROR: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
