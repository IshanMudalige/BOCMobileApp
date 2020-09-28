package com.aim.bocmobileapp.model;

public class Payee {
    private String name;
    private String nick;
    private String acc;
    private String email;

    public Payee(String name, String nick, String acc, String email) {
        this.name = name;
        this.nick = nick;
        this.acc = acc;
        this.email = email;
    }

    public Payee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
