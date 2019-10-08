package com.amtesting.mobileagentatex;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amtesting.mobileagentatex.Booking.booking;
import com.amtesting.mobileagentatex.Helper.SessionManager;
import com.amtesting.mobileagentatex.Navigation.accountcustomer;
import com.amtesting.mobileagentatex.Navigation.login;
import com.amtesting.mobileagentatex.Navigation.profile;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, beranda.FragmentToActivity,
        beranda.BottomNavToActivity{
    // deklaration
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    SessionManager sessionManager;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    // bottom button menu
    Button btn_bottom_booking, btn_bottom_register, btn_bottom_pickup, btn_bottom_droppoint,
            btn_bottom_riwayat;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_atex_main_logo);


        sessionManager = new SessionManager(getApplicationContext());

        // fragment
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        // inisialisasi view button menu bottom
//        btn_bottom_register = (Button) findViewById(R.id.btn_bottom_register);
//        btn_bottom_booking = (Button) findViewById(R.id.btn_bottom_booking);
//        btn_bottom_pickup = (Button) findViewById(R.id.btn_bottom_pickup);
//        btn_bottom_droppoint = (Button) findViewById(R.id.btn_bottom_droppoint);
//        btn_bottom_riwayat = (Button) findViewById(R.id.btn_bottom_riwayat_transaksi);

        // onload beranda first launch activity
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        beranda beranda = new beranda();
        transaction.add(R.id.frame, beranda)
                //.addToBackStack(null)
                //menyimpan fragment
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                //transisi fragment
                .commit();

        // inisiasi drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        // view navigation view
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // session for nav drawer item login or logout
        if(sessionManager.isLoggedIn()==true) {
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
        }else {

        }

        // navigation bottom
         bottomNavigationView = (BottomNavigationView) findViewById(R.id.btm_nav);
        Menu menu = bottomNavigationView.getMenu();
        this.onNavigationItemSelected(menu.findItem(R.id.bottom_pickup));

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
        //Attach the listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.bottom_pickup) {

                        } else if (id == R.id.bottom_register) {
                            fragmentManager = getSupportFragmentManager();
                            transaction = fragmentManager.beginTransaction();
                            register register = new register();
                            transaction.add(R.id.frame, register)
                                    //.addToBackStack(null)
                                    //menyimpan fragment
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    //transisi fragment
                                    .commit();
                        } else if (id == R.id.bottom_booking) {
                            fragmentManager = getSupportFragmentManager();
                            transaction = fragmentManager.beginTransaction();
                            booking booking = new booking();
                            transaction.add(R.id.frame, booking)
                                    .addToBackStack(null)
                                    //menyimpan fragment
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    //transisi fragment
                                    .commit();
                        } else if (id == R.id.bottom_droppoint) {
                            fragmentManager = getSupportFragmentManager();
                            transaction = fragmentManager.beginTransaction();
                            beranda beranda = new beranda();
                            transaction.add(R.id.frame, beranda)
                                    //.addToBackStack(null)
                                    //menyimpan fragment
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    //transisi fragment
                                    .commit();
                        } else if (id == R.id.bottom_riwayat_transaksi) {

                        }
                        /*switch (item.getItemId()) {

                            case R.id.recent_item:

                            case R.id.location_item:

                            case R.id.favorite_item:

                        }*/
                        return true;
                    }
                });

        // action button menu bottom
        // register
//        btn_bottom_register.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//                fragmentManager = getSupportFragmentManager();
//                transaction = fragmentManager.beginTransaction();
//                register register = new register();
//                transaction.add(R.id.frame, register)
//                        .addToBackStack(null)
//                        //menyimpan fragment
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        //transisi fragment
//                        .commit();
//            }
//        });
//        // booking
//        btn_bottom_booking.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                fragmentManager = getSupportFragmentManager();
//                transaction = fragmentManager.beginTransaction();
//                booking booking = new booking();
//                transaction.add(R.id.frame, booking)
//                        .addToBackStack(null)
//                        //menyimpan fragment
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        //transisi fragment
//                        .commit();
//            }
//        });
//        // pickup
//        btn_bottom_pickup.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                fragmentManager = getSupportFragmentManager();
//                transaction = fragmentManager.beginTransaction();
//                beranda beranda = new beranda();
//                transaction.add(R.id.frame, beranda)
//                        //.addToBackStack(null)
//                        //menyimpan fragment
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        //transisi fragment
//                        .commit();
//            }
//        });
    }
    // on back pressed
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            // session for nav drawer item login or logout
        if(sessionManager.isLoggedIn()==true) {
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
        }else {

        }
        } else {
            super.onBackPressed();
        }
    }
    // decision menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
    @Override
    public void SetNavState(int id) {
        NavigationView nav = (NavigationView) findViewById(R.id.nav_view);
        nav.setCheckedItem(id);
    }
    public void SetBtmNavState(int id) {
        BottomNavigationView btmnav = (BottomNavigationView) findViewById(R.id.btm_nav);
        btmnav.setSelectedItemId(id);
    }
    // action for navigation drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_beranda) {
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            beranda beranda = new beranda();
            transaction.add(R.id.frame, beranda)
                    .addToBackStack(null)
                    //menyimpan fragment
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    //transisi fragment
                    .commit();
        } else if (id == R.id.nav_profile) {
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            profile profile = new profile();
            transaction.add(R.id.frame, profile)
                    .addToBackStack(null)
                    //menyimpan fragment
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    //transisi fragment
                    .commit();
        } else if (id == R.id.nav_account) {
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            accountcustomer accountcustomer = new accountcustomer();
            transaction.add(R.id.frame, accountcustomer)
                    //.addToBackStack(null)
                    //menyimpan fragment
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    //transisi fragment
                    .commit();
        } else if (id == R.id.nav_wallet) {

        }
        else if (id == R.id.nav_login) {
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            login login = new login();
            transaction.add(R.id.frame, login)
                    //.addToBackStack(null)
                    //menyimpan fragment
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    //transisi fragment
                    .commit();
        }
        else if (id == R.id.nav_logout) {
            sessionManager.logoutUser();
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            login login = new login();
            transaction.add(R.id.frame, login)
                    //.addToBackStack(null)
                    //menyimpan fragment
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    //transisi fragment
                    .commit();
        }
        // inisiasi drawer layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
