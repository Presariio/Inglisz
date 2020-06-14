package com.example.myapplication;

import androidx.annotation.NonNull;
//PRZYPISANIE NUMERU ID DO KAŻDEJ KATEGORII
public class Category {
    public static final int KOLORY = 1;
    public static final int ZWIERZETA = 2;
    public static final int OWOCE = 3;
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
    //KONSTRUKTOR KATEGORII
    public Category(String name) {
        this.name = name;
    }
    //GETTERY I SETTERY
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