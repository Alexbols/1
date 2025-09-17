package com.example.zoomir;

public class getsetpolz {

    int id;
    String имя,пароль,email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getИмя() {
        return имя;
    }

    public void setИмя(String имя) {
        this.имя = имя;
    }

    public String getПароль() {
        return пароль;
    }

    public void setПароль(String пароль) {
        this.пароль = пароль;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public getsetpolz(int id, String имя, String пароль, String email) {
        this.id = id;
        this.имя = имя;
        this.пароль = пароль;
        this.email = email;
    }
}

