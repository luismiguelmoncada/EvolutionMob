package com.promedan.evolutionmob.ApiRest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LUISM on 30/12/2016.
 */

public class ApiClient {
    private static RestApi REST_CLIENT;
    //http://192.168.0.109:64432//api/retrofit_users
    private static final String API_URL = "http://webserverandroid.site88.net/";

    static {
        setupRestClient();
    }

    private ApiClient() {}

    private static void setupRestClient() {

        OkHttpClient okClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                //.setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okClient)
                .build();

        REST_CLIENT = retrofit.create(RestApi.class);
    }

    public static RestApi get() {
        return REST_CLIENT;
    }
}
