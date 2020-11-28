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
import com.example.location_places.model.Local;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(R.id.frameLayoutMain,
                LocaisFragment.newInstance("",""),
                "LOCAISFRAGMENT",
                "LOCAIS");

        inicializarFirebase();
        }

    public void inicializarFirebase() {
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();




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

                    case R.id.inserirLocal:
                        replaceFragment(R.id.frameLayoutMain,
                                CadastroLocalFragment.newInstance("",null),
                                "INSERIRLOCAISFRAGMENT",
                                "INSERIRLOCAIS");
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
        int data = item.getItemId();
        if(data == R.id.textViewNovoCadastro){
            Local l = new Local();
//https://www.youtube.com/watch?v=PE_riDivk6Y&ab_channel=Extraclasse 17:30
        }

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