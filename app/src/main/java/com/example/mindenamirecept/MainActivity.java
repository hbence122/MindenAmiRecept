package com.example.mindenamirecept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView txtCim ,txtVendeg;
    EditText edtxtFelhasz, edtxtJelszo;
    Button bttnBelepes;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        authStateListener= new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    Toast.makeText(MainActivity.this, "Sikeres bejelentkezés",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, KezdolapSima.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(MainActivity.this, "Próbáld meg újra",Toast.LENGTH_SHORT).show();
                }
            }
        };



        bttnBelepes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtxtFelhasz.getText().toString();
                String password = edtxtJelszo.getText().toString();
                if (username.isEmpty()){
                    edtxtFelhasz.setError("Írd be a felhasználóneved");
                }
                else if (password.isEmpty()){
                    edtxtJelszo.setError("Írd be a jelszavadat");
                    edtxtJelszo.requestFocus();
                }
                else if ((!username.isEmpty() && !password.isEmpty())){

                    firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Hiba történt. Jelentkezz be újra!",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intent = new Intent(MainActivity.this, KezdolapSima.class);
                                startActivity(intent);
                                finish();

                            }

                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Error nemtudommitjelent", Toast.LENGTH_SHORT);
                }
            }
        });

    }

    public void init(){
        txtCim=(TextView) findViewById(R.id.txtCim);
        txtVendeg=(TextView) findViewById(R.id.txtVendeg);
        edtxtFelhasz=(EditText) findViewById(R.id.edtxtFelhasz);
        edtxtJelszo=(EditText) findViewById(R.id.edtxtJelszo);
        bttnBelepes=(Button) findViewById(R.id.bttnBelepes);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this, KezdolapSima.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
