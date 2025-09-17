package com.example.zoomir;

import java.sql.Date;

public class getsetzakaz {

    int id, количество;
    Date дата;
    String название;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getКоличество() {
        return количество;
    }

    public void setКоличество(int количество) {
        this.количество = количество;
    }

    public Date getДата() {
        return дата;
    }

    public void setДата(Date дата) {
        this.дата = дата;
    }

    public String getНазвание() {
        return название;
    }

    public void setНазвание(String название) {
        this.название = название;
    }

    public getsetzakaz(int id, Date дата, String   название, int количество) {
        this.id = id;
        this.количество = Integer.parseInt(String.valueOf(this.количество));
        this.дата = Date.valueOf(дата.toLocalDate());
        this.название = String.valueOf(название);
    }
}
