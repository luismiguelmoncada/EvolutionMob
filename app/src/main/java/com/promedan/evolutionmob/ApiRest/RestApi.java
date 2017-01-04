package com.promedan.evolutionmob.ApiRest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by LUISM on 30/12/2016.
 */

public interface RestApi {

    @GET("/Retrofit-Prueba/getData.php")
    Call<List<Usuario>> getUsers();

    @POST("/Retrofit-Prueba/inserttest.php")
    Call<Usuario> createUser(@Body Usuario user);

    @POST("learn2crack-login-register/")
    Call<ServerResponse> operation(@Body Usuario user);
}
