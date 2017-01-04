package com.promedan.evolutionmob.ApiRest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LUISM on 27/12/2016.
 */

public class ServerResponse {
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("vlink")
    @Expose
    private Boolean vlink;

    /**
     *
     * @return
     * The error
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     *
     * @return
     * The vlink
     */
    public Boolean getVlink() {
        return vlink;
    }

    /**
     *
     * @param vlink
     * The vlink
     */
    public void setVlink(Boolean vlink) {
        this.vlink = vlink;
    }
    /*
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
    }*/
}
