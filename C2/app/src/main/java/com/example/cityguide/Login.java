package com.example.cityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Login extends AppCompatActivity {

    ImageView Login_back_btn;
    Button ForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_retailer_login );

        Login_back_btn = findViewById ( R.id.login_back_btn );
        ForgetPassword = findViewById ( R.id.forgetpassword_btn );

        Login_back_btn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent (Login.this,RetailerStartUpScreen.class);
                startActivity ( intent1 );
            }
        } );
        ForgetPassword.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent (Login.this,ForgetPassword.class);
                startActivity ( intent2 );
            }
        } );
    }
}