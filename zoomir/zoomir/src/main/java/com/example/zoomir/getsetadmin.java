package com.example.zoomir;

public class getsetadmin {

    int id;
    String login,parol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getParol() {
        return parol;
    }

    public void setParol(String parol) {
        this.parol = parol;
    }

    public getsetadmin(int id, String login, String parol) {
        this.id = id;
        this.login = login;
        this.parol = parol;
    }
}
