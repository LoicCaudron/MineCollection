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
import com.example.einore.minecollection.model.Chemical;
import com.example.einore.minecollection.model.Chemical_DAO;
import com.example.einore.minecollection.model.Location;
import com.example.einore.minecollection.model.Location_DAO;
import com.example.einore.minecollection.model.Mineral;
import com.example.einore.minecollection.model.Mineral_DAO;

public class EditMineralActivity extends AppCompatActivity {

    private Mineral_DAO mMineral_dao;
    private Location_DAO mLocation_dao;
    private Chemical_DAO mChemical_dao;


    private EditText nameEdit;
    private EditText minAssEdit;
    private EditText systCristEdit;
    private EditText colorEdit;
    private EditText glowEdit;
    private EditText aspectEdit;
    private EditText clivageEdit;
    private EditText hardnessEdit;
    private EditText densityEdit;
    private EditText priceEdit;
    private EditText locationEdit;
    private EditText chemicalFormulaEdit;
    private EditText chemicalClassEdit;
    private EditText cityEdit;
    private EditText areaEdit;
    private EditText countryEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mineral);
        setTitle("Edit");

        try{

            TextView idView = (TextView)findViewById(R.id.textViewId);
            TextView nameView = (TextView) findViewById(R.id.textViewName);
            TextView minAssView = (TextView) findViewById(R.id.textViewMinAss);
            TextView systCristView = (TextView) findViewById(R.id.textViewSystCrist);
            TextView colorView = (TextView) findViewById(R.id.textViewColor);
            TextView glowView = (TextView) findViewById(R.id.textViewGlow);
            TextView aspectView = (TextView) findViewById(R.id.textViewAspect);
            TextView clivageView = (TextView) findViewById(R.id.textViewClivage);
            TextView hardnessView = (TextView) findViewById(R.id.textViewHardness);
            TextView densityView = (TextView) findViewById(R.id.textViewDensity);
            TextView priceView = (TextView) findViewById(R.id.textViewPrice);
            TextView locationView = (TextView) findViewById(R.id.textViewLocation);
            TextView chemicalFormulaView = (TextView) findViewById(R.id.textViewChemicalFormula);
            TextView chemicalClassView = (TextView) findViewById(R.id.textViewChemicalClass);
            TextView cityView = (TextView) findViewById(R.id.textViewCity);
            TextView areaView = (TextView) findViewById(R.id.textViewArea);
            TextView countryView = (TextView) findViewById(R.id.textViewCountry);

            nameEdit = (EditText) findViewById(R.id.editTextName);
            minAssEdit = (EditText) findViewById(R.id.editTextMinAss);
            systCristEdit = (EditText) findViewById(R.id.editTextSystCrist);
            colorEdit = (EditText) findViewById(R.id.editTextColor);
            glowEdit = (EditText) findViewById(R.id.editTextGlow);
            aspectEdit = (EditText) findViewById(R.id.editTextAspect);
            clivageEdit = (EditText) findViewById(R.id.editTextClivage);
            hardnessEdit = (EditText) findViewById(R.id.editTextHardness);
            densityEdit = (EditText) findViewById(R.id.editTextDensity);
            priceEdit = (EditText) findViewById(R.id.editTextPrice);
            locationEdit = (EditText) findViewById(R.id.editTextLocation);
            chemicalFormulaEdit = (EditText) findViewById(R.id.editTextChemicalFormula);
            chemicalClassEdit = (EditText) findViewById(R.id.editTextChemicalClass);
            cityEdit = (EditText) findViewById(R.id.editTextCity);
            areaEdit = (EditText) findViewById(R.id.editTextArea);
            countryEdit = (EditText) findViewById(R.id.editTextCountry);

            Button saveButton = (Button) findViewById(R.id.buttonSave);
            Button deleteButton = (Button) findViewById(R.id.buttonDelete);

            Intent listIntent = getIntent();
            final String idMineral = listIntent.getStringExtra("idMineral");
            final String fk_user = listIntent.getStringExtra("idUser");

            mMineral_dao = new Mineral_DAO(EditMineralActivity.this);
            mChemical_dao = new Chemical_DAO(EditMineralActivity.this);
            mLocation_dao = new Location_DAO(EditMineralActivity.this);

            mMineral_dao.openForRead();
            mChemical_dao.openForRead();
            mLocation_dao.openForRead();
            final Mineral mineral = mMineral_dao.getObjectById(idMineral);
            final int fk_location = mineral.getForeignKey_location();
            final int fk_chemical = mineral.getForeignKey_chemical();
            String fkLocation = String.valueOf(fk_location);
            String fkChemical = String.valueOf(fk_chemical);
            final Chemical chemical = mChemical_dao.getObjectById(fkChemical);
            final Location location = mLocation_dao.getObjectById(fkLocation);

            /*String chimique = String.valueOf(fk_chemical);
            Toast.makeText(this, chimique.toString(), Toast.LENGTH_SHORT).show();*/
            mMineral_dao.close();
            mChemical_dao.close();
            mLocation_dao.close();


            final String setId = " Description of mineral ID : " + idMineral;

            idView.setText(setId);
            nameEdit.setText(mineral.getMineral_name());
            minAssEdit.setText(mineral.getMineral_minAss());
            systCristEdit.setText(mineral.getMineral_systCrist());
            colorEdit.setText(mineral.getMineral_color());
            glowEdit.setText(mineral.getMineral_glow());
            aspectEdit.setText(mineral.getMineral_aspect());
            clivageEdit.setText(mineral.getMineral_clivage());
            locationEdit.setText(mineral.getMineral_location());

            String hardnessE = String.valueOf(mineral.getMineral_hardness());
            String densityE = String.valueOf(mineral.getMineral_density());
            String priceE = String.valueOf(mineral.getMineral_price());

            hardnessEdit.setText(hardnessE);
            densityEdit.setText(densityE);
            priceEdit.setText(priceE);

            chemicalFormulaEdit.setText(chemical.getChemical_formula());
            String chemicalClass = String.valueOf(chemical.getChemical_class());
            chemicalClassEdit.setText(chemicalClass);
            cityEdit.setText(location.getLocation_city());
            areaEdit.setText(location.getLocation_area());
            countryEdit.setText(location.getLocation_Country());


            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{

                    int id = Integer.parseInt(idMineral);
                    String name = nameEdit.getText().toString();
                    String minAss = minAssEdit.getText().toString();
                    String systCrist = systCristEdit.getText().toString();
                    String color = colorEdit.getText().toString();
                    String glow = glowEdit.getText().toString();
                    String aspect = aspectEdit.getText().toString();
                    String clivage = clivageEdit.getText().toString();
                    float hardness = Float.parseFloat(hardnessEdit.getText().toString());
                    float density = Float.parseFloat(densityEdit.getText().toString());
                    float price = Float.parseFloat(priceEdit.getText().toString());
                    String location = locationEdit.getText().toString();
                    int fkUser = Integer.parseInt(fk_user);

                    String hardNess = String.valueOf(hardness);
                    String denSity = String.valueOf(density);
                    String priCe = String.valueOf(price);

                    if(name.isEmpty()){
                        //error name is empty
                        Toast.makeText(EditMineralActivity.this, "You must enter a name", Toast.LENGTH_SHORT).show();
                    }

                    if(minAss.isEmpty()){

                        Toast.makeText(EditMineralActivity.this, "You must enter a minAss", Toast.LENGTH_SHORT).show();
                    }

                    if(systCrist.isEmpty()){

                        Toast.makeText(EditMineralActivity.this, "You must enter a systCrist", Toast.LENGTH_SHORT).show();
                    }

                    if(color.isEmpty()){

                        Toast.makeText(EditMineralActivity.this, "You must enter a color", Toast.LENGTH_SHORT).show();
                    }

                    if(glow.isEmpty()){

                        Toast.makeText(EditMineralActivity.this, "You must enter a glow", Toast.LENGTH_SHORT).show();
                    }

                    if(aspect.isEmpty()){

                        Toast.makeText(EditMineralActivity.this, "You must enter an aspect", Toast.LENGTH_SHORT).show();
                    }

                    if(clivage.isEmpty()){

                        Toast.makeText(EditMineralActivity.this, "You must enter a clivage", Toast.LENGTH_SHORT).show();
                    }

                    if(hardNess.isEmpty()){

                        Toast.makeText(EditMineralActivity.this, "You must enter an hardness", Toast.LENGTH_SHORT).show();
                    }

                    if(denSity.isEmpty()){

                    Toast.makeText(EditMineralActivity.this, "You must enter a density", Toast.LENGTH_SHORT).show();
                    }

                    if(priCe.isEmpty()){

                        Toast.makeText(EditMineralActivity.this, "You must enter a price", Toast.LENGTH_SHORT).show();
                    }

                    if(location.isEmpty()){

                        Toast.makeText(EditMineralActivity.this, "You must enter a location", Toast.LENGTH_SHORT).show();
                    }

                    Mineral updatedMineral = new Mineral();


                    updatedMineral.setMineral_id(id);
                    updatedMineral.setMineral_name(name);
                    updatedMineral.setMineral_minAss(minAss);
                    updatedMineral.setMineral_systCrist(systCrist);
                    updatedMineral.setMineral_color(color);
                    updatedMineral.setMineral_glow(glow);
                    updatedMineral.setMineral_aspect(aspect);
                    updatedMineral.setMineral_clivage(clivage);
                    updatedMineral.setMineral_hardness(hardness);
                    updatedMineral.setMineral_density(density);
                    updatedMineral.setMineral_price(price);
                    updatedMineral.setMineral_location(location);
                    updatedMineral.setForeignKey_user(fkUser);
                    updatedMineral.setForeignKey_location(fk_location);
                    updatedMineral.setForeignKey_chemical(fk_chemical);


                    mMineral_dao.openForWrite();
                    mMineral_dao.update(idMineral, updatedMineral);
                    mMineral_dao.close();

                        //Toast.makeText(EditMineralActivity.this, "Ca fonctionne", Toast.LENGTH_LONG).show();

                    Intent listMineralIntent = new Intent(EditMineralActivity.this, ListMineralsActivity.class);
                    listMineralIntent.putExtra("idUser", fk_user);
                    startActivity(listMineralIntent);
                    }

                    catch (Exception e){
                        Toast.makeText(EditMineralActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{
                        int id = Integer.parseInt(idMineral);

                        Toast.makeText(EditMineralActivity.this, idMineral.toString(), Toast.LENGTH_LONG).show();

                    mMineral_dao.openForWrite();
                    mLocation_dao.openForWrite();
                    mChemical_dao.openForWrite();

                    mMineral_dao.remove(id);
                    mLocation_dao.remove(fk_location);
                    mChemical_dao.remove(fk_chemical);

                    mMineral_dao.close();
                    mLocation_dao.close();
                    mChemical_dao.close();

                    Intent listMineralIntent = new Intent(EditMineralActivity.this, ListMineralsActivity.class);
                    listMineralIntent.putExtra("idUser", fk_user);
                    startActivity(listMineralIntent);
                    }
                    catch (Exception e){
                        Toast.makeText(EditMineralActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        catch (Exception e){
            Toast.makeText(EditMineralActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }


    }
}
