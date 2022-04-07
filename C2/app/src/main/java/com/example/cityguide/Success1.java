package com.example.cityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Success1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView ( R.layout.activity_success1 );

        setTitleToolbar ();
        getDataIntent ();
    }

    private void getDataIntent(){
        String strPhoneNumber = getIntent ().getStringExtra ( "phone_number");

        TextView tvUserInfor = findViewById ( R.id.tv_user_infor );
        tvUserInfor.setText (strPhoneNumber  );
    }

    private void setTitleToolbar(){
        if(getSupportActionBar ()!=null){
            getSupportActionBar ().setTitle ( "Success" );
        }
    }
}