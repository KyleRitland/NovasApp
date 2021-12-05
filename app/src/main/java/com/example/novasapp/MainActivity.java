package com.example.novasapp;

import android.os.Bundle;

import com.example.novasapp.fragments.AddFragment;
import com.example.novasapp.fragments.DataFragment;
import com.example.novasapp.fragments.HomeFragment;
import com.example.novasapp.fragments.LogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigation;
    FrameLayout frameLayout;

    private HomeFragment homeFragment = new HomeFragment();
    private DataFragment dataFragment = new DataFragment();
    private LogFragment logFragment = new LogFragment();
    private AddFragment addFragment = new AddFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.bottom_navigation);
        frameLayout = findViewById(R.id.fl_wrapper);

        makeCurrentFragment(homeFragment);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.ic_add:
                        makeCurrentFragment(addFragment);
                        return true;

                    case R.id.ic_log:
                        makeCurrentFragment(logFragment);
                        return true;

                    case R.id.ic_data:
                        makeCurrentFragment(dataFragment);
                        return true;

                    case R.id.ic_home:
                        makeCurrentFragment(homeFragment);
                        return true;
                }

                return false;
            }
        });


    }

    private void makeCurrentFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_wrapper, fragment);
        fragmentTransaction.commit();

    }









}