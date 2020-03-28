package com.example.mindenamirecept;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import id.zelory.compressor.Compressor;

public class Feltoltes extends AppCompatActivity {

    Spinner KatSpinner;
    EditText FeltoltEtxtNev, feltoltEtxt1, feltoltEtxt2, feltoltEtxt3, feltoltEtxt4, feltoltEtxt5,
            feltoltEtxt6,
            feltoltEtxt7,
            feltoltEtxt8,
            feltoltEtxt9,
            feltoltEtxt10,
            feltoltEtxt11,
            feltoltEtxt12,
            feltoltEtxt13,
            feltoltEtxt14,
            feltoltEtxt15,
            feltoltEtxt16,
            feltoltEtxt17,
            feltoltEtxt18,
            feltoltEtxt19,
            feltoltEtxtKeszit;
    Button btnFeltolt, HozzavaloHozzaadbtn;
    ImageView mPostIv;
    private DatabaseReference databaseReceptek, IdReference;
    private StorageReference mStorageReference;
    dbhelper dbhelper;
    long EloMaxID = 0;
    int kattDarab = 3;
    String mStoragePath = "OsszesFeltoltottKep/";
    String mDatabasePath = "receptek";

    Uri mFilePathUri;
    Uri compressedImage;
    ProgressDialog mProgressDialog;
    Compressor compressor;

    int IMAGE_REQUEST_CODE = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feltoltes);
        init();

        databaseReceptek = FirebaseDatabase.getInstance().getReference("receptek");
        mStorageReference = FirebaseStorage.getInstance().getReference();
       mProgressDialog = new ProgressDialog(Feltoltes.this);


        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Feltoltes.this,
        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kategoriak));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        KatSpinner.setAdapter(myAdapter);


        //String receptKat = KatSpinner.getSelectedItem().toString();




        IdReference= FirebaseDatabase.getInstance().getReference("receptek").child("Előételek");



        IdReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    EloMaxID=(dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mPostIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Kép kiválasztása"), IMAGE_REQUEST_CODE);
            }
        });










        btnFeltolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //addReceptSQLite();


                addRecept();
            }
        });


        HozzavaloHozzaadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (kattDarab == 3){
                    feltoltEtxt4.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 4){
                    feltoltEtxt5.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 5){
                    feltoltEtxt6.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 6){
                    feltoltEtxt7.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 7){
                    feltoltEtxt8.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 8){
                    feltoltEtxt9.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 9){
                    feltoltEtxt10.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 10){
                    feltoltEtxt11.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 11){
                    feltoltEtxt12.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 12){
                    feltoltEtxt13.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 13){
                    feltoltEtxt14.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 14){
                    feltoltEtxt15.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 15){
                    feltoltEtxt16.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 16){
                    feltoltEtxt17.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 17){
                    feltoltEtxt18.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 18){
                    feltoltEtxt19.setVisibility(EditText.VISIBLE);
                    kattDarab++;
                }
                else if (kattDarab == 19){
                    kattDarab = 3;
                }
            }
        });
    }



    public void addReceptSQLite() {

        String receptNev = FeltoltEtxtNev.getText().toString().trim();
        String receptKat = KatSpinner.getSelectedItem().toString();
        String receptHozz1 = feltoltEtxt1.getText().toString().trim();
        String receptHozz2 = feltoltEtxt2.getText().toString().trim();
        String receptHozz3 = feltoltEtxt3.getText().toString().trim();
        String receptHozz4 = feltoltEtxt4.getText().toString().trim();
        String receptHozz5 = feltoltEtxt5.getText().toString().trim();
        String receptKeszites = feltoltEtxtKeszit.getText().toString().trim();

        Boolean adatRogzites = dbhelper.adatRogzites(receptNev, receptKat, receptHozz1, receptHozz2,receptHozz3, receptHozz4, receptHozz5, receptKeszites);

        if (adatRogzites == true){

            Toast.makeText(Feltoltes.this, "Sikeres feltöltés", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Feltoltes.this, KezdolapSima.class);
            finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){

            mFilePathUri = data.getData();


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mFilePathUri);
                mPostIv.setImageBitmap(bitmap);
            }
            catch (Exception e){
                Toast.makeText(Feltoltes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addRecept() {

        if (mFilePathUri != null){
            mProgressDialog.setTitle("Recept feltöltése folyamatban...");
            mProgressDialog.show();
            StorageReference storageReference2 = mStorageReference.child(mStoragePath + System.currentTimeMillis()+ "." + getFileExtension(mFilePathUri));

            storageReference2.putFile(mFilePathUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful());
                    Uri downloadUrl = urlTask.getResult();

                    String receptNev = FeltoltEtxtNev.getText().toString().trim();

                    String receptKat = KatSpinner.getSelectedItem().toString();
                    String receptHozz1 = feltoltEtxt1.getText().toString().trim();
                    String receptHozz2 = feltoltEtxt2.getText().toString().trim();
                    String receptHozz3 = feltoltEtxt3.getText().toString().trim();
                    String receptHozz4 = feltoltEtxt4.getText().toString().trim();
                    String receptHozz5 = feltoltEtxt5.getText().toString().trim();
                    String receptHozz6 = feltoltEtxt6.getText().toString().trim();
                    String receptHozz7 = feltoltEtxt7.getText().toString().trim();
                    String receptHozz8 = feltoltEtxt8.getText().toString().trim();
                    String receptHozz9 = feltoltEtxt9.getText().toString().trim();
                    String receptHozz10 = feltoltEtxt10.getText().toString().trim();
                    String receptHozz11 = feltoltEtxt11.getText().toString().trim();
                    String receptHozz12 = feltoltEtxt12.getText().toString().trim();
                    String receptHozz13 = feltoltEtxt13.getText().toString().trim();
                    String receptHozz14 = feltoltEtxt14.getText().toString().trim();
                    String receptHozz15 = feltoltEtxt15.getText().toString().trim();
                    String receptHozz16 = feltoltEtxt16.getText().toString().trim();
                    String receptHozz17 = feltoltEtxt17.getText().toString().trim();
                    String receptHozz18 = feltoltEtxt18.getText().toString().trim();
                    String receptHozz19 = feltoltEtxt19.getText().toString().trim();

                    String receptKeszites = feltoltEtxtKeszit.getText().toString();
                    long id = EloMaxID+1;

                    Recept recept = new Recept(id, receptNev, receptKat, downloadUrl.toString(), receptNev.toLowerCase(),
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

                    databaseReceptek.child(receptKat).child(receptNev).setValue(recept);

                    Toast.makeText(Feltoltes.this, "Sikeres feltöltés", Toast.LENGTH_SHORT);

                    Intent intent = new Intent(Feltoltes.this, KezdolapSima.class);
                    finish();



                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mProgressDialog.dismiss();
                            Toast.makeText(Feltoltes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            mProgressDialog.setTitle("Feltöltés...");
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());


                        }
                    });





        }
        else{
                Toast.makeText(Feltoltes.this, "Kérlek válassz fényképet", Toast.LENGTH_SHORT).show();
        }





    }

    private String getFileExtension(Uri mFilePathUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(mFilePathUri));
    }


    public void editTextHozzaad(){
        int kattDarab = 3;

        switch (kattDarab){
            case 3 :
                feltoltEtxt4.setVisibility(EditText.VISIBLE);
                kattDarab++;
                break;
            case 4 :
                feltoltEtxt5.setVisibility(EditText.VISIBLE);
                kattDarab++;
                break;
            case 5 :
                feltoltEtxt6.setVisibility(EditText.VISIBLE);
                kattDarab++;
                break;
                default:
                    System.out.println("Invalid gradle");
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
        feltoltEtxt6 = (EditText) findViewById(R.id.feltoltEtxt6);
        feltoltEtxt7 = (EditText) findViewById(R.id.feltoltEtxt7);
        feltoltEtxt8 = (EditText) findViewById(R.id.feltoltEtxt8);
        feltoltEtxt9 = (EditText) findViewById(R.id.feltoltEtxt9);
        feltoltEtxt10 = (EditText) findViewById(R.id.feltoltEtxt10);
        feltoltEtxt11 = (EditText) findViewById(R.id.feltoltEtxt11);
        feltoltEtxt12 = (EditText) findViewById(R.id.feltoltEtxt12);
        feltoltEtxt13 = (EditText) findViewById(R.id.feltoltEtxt13);
        feltoltEtxt14 = (EditText) findViewById(R.id.feltoltEtxt14);
        feltoltEtxt15 = (EditText) findViewById(R.id.feltoltEtxt15);
        feltoltEtxt16 = (EditText) findViewById(R.id.feltoltEtxt16);
        feltoltEtxt17 = (EditText) findViewById(R.id.feltoltEtxt17);
        feltoltEtxt18 = (EditText) findViewById(R.id.feltoltEtxt18);
        feltoltEtxt19 = (EditText) findViewById(R.id.feltoltEtxt19);
        mPostIv = (ImageView) findViewById(R.id.pImageIv);

        feltoltEtxtKeszit = (EditText) findViewById(R.id.feltoltEtxtKeszit);
        btnFeltolt = (Button) findViewById(R.id.btnFeltolt);
        HozzavaloHozzaadbtn = (Button) findViewById(R.id.HozzavaloHozzaadbtn);
        dbhelper =new dbhelper(this);
    }
}
