package com.example.mindenamirecept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Eloetelek extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference EloetelekReference;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferencesResult;
    //EditText search_edit_text;


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



    private void firebaseSearch(String searchText){
        Query firebaseSearchQuery = EloetelekReference.orderByChild("receptNev").startAt(searchText).endAt(searchText + "\uf0ff");

        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(Model.class, R.layout.row, ViewHolder.class, firebaseSearchQuery) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int i) {
                        viewHolder.setDetails(getApplicationContext(), model.getReceptNev(), model.getReceptLeiras(), model.getImage() );

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                                TextView mDetailTv = view.findViewById(R.id.Leirastxt);
                                ImageView mImageView = view.findViewById(R.id.rimageView);

                                String mTitle = mTitleTv.getText().toString();
                                String mDesc = mDetailTv.getText().toString();
                                Drawable mDrawable = mImageView.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();

                                Intent intent = new Intent(view.getContext(), ReceptMegjelenit.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes = stream.toByteArray();
                                intent.putExtra("image", bytes);
                                intent.putExtra("receptNev", mTitle);
                                intent.putExtra("receptLeiras", mDesc);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder;
                    }

                };


        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        }


        return super.onOptionsItemSelected(item);
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

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                                TextView mDetailTv = view.findViewById(R.id.Leirastxt);
                                ImageView mImageView = view.findViewById(R.id.rimageView);

                                String mTitle = mTitleTv.getText().toString();
                                String mDesc = mDetailTv.getText().toString();
                                Drawable mDrawable = mImageView.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();

                                Intent intent = new Intent(view.getContext(), ReceptMegjelenit.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes = stream.toByteArray();
                                intent.putExtra("image", bytes);
                                intent.putExtra("receptNev", mTitle);
                                intent.putExtra("receptLeiras", mDesc);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder;
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
        //search_edit_text = (EditText) findViewById(R.id.search_edit_text);




    }

    public void loadKategoria(){

    }


    public void onClickRecept(View view) {
    }
}
