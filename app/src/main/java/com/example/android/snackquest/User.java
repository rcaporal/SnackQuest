package com.example.android.snackquest;

/**
 * Created by caporal on 25/10/17.
 */

public class User {

    private String id;
    private String name;
    private String email;

    public  User(){};

    public User(String name, String email){
        setName(name);
        setEmail(email);
    }

    public User(String email){
        setEmail(email);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
