package com.promedan.evolutionmob.ApiRest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LUISM on 27/12/2016.
 */

public class ServerResponse {

    private String result;
    private String message;
    private Usuario user;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public Usuario getUser() {
        return user;
    }
}
