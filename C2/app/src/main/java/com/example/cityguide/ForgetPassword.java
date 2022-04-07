package com.example.cityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class ForgetPassword extends AppCompatActivity {
    Button NextForgetPassword_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_forget_password );

        NextForgetPassword_btn = findViewById ( R.id.next_forgetpassword_btn );

        NextForgetPassword_btn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent (ForgetPassword.this,SetNewPassword.class);
                startActivity ( intent1 );
            }
        } );



    }
}