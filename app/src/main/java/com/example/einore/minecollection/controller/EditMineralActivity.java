package com.example.einore.minecollection.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.einore.minecollection.R;
import com.example.einore.minecollection.model.Mineral;
import com.example.einore.minecollection.model.Mineral_DAO;

public class EditMineralActivity extends AppCompatActivity {

    private Mineral_DAO mMineral_dao;

    private EditText idEdit;
    private EditText nameEdit;
    private EditText systCristEdit;
    private EditText colorEdit;
    private EditText glowEdit;
    private EditText aspectEdit;
    private EditText clivageEdit;
    private EditText hardnessEdit;
    private EditText densityEdit;
    private EditText priceEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mineral);
        setTitle("Edit");

        TextView idView = (TextView)findViewById(R.id.textViewId);
        TextView nameView = (TextView) findViewById(R.id.textViewName);
        TextView systCristView = (TextView) findViewById(R.id.textViewSystCrist);
        TextView colorView = (TextView) findViewById(R.id.textViewColor);
        TextView glowView = (TextView) findViewById(R.id.textViewGlow);
        TextView aspectView = (TextView) findViewById(R.id.textViewAspect);
        TextView clivageView = (TextView) findViewById(R.id.textViewClivage);
        TextView hardnessView = (TextView) findViewById(R.id.textViewHardness);
        TextView densityView = (TextView) findViewById(R.id.textViewDensity);
        TextView priceView = (TextView) findViewById(R.id.textViewPrice);


        nameEdit = (EditText) findViewById(R.id.editTextName);
        systCristEdit = (EditText) findViewById(R.id.editTextSystCrist);
        colorEdit = (EditText) findViewById(R.id.editTextColor);
        glowEdit = (EditText) findViewById(R.id.editTextGlow);
        aspectEdit = (EditText) findViewById(R.id.editTextAspect);
        clivageEdit = (EditText) findViewById(R.id.editTextClivage);
        hardnessEdit = (EditText) findViewById(R.id.editTextHardness);
        densityEdit = (EditText) findViewById(R.id.editTextDensity);
        priceEdit = (EditText) findViewById(R.id.editTextPrice);

        Button saveButton = (Button) findViewById(R.id.buttonSave);
        Button deleteButton = (Button) findViewById(R.id.buttonDelete);

        Intent listIntent = getIntent();
        final String idMineral = listIntent.getStringExtra("idMineral");

        mMineral_dao = new Mineral_DAO(EditMineralActivity.this);

        mMineral_dao.openForWrite();
        final Mineral mineral = mMineral_dao.getObjectById(idMineral);
        mMineral_dao.close();

        String setId = " Description of mineral ID : " + idMineral;

        idView.setText(setId);
        nameEdit.setText(mineral.getMineral_name());
        systCristEdit.setText(mineral.getMineral_systCrist());
        colorEdit.setText(mineral.getMineral_color());
        glowEdit.setText(mineral.getMineral_glow());
        aspectEdit.setText(mineral.getMineral_aspect());
        clivageEdit.setText(mineral.getMineral_clivage());
        hardnessEdit.setText((int) mineral.getMineral_hardness());
        densityEdit.setText((int) mineral.getMineral_density());
        priceEdit.setText((int) mineral.getMineral_price());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEdit.getText().toString();
                String systCrist = nameEdit.getText().toString();
                String color = nameEdit.getText().toString();
                String glow = nameEdit.getText().toString();
                String aspect = nameEdit.getText().toString();
                String clivage = nameEdit.getText().toString();
                float hardness = Float.parseFloat(nameEdit.getText().toString());
                float density = Float.parseFloat(nameEdit.getText().toString());
                float price = Float.parseFloat(nameEdit.getText().toString());

                if(name.isEmpty()){
                    //error name is empty
                    Toast.makeText(EditMineralActivity.this, "You must enter a name", Toast.LENGTH_SHORT).show();
                }

                if(systCrist.isEmpty()){
                    //error name is empty
                    Toast.makeText(EditMineralActivity.this, "You must enter a systCrist", Toast.LENGTH_SHORT).show();
                }

                if(color.isEmpty()){
                    //error name is empty
                    Toast.makeText(EditMineralActivity.this, "You must enter a color", Toast.LENGTH_SHORT).show();
                }

                if(glow.isEmpty()){
                    //error name is empty
                    Toast.makeText(EditMineralActivity.this, "You must enter a glow", Toast.LENGTH_SHORT).show();
                }

                if(aspect.isEmpty()){
                    //error name is empty
                    Toast.makeText(EditMineralActivity.this, "You must enter an aspect", Toast.LENGTH_SHORT).show();
                }

                if(clivage.isEmpty()){
                    //error name is empty
                    Toast.makeText(EditMineralActivity.this, "You must enter a clivage", Toast.LENGTH_SHORT).show();
                }

                /*if(){
                    //error name is empty
                    Toast.makeText(EditMineralActivity.this, "You must enter an hardness", Toast.LENGTH_SHORT).show();
                }

                if(density.isEmpty()){
                    //error name is empty
                    Toast.makeText(EditMineralActivity.this, "You must enter a density", Toast.LENGTH_SHORT).show();
                }

                if(price.isEmpty()){
                    //error name is empty
                    Toast.makeText(EditMineralActivity.this, "You must enter a price", Toast.LENGTH_SHORT).show();
                }*/

                Mineral updatedMineral = new Mineral();

                updatedMineral.setMineral_name(name);
                updatedMineral.setMineral_systCrist(systCrist);
                updatedMineral.setMineral_color(color);
                updatedMineral.setMineral_glow(glow);
                updatedMineral.setMineral_aspect(aspect);
                updatedMineral.setMineral_clivage(clivage);
                updatedMineral.setMineral_hardness(hardness);
                updatedMineral.setMineral_density(density);
                updatedMineral.setMineral_price(price);

                mMineral_dao.update(idMineral, updatedMineral);

            }
        });
    }
}