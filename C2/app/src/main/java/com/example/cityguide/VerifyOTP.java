package com.example.cityguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

    public static final String TAG = VerifyOTP.class.getName ();

    //PinView pinFromUser;
    //String codeBySystem;
    EditText edtOtp;
    Button btnSendOtp;

    private FirebaseAuth mAuth;

    private String mPhoneNumber;
    private String mVerifiCationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView ( R.layout.acivity_verify_otp );

        initui();
        setTitleToolbar ();
        getDataIntent();

        mAuth =FirebaseAuth.getInstance ();

        // pinFromUser = findViewById ( R.id.pin_view );
        //String _phoneNo = getIntent ().getStringExtra ( "phoneNo" );
        //sendVericationCodeToUser(_phoneNo);
        btnSendOtp.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String strOtp = edtOtp.getText ().toString ().trim ();
                onClickSendOtpCode(strOtp);
            }
        } );
    }

    private void onClickSendOtpCode(String strOtp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerifiCationId, strOtp);
        signInWithPhoneAuthCredential(credential);
    }

    private void getDataIntent(){
        mPhoneNumber = getIntent ().getStringExtra ( "phone_number" );
        mVerifiCationId = getIntent ().getStringExtra ( "verifiCation_id" );

    }

    private void setTitleToolbar(){
        if(getSupportActionBar ()!=null){
            getSupportActionBar ().setTitle ( "Enter OTP Code" );
        }
    }

    private void initui(){
        edtOtp = findViewById ( R.id.edt_otp );
        btnSendOtp = findViewById ( R.id.btn_send_otp_code );
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            goToSuccess(user.getPhoneNumber ());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText (VerifyOTP.this,"The verification code entered was invalid",Toast.LENGTH_SHORT).show ();
                            }
                        }
                    }
                });
    }
    private void goToSuccess(String phoneNumber) {
        Intent intent = new Intent (this,Success1.class);
        intent.putExtra ( "phone_number",phoneNumber );
        startActivity ( intent );
    }
}

    /*private void sendVericationCodeToUser(String phoneNo) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance ();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity ( this )           // Activity (for callback binding)
                        .setCallbacks(mCallbacks)         // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks () {
                @Override
                public void onCodeSent(@NonNull String s , @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent ( s , forceResendingToken );
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode ();
                    if(code!=null){
                        pinFromUser.setText ( code );
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText ( VerifyOTP.this, e.getMessage (),Toast.LENGTH_SHORT).show ();
               }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential ( codeBySystem,code );
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance ();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText ( VerifyOTP.this,"Verification Completed",Toast.LENGTH_SHORT ).show ();

                       } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText ( VerifyOTP.this,"Verification Not Complete! Try again." ,Toast.LENGTH_SHORT).show ();
                           }
                        }
                    }
                });
    }

    public void callNextScreenFromOTP(View view){
        String code = pinFromUser.getText ().toString ();
        if(!code.isEmpty ()){
            verifyCode ( code );
        }
    }

     */
