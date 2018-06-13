package com.example.einore.minecollection.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.einore.minecollection.R;

public class HomeActivity extends AppCompatActivity {

    //Components
    Button btnSeeAllMinerals;
    Button btnAddMineral;
    Button btnSignOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");

        Intent intent = getIntent();
        final String idUser = intent.getStringExtra("idUser");

        btnSeeAllMinerals = (Button)findViewById(R.id.button_allMinerals);
        btnAddMineral = (Button)findViewById(R.id.button_addMineral);
        btnSignOut = (Button)findViewById(R.id.button_signOut);

        btnSeeAllMinerals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seeAllMineralIntent = new Intent(HomeActivity.this, ListMineralsActivity.class);
                seeAllMineralIntent.putExtra("idUser", idUser);
                startActivity(seeAllMineralIntent);
            }
        });

        btnAddMineral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newMineralIntent = new Intent(HomeActivity.this, NewMineralActivity.class);
                newMineralIntent.putExtra("idUser", idUser);
                startActivity(newMineralIntent);

            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

    }
}

