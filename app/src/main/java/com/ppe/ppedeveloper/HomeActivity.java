package com.ppe.ppedeveloper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNav;
    FrameLayout container;
    Fragment homeFragment, analyticsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

    }
    private void init(){
        bottomNav = findViewById(R.id.bottomNav);
        container = findViewById(R.id.container);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.home);
        homeFragment = new HomeFragment();
        analyticsFragment= new AnalyticsFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
                return true;

            case R.id.Analytics:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new AnalyticsFragment()).commit();
                return true;
        }
        return false;
    }


}