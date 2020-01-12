package com.example.mindenamirecept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Regisztracio extends AppCompatActivity {

    TextView txtRCim;
    EditText edtxtRTeljes, edtxtRFelhasz, edtxtRJelszo, edtxtREmail;
    Button bttnRegisztral, bttnVanFiok;
    private DatabaseReference databaseUsers;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisztracio);
        init();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    Toast.makeText(Regisztracio.this, "Sikeres bejelentkezés",Toast.LENGTH_SHORT);
                    Intent intent = new Intent(Regisztracio.this, KezdolapSima.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(Regisztracio.this, "Próbáld meg újra",Toast.LENGTH_SHORT);
                }
            }
        };

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        bttnVanFiok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regisztracio.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bttnRegisztral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser() {
        String username = edtxtRFelhasz.getText().toString();
        String password = edtxtRJelszo.getText().toString();
        String name = edtxtRTeljes.getText().toString().trim();
        String email = edtxtREmail.getText().toString();

        if (!TextUtils.isEmpty(username)){

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Regisztracio.this, "Sikeresen regisztráltál", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Regisztracio.this, KezdolapSima.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(Regisztracio.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            String id = databaseUsers.push().getKey();

            User user = new User(id, username, password, name, email);
            databaseUsers.child(id).setValue(user);





            Intent intent = new Intent(Regisztracio.this, KezdolapSima.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(Regisztracio.this, "Írd be a felhasználónevet és a jelszót", Toast.LENGTH_LONG);
        }
    }

    public void init(){
        txtRCim=(TextView) findViewById(R.id.txtRCim);
        edtxtRTeljes=(EditText) findViewById(R.id.edtxtRTeljes);
        edtxtRFelhasz=(EditText) findViewById(R.id.edtxtRFelhasz);
        edtxtRJelszo=(EditText) findViewById(R.id.edtxtRJelszo);
        edtxtREmail=(EditText) findViewById(R.id.edtxtREmail);
        bttnRegisztral=(Button) findViewById(R.id.bttnRegisztral);
        bttnVanFiok=(Button) findViewById(R.id.bttnVanFiok);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
