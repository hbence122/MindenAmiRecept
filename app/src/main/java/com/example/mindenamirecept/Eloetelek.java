package com.example.mindenamirecept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Eloetelek extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference EloetelekReference;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferencesResult;


    dbhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eloetelek);
        init();
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(Eloetelek.this));

        //adatLekerdezesFoetelek();
        sharedPreferencesResult = getSharedPreferences("SaveKat", Context.MODE_PRIVATE);

        String kategoria = sharedPreferencesResult.getString("Kategoria", "");


        //GlobalClass globalClass = (GlobalClass) getApplicationContext();
        EloetelekReference = FirebaseDatabase.getInstance().getReference().child("receptek").child(kategoria);

        //ActionBar actionBar = getSupportActionBar();

        //actionBar.setTitle("Előételek");







       /* EloetelekReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           String ReceptNev = dataSnapshot.child("receptNev").getValue().toString();
           //txtRecept0.setText(ReceptNev);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/





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
            //txtRecept0.setText(stringBuffer.toString());
            //txtRecept1.setText(stringBuffer2.toString());
            Toast.makeText(this, "Adat sikeresen lekérve!", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Nincs adat amit lekérjen!", Toast.LENGTH_SHORT).show();

    }



    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(Model.class, R.layout.row, ViewHolder.class, EloetelekReference) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int i) {

                        viewHolder.setDetails(getApplicationContext(), model.getReceptNev(), model.getReceptLeiras(), model.getImage() );
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);





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
        //txtRecept0 = (TextView) findViewById(R.id.txtRecept0);
        //txtRecept1 = (TextView) findViewById(R.id.txtRecept1);
        dbhelper = new dbhelper(Eloetelek.this);
        recyclerView = findViewById(R.id.recyclerView);




    }

    public void loadKategoria(){

    }


    public void onClickRecept(View view) {
    }
}
