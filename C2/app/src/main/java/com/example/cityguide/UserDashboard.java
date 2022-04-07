package com.example.cityguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cityguide.HomeAdapter.FeaturedAdapter;
import com.example.cityguide.HomeAdapter.FeaturedHelperClass;
import com.example.cityguide.HomeAdapter.MostviewedAdapter;
import com.example.cityguide.HomeAdapter.MostviewedHelperClass;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //vảiables
    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler,mostRecycler,categoriesRecycler;
    RecyclerView.Adapter adapter1,adapter2;
    //private GradientDrawable gradient1,gradient2,gradient3,gradient4;
    ImageView menu_Icon;
    LinearLayout contentView;


    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_user_dashboard );

        //Hooks
        featuredRecycler = findViewById ( R.id.featured_recycler );
        mostRecycler = findViewById ( R.id.most_recycle );
        menu_Icon = findViewById ( R.id.menu_icon );
        contentView= findViewById ( R.id.content );
        //categoriesRecycler = findViewById ( R.id.categories_recycler );

        //Menu hooks
        drawerLayout = findViewById ( R.id.drawer_layout );
        navigationView = findViewById ( R.id.navigation_view );


        navigationDrawer();


        featuredRecycler();
        mostRecycler();
        //categoriesRecycler();
    }

//Navi Drawer func
    private void navigationDrawer() {
        //Navigation Drawer
        navigationView.bringToFront (  );
        navigationView.setNavigationItemSelectedListener (  this );
        navigationView.setCheckedItem ( R.id.nav_home );

        menu_Icon.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible ( GravityCompat.START )){
                    drawerLayout.closeDrawer ( GravityCompat.START );
                }
                else {
                    drawerLayout.openDrawer ( GravityCompat.START );
                }
            }
        } );

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor ( getResources ().getColor ( R.color.primary ) );

        drawerLayout.addDrawerListener (new DrawerLayout.SimpleDrawerListener (){
            @Override
            public void onDrawerSlide(View drawerView , float slideOffset) {
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

               // super.onDrawerSlide ( drawerView , slideOffset );

                final float xOffset = drawerView.getWidth ()*slideOffset;
                final float xOffsetDiff = contentView.getWidth() *diffScaledOffset /2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
               // super.onDrawerClosed ( drawerView );
            }
        }  );

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId ()){
            case R.id.nav_all_categories:
                startActivity ( new Intent (getApplicationContext (),AllCategories.class ));
                break;
        }
        switch ( item.getItemId ()){
            case R.id.nav_add_missing_place:
                startActivity ( new Intent (getApplicationContext (),Add_Missing_Place.class ));
                break;
        }

        return true;
    }

    private void mostRecycler() {
        mostRecycler.setHasFixedSize ( true );
        mostRecycler.setLayoutManager ( new LinearLayoutManager ( this,LinearLayoutManager.HORIZONTAL,false ) );

        ArrayList<MostviewedHelperClass> mostview = new ArrayList<> ();

        mostview.add ( new MostviewedHelperClass ( R.drawable. mcdonald_imag,"McDonald's","asdasd asdasdasd dsofgjhs odsifg sd sdfh sdof sdfjks") );
        mostview.add ( new MostviewedHelperClass ( R.drawable. img_1,"Edenrobe","asdasd asdasdasd dsofgjhs odsifg sd sdfh sdof sdfjks") );
        mostview.add ( new MostviewedHelperClass ( R.drawable. img,"Sweet and Bakers","asdasd asdasdasd dsofgjhs odsifg sd sdfh sdof sdfjks") );

        adapter2 = new MostviewedAdapter ( mostview );
        mostRecycler.setAdapter ( adapter2 );
    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize ( true );
        featuredRecycler.setLayoutManager ( new LinearLayoutManager ( this,LinearLayoutManager.HORIZONTAL,false ) );

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<> ();

        featuredLocations.add ( new FeaturedHelperClass ( R.drawable. mcdonald_imag,"McDonald's","asdasd asdasdasd dsofgjhs odsifg sd sdfh sdof sdfjks") );
        featuredLocations.add ( new FeaturedHelperClass ( R.drawable. img_1,"Edenrobe","asdasd asdasdasd dsofgjhs odsifg sd sdfh sdof sdfjks") );
        featuredLocations.add ( new FeaturedHelperClass ( R.drawable. img,"Sweet and Bakers","asdasd asdasdasd dsofgjhs odsifg sd sdfh sdof sdfjks") );


        adapter1 = new FeaturedAdapter ( featuredLocations );
        featuredRecycler.setAdapter ( adapter1 );


        //
       // GradientDrawable gradient1 = new GradientDrawable (GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400,0xffaff600});
    }


    public void callRetailerScreen(View view){

        startActivity ( new Intent (getApplicationContext (),RetailerStartUpScreen.class) );

    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerVisible ( GravityCompat.START )){
            drawerLayout.closeDrawer ( GravityCompat.START );
        }
        else
            super.onBackPressed ();
    }

}