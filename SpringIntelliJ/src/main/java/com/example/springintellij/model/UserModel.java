package com.example.springintellij.model;

import javax.persistence.*;

@Entity
@Table(name = "user", catalog = "testdb")
public class UserModel implements java.io.Serializable {
    private int id;
    private String username;
    private String password;
    public UserModel(){

    }

    public UserModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "username", length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
