package com.example.zoomir;

public class getsettovars {
    int id,количество;
    String название;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getНазвание() {
        return название;
    }

    public void setНазвание(String название) {
        this.название = название;
    }

    public int getКоличество() {
        return количество;
    }

    public void setКоличество(String количество) {
        this.количество = Integer.parseInt(количество);
    }

    public getsettovars(int id, String название, int количество) {
        this.id = id;
        this.название = название;
        this.количество = Integer.parseInt(String.valueOf(количество));
    }
}
