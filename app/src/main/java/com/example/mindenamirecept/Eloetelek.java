package com.example.mindenamirecept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Eloetelek extends AppCompatActivity {

    TextView txtRecept0 , txtRecept1;
    DatabaseReference EloetelekReference;


    dbhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eloetelek);
        init();

        //adatLekerdezesFoetelek();

        EloetelekReference= FirebaseDatabase.getInstance().getReference().child("receptek").child("Előételek").child("1");
        EloetelekReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           String ReceptNev = dataSnapshot.child("receptNev").getValue().toString();
           txtRecept0.setText(ReceptNev);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }

    public void adatLekerdezesFoetelek()
    {
        Cursor eredmeny = dbhelper.adatLekerdezesEloetelekdb();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        if (eredmeny!= null && eredmeny.getCount() > 0)
        {
            while(eredmeny.moveToNext())
            {

                stringBuffer.append("" + eredmeny.getString(0) + "\n");
                stringBuffer2.append("" + eredmeny.getString(0) + "\n");

            }
            txtRecept0.setText(stringBuffer.toString());
            txtRecept1.setText(stringBuffer2.toString());
            Toast.makeText(this, "Adat sikeresen lekérve!", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Nincs adat amit lekérjen!", Toast.LENGTH_SHORT).show();

    }



    @Override
    protected void onStart() {
        super.onStart();



        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                receptList.clear();

                for (DataSnapshot receptSnapshot: dataSnapshot.getChildren()){
                    Recept recept = receptSnapshot.getValue(Recept.class);

                    receptList.add(recept);
                }

                ReceptList adapter = new ReceptList(Eloetelek.this, receptList);
                listViewReceptek.setText((CharSequence) adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }

    public void init(){
        txtRecept0 = (TextView) findViewById(R.id.txtRecept0);
        txtRecept1 = (TextView) findViewById(R.id.txtRecept1);
        dbhelper = new dbhelper(Eloetelek.this);



    }


    public void onClickRecept(View view) {
    }
}
