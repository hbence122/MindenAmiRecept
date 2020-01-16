package com.example.mindenamirecept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Kategoriak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategoriak);
    }

    public void onClickEloetel(View view) {
        Intent intent = new Intent(Kategoriak.this, Eloetelek.class);
        startActivity(intent);

    }
}
