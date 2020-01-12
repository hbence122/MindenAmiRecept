package com.example.mindenamirecept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class KezdolapSima extends AppCompatActivity {

    Button bttnKijelentkez;
    FirebaseAuth FirebaseAuth;
    private FirebaseAuth.AuthStateListener AuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kezdolap_sima);
        init();

        bttnKijelentkez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.google.firebase.auth.FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(KezdolapSima.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        bttnKijelentkez=(Button) findViewById(R.id.bttnKijelentkez);
    }
}
