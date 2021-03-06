package com.example.mindenamirecept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReceptMegjelenit extends AppCompatActivity {

    TextView mTitleTv, mDetailtv, Hozz1, Hozz2, Hozz3, Hozz4, Hozz5,
            Hozz6,
            Hozz7,
            Hozz8,
            Hozz9,
            Hozz10,
            Hozz11,
            Hozz12,
            Hozz13,
            Hozz14,
            Hozz15,
            Hozz16,
            Hozz17,
            Hozz18,
            Hozz19,
            Elkeszites;
    ImageView mImageIv, kedvencIv;

    private FirebaseAuth firebaseAuth;
    DatabaseReference ReceptReference, kedvencReference, imageReference;
    SharedPreferences sharedPreferencesRecept;
    String  image, id;
    String receptHozz1;
    String receptHozz2;
    String receptHozz3;
    String receptHozz4;
    String receptHozz5;
    String receptHozz6;
    String receptHozz7;
    String receptHozz8;
    String receptHozz9;
    String receptHozz10;
    String receptHozz11;
    String receptHozz12;
    String receptHozz13;
    String receptHozz14;
    String receptHozz15;
    String receptHozz16;
    String receptHozz17;
    String receptHozz18;
    String receptHozz19;
    String receptLeiras;

    Boolean isKedvenc = false;

    String receptKeszites;

    String kedvencE;

    List<KedvencRecept> favList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recept_megjelenit);
        init();

        sharedPreferencesRecept = getSharedPreferences("SaveKat", Context.MODE_PRIVATE);

        String kategoria = sharedPreferencesRecept.getString("Kategoria", "");

        final String receptNev = getIntent().getStringExtra("receptNev");

        ReceptReference = FirebaseDatabase.getInstance().getReference().child("receptek").child(kategoria).child(receptNev);

        if (firebaseAuth.getCurrentUser() != null){
            kedvencReference = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getCurrentUser().getUid()).child("kedvencek").child(kategoria);
        }



        imageReference = FirebaseDatabase.getInstance().getReference().child("receptek").child(kategoria).child(receptNev);







        if (firebaseAuth.getCurrentUser() != null) {

            kedvencReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(receptNev).exists()) {
                        kedvencE = dataSnapshot.child(receptNev).child("receptNev").getValue().toString();



                        if (kedvencE.equals(receptNev)) {
                            kedvencIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));

                            isKedvenc = true;

                           // kedvencCb.setButtonDrawable(R.drawable.ic_favorite_black_24dp);
                            //kedvencCb.setChecked(true);
                        }
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }




      /*  kedvencCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (firebaseAuth.getCurrentUser() == null) {
                    Toast.makeText(ReceptMegjelenit.this, "A funkció használatához kérlek jelentkezz be!", Toast.LENGTH_SHORT).show();
                    buttonView.setChecked(false);
                }

                else {
                    KedvencRecept kedvencrecept = new KedvencRecept(id, receptNev, "Elkészítési idő: "+receptLeiras, image,
                            receptHozz1,
                            receptHozz2,
                            receptHozz3,
                            receptHozz4,
                            receptHozz5,
                            receptHozz6,
                            receptHozz7,
                            receptHozz8,
                            receptHozz9,
                            receptHozz10,
                            receptHozz11,
                            receptHozz12,
                            receptHozz13,
                            receptHozz14,
                            receptHozz15,
                            receptHozz16,
                            receptHozz17,
                            receptHozz18,
                            receptHozz19,
                            receptKeszites);



                    if (isChecked) {
                        kedvencReference.child(receptNev).setValue(kedvencrecept);
                        kedvencCb.setButtonDrawable(R.drawable.ic_favorite_black_24dp);


                        kedvencCb.setChecked(true);
                        Toast.makeText(ReceptMegjelenit.this, "Hozzáadva a kedvencekhez!", Toast.LENGTH_SHORT).show();

                    } else {
                        kedvencReference.child(receptNev).setValue(null);
                        kedvencCb.setChecked(false);
                        kedvencCb.setButtonDrawable(R.drawable.ic_favorite_border_black_24dp);
                        Toast.makeText(ReceptMegjelenit.this, "Eltávolítva a kedvencekből!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });*/




        imageReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                image = dataSnapshot.child("image").getValue().toString();
                id = dataSnapshot.child("id").getValue().toString();
                receptHozz1 = dataSnapshot.child("receptHozz1").getValue().toString();
                receptHozz2 = dataSnapshot.child("receptHozz2").getValue().toString();
                receptHozz3 = dataSnapshot.child("receptHozz3").getValue().toString();
                receptHozz4 = dataSnapshot.child("receptHozz4").getValue().toString();
                receptHozz5 = dataSnapshot.child("receptHozz5").getValue().toString();
                receptHozz6 = dataSnapshot.child("receptHozz6").getValue().toString();
                receptHozz7 = dataSnapshot.child("receptHozz7").getValue().toString();
                receptHozz8 = dataSnapshot.child("receptHozz8").getValue().toString();
                receptHozz9 = dataSnapshot.child("receptHozz9").getValue().toString();
                receptHozz10 = dataSnapshot.child("receptHozz10").getValue().toString();
                receptHozz11 = dataSnapshot.child("receptHozz11").getValue().toString();
                receptHozz12 = dataSnapshot.child("receptHozz12").getValue().toString();
                receptHozz13 = dataSnapshot.child("receptHozz13").getValue().toString();
                receptHozz14 = dataSnapshot.child("receptHozz14").getValue().toString();
                receptHozz15 = dataSnapshot.child("receptHozz15").getValue().toString();
                receptHozz16 = dataSnapshot.child("receptHozz16").getValue().toString();
                receptHozz17 = dataSnapshot.child("receptHozz17").getValue().toString();
                receptHozz18 = dataSnapshot.child("receptHozz18").getValue().toString();
                receptHozz19 = dataSnapshot.child("receptHozz19").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ReceptReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("receptHozz1").getValue() != null){
                    String receptHozz1 = dataSnapshot.child("receptHozz1").getValue().toString();
                    Hozz1.setVisibility(TextView.VISIBLE);
                    Hozz1.setText(receptHozz1);

                }
                if (dataSnapshot.child("receptHozz2").getValue() != null){
                    String receptHozz2 = dataSnapshot.child("receptHozz2").getValue().toString();
                    Hozz2.setVisibility(TextView.VISIBLE);
                    Hozz2.setText(receptHozz2);

                }
                if (dataSnapshot.child("receptHozz3").getValue() != null){
                    String receptHozz3 = dataSnapshot.child("receptHozz3").getValue().toString();
                    Hozz3.setVisibility(TextView.VISIBLE);
                    Hozz3.setText(receptHozz3);

                }
                if (dataSnapshot.child("receptHozz4").getValue() != null){
                    String receptHozz4 = dataSnapshot.child("receptHozz4").getValue().toString();
                    Hozz4.setVisibility(TextView.VISIBLE);
                    Hozz4.setText(receptHozz4);

                }
                if (dataSnapshot.child("receptHozz5").getValue() != null){
                    String receptHozz5 = dataSnapshot.child("receptHozz5").getValue().toString();
                    Hozz5.setVisibility(TextView.VISIBLE);
                    Hozz5.setText(receptHozz5);

                }
                if (dataSnapshot.child("receptHozz6").getValue() != null){
                    String receptHozz6 = dataSnapshot.child("receptHozz6").getValue().toString();
                    Hozz6.setVisibility(TextView.VISIBLE);
                    Hozz6.setText(receptHozz6);

                }
                if (dataSnapshot.child("receptHozz7").getValue() != null){
                    String receptHozz7 = dataSnapshot.child("receptHozz7").getValue().toString();
                    Hozz7.setVisibility(TextView.VISIBLE);
                    Hozz7.setText(receptHozz7);

                }
                if (dataSnapshot.child("receptHozz8").getValue() != null){
                    String receptHozz8 = dataSnapshot.child("receptHozz8").getValue().toString();
                    Hozz8.setVisibility(TextView.VISIBLE);
                    Hozz8.setText(receptHozz8);

                }

                if (dataSnapshot.child("receptHozz9").getValue() != null){
                    String receptHozz9 = dataSnapshot.child("receptHozz9").getValue().toString();
                    Hozz9.setVisibility(TextView.VISIBLE);
                    Hozz9.setText(receptHozz9);

                }
                if (dataSnapshot.child("receptHozz10").getValue() != null){
                    String receptHozz10 = dataSnapshot.child("receptHozz10").getValue().toString();
                    Hozz10.setVisibility(TextView.VISIBLE);
                    Hozz10.setText(receptHozz10);

                }
                if (dataSnapshot.child("receptHozz11").getValue() != null){
                    String receptHozz11 = dataSnapshot.child("receptHozz11").getValue().toString();
                    Hozz11.setVisibility(TextView.VISIBLE);
                    Hozz11.setText(receptHozz11);

                }

                if (dataSnapshot.child("receptHozz12").getValue() != null){
                    String receptHozz12 = dataSnapshot.child("receptHozz12").getValue().toString();
                    Hozz12.setVisibility(TextView.VISIBLE);
                    Hozz12.setText(receptHozz12);

                }
                if (dataSnapshot.child("receptHozz13").getValue() != null){
                    String receptHozz13 = dataSnapshot.child("receptHozz13").getValue().toString();
                    Hozz13.setVisibility(TextView.VISIBLE);
                    Hozz13.setText(receptHozz13);

                }
                if (dataSnapshot.child("receptHozz14").getValue() != null){
                    String receptHozz14 = dataSnapshot.child("receptHozz14").getValue().toString();
                    Hozz14.setVisibility(TextView.VISIBLE);
                    Hozz14.setText(receptHozz14);

                }
                if (dataSnapshot.child("receptHozz15").getValue() != null){
                    String receptHozz15 = dataSnapshot.child("receptHozz15").getValue().toString();
                    Hozz15.setVisibility(TextView.VISIBLE);
                    Hozz15.setText(receptHozz15);

                }
                if (dataSnapshot.child("receptHozz16").getValue() != null){
                    String receptHozz16 = dataSnapshot.child("receptHozz16").getValue().toString();
                    Hozz16.setVisibility(TextView.VISIBLE);
                    Hozz16.setText(receptHozz16);

                }
                if (dataSnapshot.child("receptHozz17").getValue() != null){
                    String receptHozz17 = dataSnapshot.child("receptHozz17").getValue().toString();
                    Hozz17.setVisibility(TextView.VISIBLE);
                    Hozz17.setText(receptHozz17);

                }
                if (dataSnapshot.child("receptHozz18").getValue() != null){

                    String receptHozz18 = dataSnapshot.child("receptHozz18").getValue().toString();
                    Hozz18.setVisibility(TextView.VISIBLE);
                    Hozz18.setText(receptHozz18);

                }
                if (dataSnapshot.child("receptHozz19").getValue() != null){

                    String receptHozz19 = dataSnapshot.child("receptHozz19").getValue().toString();
                    Hozz19.setVisibility(TextView.VISIBLE);
                    Hozz19.setText(receptHozz19);

                }
                if (dataSnapshot.child("receptKeszites").getValue().toString() != null){
                    String receptKeszites = dataSnapshot.child("receptKeszites").getValue().toString();
                    Elkeszites.setText(receptKeszites);

                }

               // String receptHozz1 = dataSnapshot.child("receptHozz1").getValue().toString();
               /* String receptHozz2 = dataSnapshot.child("receptHozz2").getValue().toString();
                String receptHozz3 = dataSnapshot.child("receptHozz3").getValue().toString();
                String receptHozz4 = dataSnapshot.child("receptHozz4").getValue().toString();
                String receptHozz5 = dataSnapshot.child("receptHozz5").getValue().toString();
                String receptKeszites = dataSnapshot.child("receptKeszites").getValue().toString();
                Hozz1.setText("- "+receptHozz1);
                Hozz2.setText("- "+receptHozz2);
                Hozz3.setText("- "+receptHozz3);
                Hozz4.setText("- "+receptHozz4);
                Hozz5.setText("- "+receptHozz5);
                //Elkeszites.setText(receptKeszites);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        byte[] bytes = getIntent().getByteArrayExtra("image");
        //String receptNev = getIntent().getStringExtra("receptNev");
        String receptLeiras = getIntent().getStringExtra("receptLeiras");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        mTitleTv.setText(receptNev);
        mDetailtv.setText(receptLeiras);
        mImageIv.setImageBitmap(bmp);


    }



    public void init(){
        mTitleTv = findViewById(R.id.rTitleTvR);
        mDetailtv = findViewById(R.id.LeirastxtR);
        mImageIv = findViewById(R.id.rimageViewR);
       // kedvencCb = findViewById(R.id.kedvencCb);
        kedvencIv = findViewById(R.id.kedvencIv);
        Hozz1 = findViewById(R.id.Hozz1);
        Hozz2 = findViewById(R.id.Hozz2);
        Hozz3 = findViewById(R.id.Hozz3);
        Hozz4 = findViewById(R.id.Hozz4);
        Hozz5 = findViewById(R.id.Hozz5);
        Hozz6 = findViewById(R.id.Hozz6);
        Hozz7 = findViewById(R.id.Hozz7);
        Hozz8 = findViewById(R.id.Hozz8);
        Hozz9 = findViewById(R.id.Hozz9);
        Hozz10 = findViewById(R.id.Hozz10);
        Hozz11 = findViewById(R.id.Hozz11);
        Hozz12 = findViewById(R.id.Hozz12);
        Hozz13 = findViewById(R.id.Hozz13);
        Hozz14 = findViewById(R.id.Hozz14);
        Hozz15 = findViewById(R.id.Hozz15);
        Hozz16 = findViewById(R.id.Hozz16);
        Hozz17 = findViewById(R.id.Hozz17);
        Hozz18 = findViewById(R.id.Hozz18);
        Hozz19 = findViewById(R.id.Hozz19);

        Elkeszites = findViewById(R.id.Elkeszites);
        firebaseAuth = FirebaseAuth.getInstance();
        favList = new ArrayList<>();
    }

    public void onClickKedvenc(View view) {

        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(ReceptMegjelenit.this, "A funkció használatához kérlek jelentkezz be!", Toast.LENGTH_SHORT).show();

        }
        else{
            if (isKedvenc == false){
                String receptNev = getIntent().getStringExtra("receptNev");
                String receptLeiras = getIntent().getStringExtra("receptLeiras");
                KedvencRecept kedvencrecept = new KedvencRecept(id, receptNev, receptLeiras, image,
                        receptHozz1,
                        receptHozz2,
                        receptHozz3,
                        receptHozz4,
                        receptHozz5,
                        receptHozz6,
                        receptHozz7,
                        receptHozz8,
                        receptHozz9,
                        receptHozz10,
                        receptHozz11,
                        receptHozz12,
                        receptHozz13,
                        receptHozz14,
                        receptHozz15,
                        receptHozz16,
                        receptHozz17,
                        receptHozz18,
                        receptHozz19,
                        receptKeszites);

                kedvencReference.child(receptNev).setValue(kedvencrecept);
                Toast.makeText(ReceptMegjelenit.this, "Hozzáadva a kedvencekhez!", Toast.LENGTH_SHORT).show();
                kedvencIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));

                isKedvenc = true;


            }

            else{
                String receptNev = getIntent().getStringExtra("receptNev");
                kedvencReference.child(receptNev).setValue(null);
                Toast.makeText(ReceptMegjelenit.this, "Eltávolítva a kedvencekből!", Toast.LENGTH_SHORT).show();
                kedvencIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
                isKedvenc = false;

            }
        }
    }
}
