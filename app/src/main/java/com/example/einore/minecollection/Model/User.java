package com.example.einore.minecollection.Model;

public class User {

    private int user_id;
    private String user_username;
    private String user_pinCode;
    private String user_name;
    private String user_surname;
    private String user_email;
    private String user_phone;

    public User(){}

    public User(int user_id, String user_username, String user_pinCode,
                String user_name, String user_surname, String user_email, String user_phone) {
        this.user_id = user_id;
        this.user_username = user_username;
        this.user_pinCode = user_pinCode;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_phone = user_phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_username() { return user_username; }

    public void setUser_username(String user_username) { this.user_username = user_username; }

    public String getUser_pinCode() { return user_pinCode; }

    public void setUser_pinCode(String user_pinCode) { this.user_pinCode = user_pinCode; }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }
}
