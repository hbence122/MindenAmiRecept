package com.example.mindenamirecept;

import android.app.Application;

public class GlobalClass extends Application {

    private String ReceptKategoria;

    public String getReceptKategoria() {
        return ReceptKategoria;
    }

    public void setReceptKategoria(String receptKategoria) {
        ReceptKategoria = receptKategoria;
    }
}
