package com.pyropy.work24.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pyropy.work24.R;
import com.pyropy.work24.database.UserHelper;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

    PinView mPinView;
    ImageView closeBtn;
    MaterialButton verifyBtn, resendCodeBtn;
    TextView caption;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mCode;
    FirebaseAuth mAuth;
    private static final String SHARED_PREFS = "com.pyropy.nodal.STATUS";
    private static final String REG_STATUS = "regstatus";
    private String mUserPassword;
    private String mUserEmail;
    private String mUserFullname;
    private String mUserPhoneNumber, mUsertype;
    private FirebaseDatabase mRootNode;
    private DatabaseReference mDbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);

        mPinView = findViewById(R.id.otp_code);
        closeBtn = findViewById(R.id.close_verification);
        verifyBtn = findViewById(R.id.verifyCode);
        resendCodeBtn = findViewById(R.id.resend_code);
        caption = findViewById(R.id.caption);

        mUserPassword = getIntent().getStringExtra("password");
        mUserEmail = getIntent().getStringExtra("email");
        mUserFullname = getIntent().getStringExtra("fullname");
        mUsertype = getIntent().getStringExtra("userType");

        mUserPhoneNumber = getIntent().getStringExtra("phoneNo");
        String captionTxt = "Enter one time password sent to "+ mUserPhoneNumber;
        caption.setText(captionTxt);

        sendVerificationCodeToUser(mUserPhoneNumber);

        //add event listener to closeBtn
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void sendVerificationCodeToUser(String userPhoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                userPhoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            mPinView.setText(credential.getSmsCode());
            storeNewUserData();
            signInUserAutomatically(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.

            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId;
            mResendToken = token;

            // ...
        }
    };

    private void storeNewUserData() {
        mRootNode = FirebaseDatabase.getInstance();
        mDbref = mRootNode.getReference("Users");

        UserHelper addNewUser = new UserHelper(mUserFullname,mUserPassword,mUserEmail,mUserPhoneNumber,mUsertype);
        String mailParts[] = mUserEmail.split("@");
        String userNode = mailParts[0];
        mDbref.child(userNode).setValue(addNewUser);
    }

    private void signInUserAutomatically(PhoneAuthCredential credential) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(getApplicationContext(), "Verification Completed", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = task.getResult().getUser();
                            String userId = user.getUid();
                            Intent uIntent = new Intent(VerifyOTP.this,Login.class);
                            uIntent.putExtra("userId", userId);
                            startActivity(uIntent);
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(), "invalid code", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(getApplicationContext(), "Something went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        storeNewUserData();
        signInUser(credential);
    }

    private void signInUser(PhoneAuthCredential credential){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(getApplicationContext(), "Verification Completed", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = task.getResult().getUser();
                            String userId = user.getUid();
                            Intent uIntent = new Intent(VerifyOTP.this,Login.class);
                            uIntent.putExtra("userId", userId);
                            startActivity(uIntent);
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(), "invalid code", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(getApplicationContext(), "Something went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signInUserManually(View view){
        String code  = mPinView.getText().toString();
        if (!code.isEmpty()){
            verifyCode(code);
        }
    }

    public void resendOTP(View view){
        sendVerificationCodeToUser(mUserPhoneNumber);
    }
}