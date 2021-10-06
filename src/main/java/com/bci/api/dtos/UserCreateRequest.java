package com.bci.api.dtos;

import java.util.ArrayList;
import java.util.List;

public class UserCreateRequest {


    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;
    private List<String> errors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDto> phones) {
        this.phones = phones;
    }

    public boolean validate(){
        boolean valid = true;
        if(name == null){
            addToErrors("El nombre es requerido");
            valid = false;
        }
        if(email == null){
            addToErrors("El email es requerido");
            valid = false;
        }
        if(password == null){
            addToErrors("El password es requerido");
            valid = false;
        }
        if(!isPasswordValid(password)){
            addToErrors("El password es invalido, debe contener una mayuscula, letras minúsculas, y dos numeros");
            valid = false;
        }
        return valid;
    }
    private void addToErrors(String e){
        if(errors == null){
            errors = new ArrayList<>();
        }
        errors.add(e);
    }
    public List<String> getErrors(){
        return errors;
    }
    private boolean isPasswordValid(String password){
        String pattern = "^(?=.*[0-9]{2})(?=.*[a-z])(?=.*[A-Z]).{2,8}$";
        return password.matches(pattern);
    }

}
