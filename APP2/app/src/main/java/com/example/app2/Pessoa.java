package com.example.app2;

import java.util.Date;

public class Pessoa {
    private String Nome;
    private String User;
    private int senha;
    private Date Nasc;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public Date getNasc() {
        return Nasc;
    }

    public void setNasc(Date nasc) {
        Nasc = nasc;
    }
}
