package com.aim.bocmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        //-------------------- set navigation header -----------------------------------------------
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.tvName);
        TextView navEmail = headerView.findViewById(R.id.tvEmail);
        navUsername.setText("User user");
        navEmail.setText("user123@gmail.com");

        //------------------ set default as home ---------------------------------------------------
        navigationView.setCheckedItem(R.id.nav_home);
        Fragment fragment = new HomeFragment();
        displaySelectedFragment(fragment);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
            displaySelectedFragment(fragment);
            navigationView.setCheckedItem(R.id.nav_home);
        }else if(id == R.id.nav_payee){
            startActivity(new Intent(this,PayeeMaintainActivity.class));
        }else if(id == R.id.nav_biller){
            startActivity(new Intent(this,BillerMaintainActivity.class));
        }else if(id == R.id.nav_contact){
            fragment = new ContactFragment();
            displaySelectedFragment(fragment);
            navigationView.setCheckedItem(R.id.nav_contact);
        }else if(id == R.id.nav_setting){
            Toast.makeText(this, "This feature not available.", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.nav_logout){
            startActivity(new Intent(this,LogInPage.class));
        }else if(id == R.id.nav_exit){
            finish();
            System.exit(0);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    //------------------- load fragments -----------------------------------------------------------
    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
