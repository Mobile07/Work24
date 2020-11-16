package com.pyropy.work24.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pyropy.work24.R;
import com.pyropy.work24.database.FirebaseUtil;

public class Login extends AppCompatActivity {

    private Button callSignUp;
    private MaterialButton logIn;
    private TextView wlcTxt, logoTxt;
    private ImageView logo;
    private TextInputLayout userName, userPass;
    String mUserId,mPassword;
    RelativeLayout myProgressBar;
    private String mUserNode;
    private FirebaseUtil mUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        Intent uIntent = getIntent();
        if(uIntent != null){
            mUserId = uIntent.getStringExtra("userId");
        }

        initComponents();
    }

    private void initComponents() {
        logIn = findViewById(R.id.signin_btn);
        wlcTxt = findViewById(R.id.logo_name);
        logoTxt = findViewById(R.id.slogan_name);
        logo = findViewById(R.id.logo);
        userName = findViewById(R.id.username);
        userPass = findViewById(R.id.password);
        myProgressBar = findViewById(R.id.my_progress);

        callSignUp = findViewById(R.id.call_signup);
        addEventToBtn();
        addLoginEventToBtn();
        mUtil = FirebaseUtil.getInstances(getApplicationContext());
    }

    private void addLoginEventToBtn() {
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAndLoginUser();
//                Intent uIntent = new Intent(Login.this, DashboardActivity.class);
//                startActivity(uIntent);
            }
        });
    }

    private void validateAndLoginUser() {
        if(!validateMail()) {
            return;
        }
        myProgressBar.setVisibility(View.VISIBLE);

        String email = userName.getEditText().getText().toString().trim();
        String mailParts[] = email.split("@");
        mUserNode = mailParts[0];

        String userPassword = userPass.getEditText().getText().toString().trim();
        mPassword=userPassword;

        Query checkUser = mUtil.mFirebaseDatabase.getReference("Users").orderByChild("email").equalTo(email);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    userName.setError(null);
                    userName.setErrorEnabled(false);

                    String systemPassword = dataSnapshot.child(mUserNode).child("password").getValue(String.class);
                    validatePassword(systemPassword, dataSnapshot);
                }else{
                    myProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"User does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                myProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validatePassword(String systemPassword, DataSnapshot dataSnapshot) {

        if (systemPassword.equals(mPassword)){
            userPass.setError(null);
            userPass.setErrorEnabled(false);

            String fullname = dataSnapshot.child(mUserNode).child("fullname").getValue(String.class);
            String email = dataSnapshot.child(mUserNode).child("email").getValue(String.class);
            String uPhone = dataSnapshot.child(mUserNode).child("phone").getValue(String.class);
            String usertype = dataSnapshot.child(mUserNode).child("usertype").getValue(String.class);

            myProgressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), fullname+"\n"+email+"\n"+uPhone+"\n"+usertype, Toast.LENGTH_SHORT).show();
            Intent uIntent = new Intent(Login.this, DashboardActivity.class);
            startActivity(uIntent);
            finish();
        }else{
            myProgressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"Wrong password, try again!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateMail() {
        String val = userName.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            userName.setError("Field cannot be empty");
            return false;
        }else if (!val.matches(checkEmail)){
            userName.setError("Invalid Email!");
            return false;
        }else{
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private void addEventToBtn() {
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uIntent = new Intent(Login.this,SignUp.class);

//                Pair[] uPairs = new Pair[4];
//                uPairs[0]  = new Pair<View, String>(logo,"logo_image");
//                uPairs[1]  = new Pair<View, String>(logIn,"user_btn");
//                uPairs[2]  = new Pair<View, String>(wlcTxt,"welcome_text");
//                uPairs[3]  = new Pair<View, String>(logoTxt,"logo_text");
//
//                ActivityOptions uOptions = ActivityOptions.makeSceneTransitionAnimation(Login.this,uPairs);
//                startActivity(uIntent, uOptions.toBundle());
                startActivity(uIntent);
            }
        });
    }
}