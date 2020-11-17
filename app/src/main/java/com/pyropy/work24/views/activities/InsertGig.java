package com.pyropy.work24.views.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cn.pedant.SweetAlert.SweetAlertDialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pyropy.work24.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class InsertGig extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button pickADate;
    private DatePickerDialog dpd;
    private Toolbar mMyToolbar;
    private ImageView image1,image2,image3;
    public static final int PICK_IMAGE_REQUEST = 1;
    private Uri image1Uri, image2Uri, image3Uri;
    private TextView deliveryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_gig);

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

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    return;
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
                    return;
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
        String date = "You picked the following date: "+dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        deliveryDate.setText(date);
        //Toast.makeText(this,date,Toast.LENGTH_SHORT).show();
        dpd = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            if (image1Uri == null){
                image1Uri = data.getData();
                Glide.with(this).load(image1Uri).into(image1);
            }else if (image2Uri == null){
                image2Uri = data.getData();
                Glide.with(this).load(image2Uri).into(image2);
            }else{
                image3Uri = data.getData();
                Glide.with(this).load(image3Uri).into(image3);
            }
        }
    }
}