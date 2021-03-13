package io.github.akkhadka.webstore.controller.viewmodels;

import io.github.akkhadka.webstore.controller.BusinessRule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserViewModel extends ViewModelBase {

    public String getUsername() {
        return username;
    }


    private String firstName;
    private String username;
    private String password;
    private  String email;

    private String lastName;
    private String confirmPassword;

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


//
//    public UserViewModel(String username, String password,String confirmPassword,String firstName,String lastName,String email){
//        this.password=password;
//        this.username = username;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.confirmPassword = confirmPassword;
//        this.email =email;
//    }


    @Override
    protected void Validate() {
        if(username!=null ){
            username = username.trim();
            if(username=="") addBrokenRule("username","Username cannot be empty.");
            else if(username.length() < 5) addBrokenRule("username","Username must be at least 5 characters.");

        }
        if( password!=null ) {
            password = password.trim();
            Pattern pattern = Pattern.compile("(?=.*\\d)(?=.*[a-zA-Z]).{6,}");
            Matcher matcher = pattern.matcher(password);
            if(password.equals("")) addBrokenRule("password","password cannot be empty.");
            else if(!matcher.matches()) {
                addBrokenRule("password","Pass must be at least 6 characters with at least one numeric and a letter.");
            }

            confirmPassword = confirmPassword.trim();
            if(confirmPassword!=null && !confirmPassword.equals(password)){
                addBrokenRule("confirmPassword","Confirm password do not match with password.");
            }
        }

        if(firstName.trim().equals("")) {
            addBrokenRule("firstName","firstName cannot be empty.");
        }
        if(lastName.trim().equals("")) {
            addBrokenRule("lastName","lastName cannot be empty.");
        }
        if(email.trim().equals("")){
            addBrokenRule("email","email cannot be empty.");
        }
    }
}
