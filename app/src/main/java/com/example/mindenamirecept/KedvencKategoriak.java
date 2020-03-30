package com.example.mindenamirecept;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;

public class KedvencKategoriak extends AppCompatActivity {

    DatabaseReference KatReference;
    RecyclerView recyclerView;

    public final String SHARED_PREFS = "sharedPrefs";
    public final String TEXT = "text";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kedvenc_kategoriak);


    }

    public void saveKatEloetel(){
        sharedPreferences = getSharedPreferences("SaveKat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Kategoria", "Előételek");
        editor.apply();
    }

    public void saveKatLeves(){
        sharedPreferences = getSharedPreferences("SaveKat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Kategoria", "Levesek");
        editor.apply();
    }
    public void saveKatSalata(){
        sharedPreferences = getSharedPreferences("SaveKat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Kategoria", "Saláták");
        editor.apply();
    }
    public void saveKatDesszert(){
        sharedPreferences = getSharedPreferences("SaveKat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Kategoria", "Desszertek");
        editor.apply();
    }
    public void saveKatEgyeb(){
        sharedPreferences = getSharedPreferences("SaveKat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Kategoria", "Egyéb");
        editor.apply();
    }
    public void saveKatFoetel(){
        sharedPreferences = getSharedPreferences("SaveKat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Kategoria", "Főételek");
        editor.apply();
    }


    public void onClickEloetel(View view) {

        //GlobalClass globalClass = (GlobalClass) getApplicationContext();
        //globalClass.setReceptKategoria("Előételek");
        //((GlobalClass) this.getApplication()).setReceptKategoria("Előételek");
        saveKatEloetel();
        Intent intent = new Intent(KedvencKategoriak.this, Kedvencek.class);
        startActivity(intent);

    }

    public void onClickFoetelek(View view) {
        saveKatFoetel();
        Intent intent = new Intent(KedvencKategoriak.this, Kedvencek.class);
        startActivity(intent);
    }

    public void onClickLevesek(View view) {
        saveKatLeves();
        Intent intent = new Intent(KedvencKategoriak.this, Kedvencek.class);
        startActivity(intent);
    }

    public void onClickSalatak(View view) {
        saveKatSalata();
        Intent intent = new Intent(KedvencKategoriak.this, Kedvencek.class);
        startActivity(intent);
    }

    public void onClickDesszertek(View view) {
        saveKatDesszert();
        Intent intent = new Intent(KedvencKategoriak.this, Kedvencek.class);
        startActivity(intent);
    }

    public void onClickEgyeb(View view) {
        saveKatEgyeb();
        Intent intent = new Intent(KedvencKategoriak.this, Kedvencek.class);
        startActivity(intent);
    }
}
