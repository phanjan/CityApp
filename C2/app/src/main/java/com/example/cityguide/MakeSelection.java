package com.example.cityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MakeSelection extends AppCompatActivity {
    Button ViaSms_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_make_selection );

        ViaSms_btn = findViewById ( R.id.Viasms_btn );
        ViaSms_btn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MakeSelection.this,SetNewPassword.class);
                startActivity ( intent );
            }
        } );
    }
}