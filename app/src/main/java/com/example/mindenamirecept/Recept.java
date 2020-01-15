package com.example.mindenamirecept;

public class Recept {

    String id;
    String receptNev;
    String receptKat;
    String receptHozz1;
    String receptHozz2;
    String receptHozz3;
    String receptHozz4;
    String receptHozz5;
    String receptKeszites;

    public Recept() {
    }

    public Recept(String id, String receptNev, String receptKat, String receptHozz1, String receptHozz2, String receptHozz3, String receptHozz4, String receptHozz5, String receptKeszites) {
        this.id = id;
        this.receptNev = receptNev;
        this.receptKat = receptKat;
        this.receptHozz1 = receptHozz1;
        this.receptHozz2 = receptHozz2;
        this.receptHozz3 = receptHozz3;
        this.receptHozz4 = receptHozz4;
        this.receptHozz5 = receptHozz5;
        this.receptKeszites = receptKeszites;
    }

    public String getId() {
        return id;
    }

    public String getReceptNev() {
        return receptNev;
    }

    public String getReceptKat() {
        return receptKat;
    }

    public String getReceptHozz1() {
        return receptHozz1;
    }

    public String getReceptHozz2() {
        return receptHozz2;
    }

    public String getReceptHozz3() {
        return receptHozz3;
    }

    public String getReceptHozz4() {
        return receptHozz4;
    }

    public String getReceptHozz5() {
        return receptHozz5;
    }

    public String getReceptKeszites() {
        return receptKeszites;
    }
}
