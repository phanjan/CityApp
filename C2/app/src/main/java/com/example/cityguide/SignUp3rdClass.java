package com.example.cityguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class SignUp3rdClass extends AppCompatActivity {

    private static final String  TAG = SignUp3rdClass.class.getName ();

    //CountryCodePicker countryCodePicker;
    ImageView backBtn3;

    TextInputLayout edtPhoneNumber;
    Button btnVerifyPhoneNumber;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_sign_up3rd_class );

        backBtn3= findViewById ( R.id.signup_back_btn3 );
        //countryCodePicker = findViewById ( R.id.country_code_picker );

        initui();
        setTitleToolbar ();
        mAuth = FirebaseAuth.getInstance ();

        btnVerifyPhoneNumber.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String strPhoneNumber = edtPhoneNumber.getEditText ().getText ().toString ().trim ();
                onClickVerifyPhoneNumber(strPhoneNumber);
            }
        } );

    }

    private void setTitleToolbar(){
        if(getSupportActionBar ()!=null){
            getSupportActionBar ().setTitle ( "Verify phone Number" );
        }
    }

    private void initui(){
        edtPhoneNumber = findViewById ( R.id.signup_phone_number );
        btnVerifyPhoneNumber = findViewById ( R.id.signup_next_btn3 );
    }

    private void onClickVerifyPhoneNumber(String strPhoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(strPhoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks( new PhoneAuthProvider.OnVerificationStateChangedCallbacks () {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText (SignUp3rdClass.this,"Verification Failed",Toast.LENGTH_SHORT).show ();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId , @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent ( verificationId , forceResendingToken );
                                gotoEnterOtp(strPhoneNumber,verificationId);
                            }
                        } )          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
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
                                Toast.makeText (SignUp3rdClass.this,"The verification code entered was invalid",Toast.LENGTH_SHORT).show ();
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
    private void gotoEnterOtp(String strPhoneNumber , String verificationId) {
        Intent intent = new Intent (this,VerifyOTP.class);
        intent.putExtra ( "phone_number",strPhoneNumber );
        intent.putExtra ( "verifiCation_id",verificationId );
        startActivity ( intent );
    }


}
/*
    public void callVerifyOTPScreen(View view){

        if(!validatePhoneNumber()){
            return;
        }
        /*String _fullname = getIntent ().getStringExtra ( "fullname2" );
        String _username = getIntent ().getStringExtra ( "username2" );
        String _email = getIntent ().getStringExtra ( "email2" );
        String _password = getIntent ().getStringExtra ( "password2" );
        String _date = getIntent ().getStringExtra ( "date2" );
        String _gender = getIntent ().getStringExtra ( "gender2" );

        String strPhoneNumber = PhoneNumber.getEditText ().getText ().toString().trim ();
        String _phoneNo = PhoneNumber.getFullNumber()+_getUserEnteredPhoneNumber;

        Intent intent= new Intent (getApplicationContext (),VerifyOTP.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String> ( next3 , "transition_verifyOTP" );

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation ( SignUp3rdClass.this , pairs );
            startActivity ( intent , options.toBundle () );
        } else {
            startActivity ( intent );
        }

        /*intent.putExtra ( "fullname",_fullname );
        intent.putExtra ( "username",_username );
        intent.putExtra ( "email",_email );
        intent.putExtra ( "password",_password );
        intent.putExtra ( "date",_date );
        intent.putExtra ( "gender",_gender );
        intent.putExtra ( "phoneNo",_phoneNo );

       startActivity ( intent );
    }

    private boolean validatePhoneNumber(){
        String val = PhoneNumber.getEditText ().getText ().toString ();
        if (val.isEmpty ()) {
            PhoneNumber.setError ( "Field can not be empty" );
            return false;
        } else {
            PhoneNumber.setError ( null );
            PhoneNumber.setErrorEnabled ( false );
            return true;
        }

   }*/
