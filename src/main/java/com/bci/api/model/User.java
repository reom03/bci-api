package com.bci.api.model;

import com.bci.api.dtos.PhoneDto;
import com.bci.api.dtos.UserCreateRequest;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Phone> phones;

    private Date created = new Date();
    private Date modified;
    @Column(name = "last_login")
    private Date lastLogin;
    private String token;

    public static User create(UserCreateRequest vo){
        User instance = new User();
        instance.name = vo.getName();
        instance.email = vo.getEmail();
        instance.password = vo.getPassword();

        if(vo.getPhones() != null){
            for( PhoneDto phoneVO: vo.getPhones()){
                instance.addToPhones(Phone.create(phoneVO));
            }
        }

        return instance;
    }

    public void addToPhones(Phone phone){
        if (phones == null){
            phones = new ArrayList<>();
        }
        phones.add(phone);
    }
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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
