package com.example.myapplication;

import androidx.annotation.NonNull;

public class Category {
    public static final int KOLORY = 1;
    public static final int ZWIERZETA = 2;
    public static final int JEDZENIE = 3;
    public static final int TRANSPORT = 4;
    public static final int WARZYWA = 5;
    public static final int CZESCI_CIALA = 6;
    public static final int SPORT = 7;
    public static final int ZAWODY = 8;
    public static final int MEBLE = 9;
    public static final int UBRANIA = 10;


    private int id;
    private String name;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return getName();
    }
}