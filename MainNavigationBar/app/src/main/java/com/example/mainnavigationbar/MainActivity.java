package com.example.mainnavigationbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView myBtnNavView;

    HomeFragment myHomeFrg = new HomeFragment();
    ProfileFragment myProfFrg = new ProfileFragment();
    ContentFragment myContFrg = new ContentFragment();
    BuysFragment myBuyFrg = new BuysFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBtnNavView = findViewById(R.id.bnavbar1);

        getSupportFragmentManager().beginTransaction().replace(R.id.frmLyt ,myHomeFrg).commit();


        BadgeDrawable badgeDrawable = myBtnNavView.getOrCreateBadge(R.id.Inicio);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(4);

        myBtnNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.Inicio){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frmLyt, myHomeFrg).commit();
                    return true;
                } else if (item.getItemId() == R.id.Perfil){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frmLyt, myProfFrg).commit();
                    return true;
                } else if (item.getItemId() == R.id.Contenido){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frmLyt, myContFrg).commit();
                    return true;
                } else if (item.getItemId() == R.id.Compras){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frmLyt, myBuyFrg).commit();
                    return true;
                }
                return false;
            }
        });

    }
}