package io.github.akkhadka.webstore.model;

import io.github.akkhadka.webstore.controller.BusinessRule;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User  {

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    private String username;
    private String password;
    private  String email;
    private String firstName;
    private String lastName;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private Role role;
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public User(String username, String password,String firstName,String lastName,String email){
        this.password=password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role= Role.CUSTOMER;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o.getClass() != this.getClass())
            return false;
        User user = (User)o;
        boolean isEqual = this.username.equals(user.username) && this.email.equals(user.email);
        return isEqual;
    }


    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }


}
