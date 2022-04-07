package com.example.cityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class ForgetPasswordSuccessMessage extends AppCompatActivity {

    Button LoginSuccessPassword_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_forget_password_success_message );

        LoginSuccessPassword_btn = findViewById ( R.id.login_passwordupdate_btn );
        LoginSuccessPassword_btn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent (ForgetPasswordSuccessMessage.this,Login.class);
                startActivity ( intent1 );
            }
        } );

    }
}