package com.chynadoll.cryptocoins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.chynadoll.cryptocoins.Currencies.CurrenciesFragment;
import com.chynadoll.cryptocoins.Phantom_calculator.PhantomCalculatorFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottom_navigation extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(navListner);
        getSupportFragmentManager().beginTransaction().replace(R.id.fr, new CurrenciesFragment()).commit();

    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment =null;
                    switch (item.getItemId()){
                        case R.id.navigation_curriencies:
                            selectedFragment=new CurrenciesFragment();
                            break;

                        case R.id.navigation_calculator:
                            selectedFragment=new PhantomCalculatorFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fr,selectedFragment).commit();
                    return true;
                }
            };
}