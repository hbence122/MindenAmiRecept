package com.example.mindenamirecept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KezdolapSima extends AppCompatActivity {

    Button bttnKijelentkez;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener AuthStateListener;
    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kezdolap_sima);
        init();

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

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
        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void onClickKeres(View view) {
        Intent intent = new Intent(KezdolapSima.this, Kategoriak.class);
        startActivity(intent);
    }

    public void onClickFeltolt(View view) {



                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                 if (firebaseUser == null){

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(KezdolapSima.this);
                    builder1.setMessage("Recept feltöltéséhez regisztráció szükséges!");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Regisztrálok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(KezdolapSima.this, Regisztracio.class);
                                    startActivity(intent);
                                }
                            }

                    );

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

                 else if (firebaseUser != null) {
                     Intent intent = new Intent(KezdolapSima.this, Feltoltes.class);
                     startActivity(intent);

        }







    }
}

