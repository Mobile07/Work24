package com.pyropy.work24.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.pyropy.work24.R;

public class SignUp extends AppCompatActivity {

    private Button callSignIn, signIn;
    private TextView wlcTxt, logoTxt;
    private ImageView logo;
    private TextInputLayout phoneNumber,fullName,email,password,confirmPassword;
    CountryCodePicker mCountryCodePicker;
    RadioGroup mRadioGroup;
    RadioButton selectedUserType;
    private static final String SHARED_PREFS = "com.pyropy.nodal.STATUS";
    private static final String REG_STATUS = "regstatus";
    String mUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        initComponents();
    }

    private void initComponents() {
        signIn = findViewById(R.id.signup_btn);
        wlcTxt = findViewById(R.id.wlc_txt);
        logoTxt = findViewById(R.id.logo_txt);
        logo = findViewById(R.id.app_dark_logo);
        mCountryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumber = findViewById(R.id.phone);
        fullName = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mRadioGroup = findViewById(R.id.user_type);
        confirmPassword = findViewById(R.id.confirm_password);


        callSignIn = findViewById(R.id.to_login);
        addEventToCallSignInBtn();
        addEventToSignInBtn();
    }

    private void addEventToSignInBtn() {
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUserType() | !validateEmail() | !validatePassword()){
                    return;
                }

                String userFullname = fullName.getEditText().getText().toString().trim();
                String userEmail = email.getEditText().getText().toString().trim();
                String userPassword = password.getEditText().getText().toString().trim();
                int stdType = mRadioGroup.getCheckedRadioButtonId();
                if (stdType == R.id.student){mUserType = "Student";}
                if (stdType == R.id.instructor){mUserType = "Instructor";}
                if (stdType == R.id.freelancer){mUserType = "Freelancer";}
                if (stdType == R.id.buyer){mUserType = "Buyer";}

                String userEnteredPhone = phoneNumber.getEditText().getText().toString().trim();
                if (userEnteredPhone.charAt(0) == '0'){
                    userEnteredPhone = userEnteredPhone.substring(1);
                }
                String userPhone = "+"+mCountryCodePicker.getFullNumber()+userEnteredPhone;

                Intent uIntent = new Intent(getApplicationContext(),VerifyOTP.class);
                uIntent.putExtra("fullname", userFullname);
                uIntent.putExtra("email", userEmail);
                uIntent.putExtra("password", userPassword);
                uIntent.putExtra("phoneNo", userPhone);
                uIntent.putExtra("userType", mUserType);

                startActivity(uIntent);
                //saveRegistration();
                finish();
            }
        });
    }

    private boolean validatePassword() {
        String confirm = confirmPassword.getEditText().getText().toString();
        String pass = password.getEditText().getText().toString();
        if (!pass.isEmpty() | !confirm.isEmpty()){
            if (pass.matches(confirm)){
                confirmPassword.setError(null);
                confirmPassword.setErrorEnabled(false);
                return true;
            }else {
                confirmPassword.setError("password does not match");
                return false;
            }
        }else{
            confirmPassword.setError("ensure to fill both password fields");
            return false;
        }
    }

    private void saveRegistration() {
        SharedPreferences uSharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = uSharedPreferences.edit();
        edit.putString(REG_STATUS, "pending");
        edit.commit();
    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        }else if (!val.matches(checkEmail)){
            email.setError("Invalid Email!");
            return false;
        }else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserType() {
        if (mRadioGroup.getCheckedRadioButtonId()==-1){
            Toast.makeText(getApplicationContext(),"Please select a User type", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    private void addEventToCallSignInBtn() {
        callSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uIntent = new Intent(SignUp.this,Login.class);

//                Pair[] uPairs = new Pair[4];
//                uPairs[0]  = new Pair<View, String>(logo,"logo_image");
//                uPairs[1]  = new Pair<View, String>(signIn,"user_btn");
//                uPairs[2]  = new Pair<View, String>(wlcTxt,"welcome_text");
//                uPairs[3]  = new Pair<View, String>(logoTxt,"logo_text");
//
//                ActivityOptions uOptions = ActivityOptions.makeSceneTransitionAnimation(SignUp.this,uPairs);
                //startActivity(uIntent, uOptions.toBundle());
                startActivity(uIntent);
            }
        });
    }
}