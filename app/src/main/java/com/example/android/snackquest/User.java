package com.example.android.snackquest;

/**
 * Created by caporal on 25/10/17.
 */

public class User {

    private String id;
    private String name;
    private String password;
    private String email;

    public User(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public User(String email, String password){
        setEmail(email);
        setPassword(password);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
