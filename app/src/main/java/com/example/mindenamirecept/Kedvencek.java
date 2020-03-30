package com.example.mindenamirecept;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class Kedvencek extends AppCompatActivity {

    DatabaseReference kedvencekReference;
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferencesResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kedvencek);
        recyclerView = findViewById(R.id.KedvencRecyclerView);
        firebaseAuth = FirebaseAuth.getInstance();

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(Kedvencek.this));

        sharedPreferencesResult = getSharedPreferences("SaveKat", Context.MODE_PRIVATE);

        String kategoria = sharedPreferencesResult.getString("Kategoria", "");

        if (firebaseAuth.getCurrentUser() != null){
            kedvencekReference = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getCurrentUser().getUid()).child("kedvencek").child(kategoria);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(Model.class, R.layout.row, ViewHolder.class, kedvencekReference) {
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
}
