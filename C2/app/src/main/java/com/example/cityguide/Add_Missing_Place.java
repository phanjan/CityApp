package com.example.cityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class Add_Missing_Place extends AppCompatActivity {

    ImageView BackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_add_missing_place );

        BackPressed = findViewById ( R.id.back_pressed );

        BackPressed.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Add_Missing_Place.super.onBackPressed ();
            }
        } );

    }
}