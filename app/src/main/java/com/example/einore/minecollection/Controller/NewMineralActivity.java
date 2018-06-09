package com.example.einore.minecollection.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewMineralActivity extends AppCompatActivity {

    EditText et_name;
    EditText et_synonym;
    EditText et_minAss;
    EditText et_systCrist;
    EditText et_color;
    EditText et_glow;
    EditText et_aspect;
    EditText et_clivage;
    EditText et_hardness;
    EditText et_density;
    EditText et_price;
    EditText et_stockageLocation;
    EditText et_chemicalFormula;
    EditText et_chemicalClass;
    EditText et_locationCity;
    EditText et_locationArea;
    EditText et_locationCountry;
    Button btn_addMineral;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mineral);

        //Getting the idUser in the Intent to use it as the fk_user
        Intent intent = getIntent();
        final String idUser = intent.getStringExtra("idUser");

        et_name = (EditText)findViewById(R.id.editTextName);
        et_synonym = (EditText)findViewById(R.id.editTextSynonym);
        et_minAss = (EditText)findViewById(R.id.editTextMinAss);
        et_systCrist = (EditText)findViewById(R.id.editTextSystCrist);
        et_color = (EditText)findViewById(R.id.editTextColor);
        et_glow = (EditText)findViewById(R.id.editTextGlow);
        et_aspect = (EditText)findViewById(R.id.editTextAspect);
        et_clivage = (EditText)findViewById(R.id.editTextClivage);
        et_hardness = (EditText)findViewById(R.id.editTextHardness);
        et_density = (EditText)findViewById(R.id.editTextDensity);
        et_price = (EditText)findViewById(R.id.editTextPrice);
        et_stockageLocation = (EditText)findViewById(R.id.editTextLocation);
        et_chemicalFormula = (EditText)findViewById(R.id.editTextChemicalFormula);
        et_chemicalClass = (EditText)findViewById(R.id.editTextChemicalClass);
        et_locationCity = (EditText)findViewById(R.id.editTextCity);
        et_locationArea = (EditText)findViewById(R.id.editTextArea);
        et_locationCountry = (EditText)findViewById(R.id.editTextCountry);
        btn_addMineral = (Button)findViewById(R.id.buttonAdd);

        btn_addMineral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = getApplicationContext();

                //Creating and inserting the Chemical object
                String chemicalFormula = et_chemicalFormula.getText().toString();
                int chemicalClass = Integer.parseInt(et_chemicalClass.getText().toString());
                Chemical chemical = new Chemical();
                chemical.setChemical_formula(chemicalFormula);
                chemical.setChemical_class(chemicalClass);

                Chemical_DAO chemical_dao = new Chemical_DAO(context);
                chemical_dao.openForWrite();
                chemical_dao.insert(chemical);
                // not closed, used after

                //Creating and inserting the Location object
                String locationCity = et_locationCity.getText().toString();
                String locationArea = et_locationArea.getText().toString();
                String locationCountry = et_locationCountry.getText().toString();
                Location location = new Location();
                location.setLocation_city(locationCity);
                location.setLocation_area(locationArea);
                location.setLocation_Country(locationCountry);

                Location_DAO location_dao = new Location_DAO(context);
                location_dao.openForWrite();
                location_dao.insert(location);
                //not closed, used after

                //Creating and inserting the Mineral object
                String name = et_name.getText().toString();
                String synonym = et_synonym.getText().toString();
                String minAss = et_minAss.getText().toString();
                String systCrist = et_systCrist.getText().toString();
                String color = et_color.getText().toString();
                String glow = et_glow.getText().toString();
                String aspect = et_aspect.getText().toString();
                String clivage = et_clivage.getText().toString();
                float hardness = Float.parseFloat(et_hardness.getText().toString());
                float density = Float.parseFloat(et_density.getText().toString());
                float price = Float.parseFloat(et_price.getText().toString());
                String stockageLocation = et_stockageLocation.getText().toString();


                //Getting the ids of the Chemical and Location objects we just inserted
                // in order to use it for the foreign keys when inserting the mineral
                Location fk_location = location_dao.getObject(locationCity);
                int id_fk_location = fk_location.getLocation_id();
                location_dao.close();


                Chemical fk_chemical = chemical_dao.getObject(chemicalFormula);
                int id_fk_chemical = fk_chemical.getChemical_id();
                chemical_dao.close();

                //Setting mineral attributes
                Mineral mineral = new Mineral();
                mineral.setMineral_name(name);
                mineral.setMineral_synonyme(synonym);
                mineral.setMineral_minAss(minAss);
                mineral.setMineral_systCrist(systCrist);
                mineral.setMineral_color(color);
                mineral.setMineral_glow(glow);
                mineral.setMineral_aspect(aspect);
                mineral.setMineral_clivage(clivage);
                mineral.setMineral_hardness(hardness);
                mineral.setMineral_density(density);
                mineral.setMineral_price(price);
                mineral.setMineral_location(stockageLocation);
                mineral.setForeignKey_user(Integer.parseInt(idUser));
                mineral.setForeignKey_location(id_fk_location);
                mineral.setForeignKey_chemical(id_fk_chemical);

                Mineral_DAO mineral_dao = new Mineral_DAO(context);
                mineral_dao.openForWrite();
                mineral_dao.insert(mineral);
                mineral_dao.close();

                Intent homeIntent = new Intent(NewMineralActivity.this, HomeActivity.class);
                homeIntent.putExtra("idUser", idUser);
                startActivity(homeIntent);


            }
        });
    }
}
