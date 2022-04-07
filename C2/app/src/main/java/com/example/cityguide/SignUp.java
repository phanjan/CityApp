package com.example.cityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    //Variables
    ImageView backBtn1;
    Button  login,next1;
    TextView titleText;
    //Get Date Variables
    TextInputLayout fullname, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_retailer_sign_up );

        backBtn1 = findViewById ( R.id.signup_back_btn1 );
        next1 = findViewById ( R.id.signup_next_btn1 );
        login = findViewById ( R.id.signup_login_btn );

        //Hoooks for getting data
        fullname = findViewById ( R.id.signup_fullname );
        username = findViewById ( R.id.signup_username );
        email = findViewById ( R.id.signup_email );
        password = findViewById ( R.id.signup_password );

    }

    public void callNextSignUpScreen1(View view) {

        if (!validateFullname () | !validateUsername () | !validateEmail ()  | !validatePassword ()){
           return;
        }
        Intent intent = new Intent ( getApplicationContext () , SignUp2ndClass.class );

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String> ( next1 , "transition_next_btn1" );

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation ( SignUp.this , pairs );
            startActivity ( intent , options.toBundle () );
        } else {
            startActivity ( intent );
        }
    }

    public void callBackSignUpScreen(View view) {
        Intent intent = new Intent ( getApplicationContext () , RetailerStartUpScreen.class );
        startActivity ( intent );
    }

    public boolean validateFullname() {
        String val = fullname.getEditText ().getText ().toString ().trim ();
          if (val.isEmpty ()) {
            fullname.setError ( "Field can not be empty" );
            return false;
        } else {
            fullname.setError ( null );
            fullname.setErrorEnabled ( false );
            return true;
        }
    }

    public boolean validateUsername() {
        String val = username.getEditText ().getText ().toString ().trim ();
        String checkspaces = "\\A\\w{1,20}\\z";

        if (val.isEmpty ()) {
            username.setError ( "Field can not be empty" );
            return false;
        } else if (val.length () > 20) {
            username.setError ( "Username is too large!" );
            return false;
        } else if (!val.matches ( checkspaces )) {
            username.setError ( "No white sapces are allowed!" );
            return false;
        } else {
            username.setError ( null );
            username.setErrorEnabled ( false );
            return true;
        }
    }

    public boolean validateEmail() {
        String val = email.getEditText ().getText ().toString ().trim ();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty ()) {
            email.setError ( "Field can not be empty" );
            return false;
        }  else if (!val.matches ( checkEmail )) {
            email.setError ( "Invalid Email!" );
            return false;
        } else {
            email.setError ( null );
            email.setErrorEnabled ( false );
            return true;
        }
    }

    public boolean validatePassword() {
        String val = password.getEditText ().getText ().toString ().trim ();
        String checkPassword = "^"+
                                //"(?=.*[0-9])"+        //at least 1 digit
                                //"()?=.*[a-z]"+        //at least 1 lower case letter
                                //"?=.*[A-Z]"+          //at least 1 upper case letter
                                "(?=.*[a-zA-Z])"+       //any letter
                                "(?=.*[@#$%^&+=])"+     //at least 1 special character
                                "(?=\\S+$)"+            // no white space
                                ".{4,}"+                //at least 4 characters
                                "$";

        if (val.isEmpty ()) {
            password.setError ( "Field can not be empty" );
            return false;
        }  else if (!val.matches ( checkPassword )) {
            password.setError ( "Password should contain 4 characters!" );
            return false;
        } else {
            password.setError ( null );
            password.setErrorEnabled ( false );
            return true;
        }
    }

}