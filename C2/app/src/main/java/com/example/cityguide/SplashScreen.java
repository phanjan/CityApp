package com.example.cityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    static int SPLASH_TIMER = 3000;

    //Hooks
    ImageView backgroundImage;
    TextView poweredByLine;
    //Animatoins
    Animation leftAnim,rightAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.splash_screen );

        //Hooks
        backgroundImage = findViewById ( R.id. background_image);
        poweredByLine = findViewById ( R.id. powered_by_line);
        //Animations
        leftAnim= AnimationUtils.loadAnimation (this,R.anim.left_anim);
        rightAnim= AnimationUtils.loadAnimation (this,R.anim.right_anim);
        //set animations on element
        backgroundImage.setAnimation ( leftAnim );
        poweredByLine.setAnimation ( rightAnim );

        new Handler ().postDelayed ( new Runnable () {
            @Override
            public void run() {
                Intent intent = new Intent (getApplicationContext (),OnBoarding.class);
                startActivity ( intent );
                finish ();
            }
        },SPLASH_TIMER );


    }
}