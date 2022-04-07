package com.example.cityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SignUp2ndClass extends AppCompatActivity {

    ImageView backBtn2;
    Button next2, login;
    TextView titleText;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_sign_up2nd_class );

        backBtn2 = findViewById ( R.id.signup_back_btn2 );
        next2 = findViewById ( R.id.signup_next_btn2 );
        login = findViewById ( R.id.signup_login_btn );
        titleText = findViewById ( R.id.signup_title_text );
        radioGroup = findViewById ( R.id.radio_group );
        datePicker = findViewById ( R.id.age_picker );

    }

    public void callNextSignUpScreen2(View view) {

        if(!validateGender () | !validateAge ())
        {
               return;
        }

        selectedGender= findViewById ( radioGroup.getCheckedRadioButtonId () );
        String  gender2 = selectedGender.getText ().toString ();

        int day = datePicker.getDayOfMonth ();
        int month = datePicker.getMonth ();
        int year = datePicker.getYear ();

        String date2 = day+"/"+month+"/"+year;

        String fullname2 = getIntent ().getStringExtra ( "fullname" );
        String email2 = getIntent ().getStringExtra ( "email" );
        String username2 = getIntent ().getStringExtra ( "username" );
        String password2 = getIntent ().getStringExtra ( "password" );

        Intent intent = new Intent ( getApplicationContext () , SignUp3rdClass.class );

        intent.putExtra ( "fullname2", fullname2 );
        intent.putExtra ( "username2", username2 );
        intent.putExtra ( "email2", email2 );
        intent.putExtra ( "password2", password2 );
        intent.putExtra ( "date2",date2 );
        intent.putExtra ( "gender2",gender2 );


        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String> ( next2 , "transition_next_btn2" );

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation ( SignUp2ndClass.this , pairs );
            startActivity ( intent , options.toBundle () );
        } else {
            startActivity ( intent );
        }
    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId () == -1) {
            Toast.makeText ( this , "Please Select Gender" , Toast.LENGTH_LONG ).show ();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAge() {
        int currentYear = Calendar.getInstance ().get ( Calendar.YEAR );
        int userAge = datePicker.getYear ();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 14) {
           Toast.makeText ( this , "You are not eligible to apply" , Toast.LENGTH_SHORT ).show ();
            return false;
        } else {
            return true;
        }
    }

}