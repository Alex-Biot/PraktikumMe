package com.telran.scheduler.model;

import java.util.Objects;

public class User {
    private  String email;
    private  String password;

    public User withEmail(String email) {
        this.email = email;
        return  this;
    }

    public User withPassword(String password) {
        this.password = password;
        return  this;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
