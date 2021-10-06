package com.bci.api.dtos;

import com.bci.api.model.User;

import java.util.List;

public class UserCreateResponse {

    private String mensaje;
    private User user;

    public String getMensaje() {
        return mensaje;
    }
    public UserCreateResponse(List<String> errors) {
        this.mensaje = errors.toString();
    }
    public UserCreateResponse(String error) {
        this.mensaje = error;
    }
    public UserCreateResponse(User user) {
        this.user = user;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
