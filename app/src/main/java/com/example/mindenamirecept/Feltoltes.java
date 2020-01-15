package com.example.mindenamirecept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feltoltes extends AppCompatActivity {

    Spinner KatSpinner;
    EditText FeltoltEtxtNev, feltoltEtxt1, feltoltEtxt2, feltoltEtxt3, feltoltEtxt4, feltoltEtxt5, feltoltEtxtKeszit;
    Button btnFeltolt;
    private DatabaseReference databaseReceptek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feltoltes);
        init();

        databaseReceptek = FirebaseDatabase.getInstance().getReference("receptek");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Feltoltes.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kategoriak));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        KatSpinner.setAdapter(myAdapter);

        btnFeltolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecept();
            }
        });
    }

    private void addRecept() {
        String receptNev = FeltoltEtxtNev.getText().toString().trim();
        String receptKat = KatSpinner.getSelectedItem().toString();
        String receptHozz1 = feltoltEtxt1.getText().toString().trim();
        String receptHozz2 = feltoltEtxt2.getText().toString().trim();
        String receptHozz3 = feltoltEtxt3.getText().toString().trim();
        String receptHozz4 = feltoltEtxt4.getText().toString().trim();
        String receptHozz5 = feltoltEtxt5.getText().toString().trim();
        String receptKeszites = feltoltEtxtKeszit.getText().toString();

        if (!TextUtils.isEmpty(receptNev)){



            String id = databaseReceptek.push().getKey();

            Recept recept = new Recept(id, receptNev, receptKat, receptHozz1, receptHozz2, receptHozz3, receptHozz4, receptHozz5, receptKeszites);
            databaseReceptek.child(id).setValue(recept);
            Toast.makeText(Feltoltes.this, "Sikeres feltöltés", Toast.LENGTH_SHORT);

            Intent intent = new Intent(Feltoltes.this, KezdolapSima.class);
            finish();



        }
        else{
            Toast.makeText(Feltoltes.this, "Írd be a recept nevét", Toast.LENGTH_LONG);
        }
    }

    public void init(){
        KatSpinner = (Spinner) findViewById(R.id.KatSpinner);
        FeltoltEtxtNev = (EditText) findViewById(R.id.FeltoltEtxtNev);
        feltoltEtxt1 = (EditText) findViewById(R.id.feltoltEtxt1);
        feltoltEtxt2 = (EditText) findViewById(R.id.feltoltEtxt2);
        feltoltEtxt3 = (EditText) findViewById(R.id.feltoltEtxt3);
        feltoltEtxt4 = (EditText) findViewById(R.id.feltoltEtxt4);
        feltoltEtxt5 = (EditText) findViewById(R.id.feltoltEtxt5);
        feltoltEtxtKeszit = (EditText) findViewById(R.id.feltoltEtxtKeszit);
        btnFeltolt = (Button) findViewById(R.id.btnFeltolt);
    }
}
