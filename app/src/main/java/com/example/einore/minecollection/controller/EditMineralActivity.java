package com.example.einore.minecollection.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.einore.minecollection.R;
import com.example.einore.minecollection.model.Mineral;
import com.example.einore.minecollection.model.Mineral_DAO;

public class EditMineralActivity extends AppCompatActivity {

    private Mineral_DAO mMineral_dao;

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

        EditText idEdit = (EditText)findViewById(R.id.editTextId);
        EditText nameEdit = (EditText) findViewById(R.id.editTextName);
        EditText systCristEdit = (EditText) findViewById(R.id.editTextSystCrist);
        EditText colorEdit = (EditText) findViewById(R.id.editTextColor);
        EditText glowEdit = (EditText) findViewById(R.id.editTextGlow);
        EditText aspectEdit = (EditText) findViewById(R.id.editTextAspect);
        EditText clivageEdit = (EditText) findViewById(R.id.editTextClivage);
        EditText hardnessEdit = (EditText) findViewById(R.id.editTextHardness);
        EditText densityEdit = (EditText) findViewById(R.id.editTextDensity);
        EditText priceEdit = (EditText) findViewById(R.id.editTextPrice);

        Intent listIntent = getIntent();
        final String idMineral = listIntent.getStringExtra("idMineral");

        mMineral_dao = new Mineral_DAO(EditMineralActivity.this);

        mMineral_dao.openForWrite();
        final Mineral mineral = mMineral_dao.getObjectById(idMineral);
        mMineral_dao.close();

        idEdit.setText(mineral.getMineral_id());
        nameEdit.setText(mineral.getMineral_name());
        systCristEdit.setText(mineral.getMineral_name());
        colorEdit.setText(mineral.getMineral_name());
        glowEdit.setText(mineral.getMineral_name());
        aspectEdit.setText(mineral.getMineral_name());
        clivageEdit.setText(mineral.getMineral_name());
        hardnessEdit.setText(mineral.getMineral_name());
        densityEdit.setText(mineral.getMineral_name());
        priceEdit.setText(mineral.getMineral_name());
    }
}
