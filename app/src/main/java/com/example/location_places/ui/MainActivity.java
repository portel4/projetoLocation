package com.example.location_places.ui;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.location_places.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(R.id.frameLayoutMain,
                LocaisFragment.newInstance("",""),
                "LOCAISFRAGMENT",
                "LOCAIS");

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        replaceFragment(R.id.frameLayoutMain,
                                LocaisFragment.newInstance("",""),
                                "LOCAISFRAGMENT",
                                "LOCAIS");
                        return true;

                    case R.id.inserir:
                        replaceFragment(R.id.frameLayoutMain,
                                LocaisFragment.newInstance("",""),
                                "INSERIRLOCAISFRAGMENT",
                                "INSERIRLOCAIS");
                        return true;

                    case R.id.onde:
                        replaceFragment(R.id.frameLayoutMain,
                                new OndeEstouFragment(),
                                "ONDEFRAGMENT",
                                "ONDEESTOU");
                        return true;
                }


                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.editar:
                Intent intent = new Intent(this, CadastroActivity.class);
                startActivity(intent);
                return(true);

                case R.id.sair:
                finish();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
}