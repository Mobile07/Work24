package com.pyropy.work24.views.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import cn.pedant.SweetAlert.SweetAlertDialog;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pyropy.work24.R;
import com.pyropy.work24.database.FirebaseUtil;
import com.pyropy.work24.database.GigHelper;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class InsertGig extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button pickADate, createGig;
    private DatePickerDialog dpd;
    private Toolbar mMyToolbar;
    private ImageView image1,image2,image3;
    public static final int PICK_IMAGE_REQUEST = 1;
    private Uri image1Uri, image2Uri, image3Uri;
    private TextView deliveryDate;
    private boolean mStoragePermissions=false;
    public static final int REQUEST_CODE = 1234;

    private EditText title,description,price;
    private Spinner category;
    private String mGigTitle;
    private String mGigDesc;
    private String mGigPrice;
    private String mCategory;
    private String mDelivery_date;

    private FirebaseUtil mUtil;
    public GigHelper mGigHelper;
    //ArrayList<GigHelper> gigs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_gig);

        //gigs = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        mMyToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mMyToolbar);
        pickADate = findViewById(R.id.duedate);

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        deliveryDate = findViewById(R.id.delivery_date);

        title = findViewById(R.id.gig_title);
        description = findViewById(R.id.gig_description);
        price = findViewById(R.id.gig_price);
        category = findViewById(R.id.category);
        createGig = findViewById(R.id.create_gig);
        mUtil = FirebaseUtil.getInstances(this);
        mGigHelper = new GigHelper();

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mStoragePermissions){
                    verifyStoragePermissions();
                }
                if (image1Uri != null){
                    image1Uri = null;
                }
                Intent uIntent = new Intent();
                uIntent.setType("image/*");
                uIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(uIntent,PICK_IMAGE_REQUEST);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (img1UriDoesNotExists()){
                    Toast.makeText(getApplicationContext(), "Select Image 1", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (image2Uri != null){
                    image2Uri = null;
                }
                Intent uIntent = new Intent();
                uIntent.setType("image/*");
                uIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(uIntent,PICK_IMAGE_REQUEST);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (img2UriDoesNotExists()){
                    Toast.makeText(getApplicationContext(), "Select Image 2", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (image3Uri != null){
                    image3Uri = null;
                }
                Intent uIntent = new Intent();
                uIntent.setType("image/*");
                uIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(uIntent,PICK_IMAGE_REQUEST);
            }
        });


        pickADate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                dpd = DatePickerDialog.newInstance(
                        InsertGig.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getSupportFragmentManager(), "Datepickerdialog");
            }
        });

        createGig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collectAndValidateGigDetails();
            }
        });
    }

    private void collectAndValidateGigDetails() {
        mGigTitle = title.getText().toString().trim();
        mGigDesc = description.getText().toString().trim();
        mGigPrice = price.getText().toString().trim();
        mCategory = category.getSelectedItem().toString();
        mDelivery_date = deliveryDate.getText().toString();

        if(mGigTitle != null && mGigDesc !=null && mCategory != null && mDelivery_date != null && mGigPrice != null
                && image1Uri != null && image2Uri != null && image3Uri != null){
            //Toast.makeText(this, mGigTitle+"\n"+mGigDesc+"\n"
            //        +mGigPrice+"\n"+mCategory+"\n"+mDelivery_date+"\n"
            //        +image1Uri+"\n"+image2Uri+"\n"+image3Uri, Toast.LENGTH_SHORT).show();

            mGigHelper.setGigTitle(mGigTitle);
            mGigHelper.setDescription(mGigDesc);
            mGigHelper.setDeliveryDate(mDelivery_date);
            mGigHelper.setPrice(mGigPrice);
            mGigHelper.setCategory(mCategory);
            mGigHelper.setAuthor(FirebaseUtil.mAuthEmail);

            String key = mUtil.mDbRef.child("Gigs").push().getKey();
            Map<String, Object> gigValues = mGigHelper.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/Gigs/" + key, gigValues);
            childUpdates.put("/user-gigs/" + mUtil.mAuthEmail + "/" + key, gigValues);

            mUtil.mDbRef.updateChildren(childUpdates).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(), "gig added successfully", Toast.LENGTH_SHORT).show();
                    clearDetails();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT);
                }
            });

        }
        else{
            Toast.makeText(this, "You must fill all \n fields before Creating", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearDetails() {
        title.setText("");
        description.setText("");
        category.setSelection(0);
        image1.setImageURI(null);
        image2.setImageURI(null);
        image3.setImageURI(null);
        price.setText("");
        deliveryDate.setText("");
    }

    private boolean img2UriDoesNotExists() {
        if (image2Uri == null){
            new SweetAlertDialog(InsertGig.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Select Image 2 First!");
            return true;
        }
        return false;
    }

    private boolean img1UriDoesNotExists() {
        if (image1Uri == null){
            new SweetAlertDialog(InsertGig.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Select Image 1 First!");
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag("Datepickerdialog");
        if(dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        deliveryDate.setText(date);
        //Toast.makeText(this,date,Toast.LENGTH_SHORT).show();
        dpd = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            if (image1Uri == null){
                image1Uri = data.getData();

                String localTime = Long.toString(System.currentTimeMillis());
                StorageReference ref = mUtil.mStoreRef.child("work_24_gigs").child(localTime+"_"+image1Uri.getLastPathSegment());
                UploadTask uUploadTask = ref.putFile(image1Uri);

                Task<Uri> urlTask = uUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            mGigHelper.setImg1Uri(downloadUri.toString());
                            Glide.with(getApplicationContext()).load(downloadUri.toString()).into(image1);
                        } else {
                            Toast.makeText(getApplicationContext(), "Image upload failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }else if (image2Uri == null){
                image2Uri = data.getData();


                String localTime = Long.toString(System.currentTimeMillis());
                StorageReference ref = mUtil.mStoreRef.child("work_24_gigs").child(localTime+"_"+image2Uri.getLastPathSegment());
                UploadTask uUploadTask = ref.putFile(image2Uri);

                Task<Uri> urlTask = uUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            mGigHelper.setImg2Uri(downloadUri.toString());
                            Glide.with(getApplicationContext()).load(downloadUri.toString()).into(image2);
                        } else {
                            Toast.makeText(getApplicationContext(), "Image 2 upload failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }else{
                image3Uri = data.getData();


                String localTime = Long.toString(System.currentTimeMillis());
                StorageReference ref = mUtil.mStoreRef.child("work_24_gigs").child(localTime+"_"+image3Uri.getLastPathSegment());
                UploadTask uUploadTask = ref.putFile(image3Uri);

                Task<Uri> urlTask = uUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            mGigHelper.setImg3Uri(downloadUri.toString());
                            Glide.with(getApplicationContext()).load(downloadUri.toString()).into(image3);
                        } else {
                            Toast.makeText(getApplicationContext(), "Image upload failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        }
    }

    private void verifyStoragePermissions(){
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[1]) == PackageManager.PERMISSION_GRANTED) {
            mStoragePermissions = true;
        }else {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
        }
    }
}