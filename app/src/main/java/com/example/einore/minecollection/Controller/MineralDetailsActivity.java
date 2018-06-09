package com.example.einore.minecollection.Controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.einore.minecollection.Model.Mineral;
import com.example.einore.minecollection.Model.Mineral_DAO;
import com.example.einore.minecollection.R;

import java.util.ArrayList;

public class MineralDetailsActivity extends AppCompatActivity {


    String num;
    String to;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mineral_details);
        setTitle("Mineral details");

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

        final EditText phone_num = (EditText) findViewById(R.id.editTextPhoneNumber);
        Button sendButton = (Button) findViewById(R.id.sendButton);
        final Button sendEmailButton = (Button) findViewById(R.id.sendEmailButton);



        Intent listIntent = getIntent();
        final String idMineral = listIntent.getStringExtra("idMineral");

        int id = Integer.parseInt(idMineral);

        Context context = getApplicationContext();
        Mineral_DAO mineral_dao = new Mineral_DAO(context);
        mineral_dao.openForWrite();
        final Mineral mineral = mineral_dao.getId(idMineral);
        mineral_dao.close();

        final String setId = " Description of mineral ID : " + idMineral;
        final String setName = "\n Name : " + mineral.getMineral_name();
        final String setSystCrist = "\n Syst Crist : " + mineral.getMineral_systCrist();
        final String setColor = "\n Color : " + mineral.getMineral_color();
        final String setGlow = "\n Glow : " + mineral.getMineral_glow();
        final String setAspect = "\n Aspect : " + mineral.getMineral_aspect();
        final String setClivage = "\n Clivage : " + mineral.getMineral_clivage();
        final String setHardness = "\n Hardness : " + mineral.getMineral_hardness();
        final String setDensity = "\n Density : " + mineral.getMineral_density();
        final String setPrice = "\n Price : " + mineral.getMineral_price();

        idView.setText(setId);
        nameView.setText(setName);
        systCristView.setText(setSystCrist);
        colorView.setText(setColor);
        glowView.setText(setGlow);
        aspectView.setText(setAspect);
        clivageView.setText(setClivage);
        hardnessView.setText(setHardness);
        densityView.setText(setDensity);
        priceView.setText(setPrice);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    num = phone_num.getText().toString();
                    String message = "Informations mineral ID : " + idMineral
                            + setName
                            + setSystCrist
                            + setColor
                            + setGlow
                            + setAspect
                            + setClivage
                            + setHardness
                            + setDensity
                            + setPrice;


                    // Allow to send messages longer than 160 characters ( max length previous method )
                    SmsManager smsManager = SmsManager.getDefault();
                    ArrayList<String> parts = smsManager.divideMessage(message);
                    smsManager.sendMultipartTextMessage(num, null, parts,
                            null, null);
                    //sms(message);
                    Intent listMineralsIntent = new Intent(MineralDetailsActivity.this, ListMineralsActivity.class);
                    listMineralsIntent.putExtra("idUser", mineral.getForeignKey_user());
                    startActivity(listMineralsIntent);
                }
                catch(Exception e){
                    Toast.makeText(MineralDetailsActivity.this, e.toString(),Toast.LENGTH_LONG).show();
                }
            }

            private void sms(String message) {
                if(num.length()>= 4){
                    SmsManager.getDefault().sendTextMessage(num, null, message, null, null );
                }
            }
        });

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    to = sendEmailButton.getText().toString();
                    String message = "Informations mineral ID : " + idMineral
                            + setName
                            + setSystCrist
                            + setColor
                            + setGlow
                            + setAspect
                            + setClivage
                            + setHardness
                            + setDensity
                            + setPrice;

                    //intent to send email
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setData(Uri.parse("mailto:"));
                    //String[] to={"lyrcaudron@gmail.com"};
                    intent.putExtra(Intent.EXTRA_EMAIL, to);
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Test d'envoi d'email par une application mobile");
                    intent.putExtra(Intent.EXTRA_TEXT, message);
                    intent.setType("message/rfc822");
                    startActivity(Intent.createChooser(intent, "Send Email"));

                    //intent to return to the ListMineralActivity
                    Intent listMineralsIntent = new Intent(MineralDetailsActivity.this, ListMineralsActivity.class);
                    listMineralsIntent.putExtra("idUser", mineral.getForeignKey_user());
                    startActivity(listMineralsIntent);
                }
                catch(Exception e){
                    Toast.makeText(MineralDetailsActivity.this, e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(MineralDetailsActivity.this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MineralDetailsActivity.this,
                    Manifest.permission.SEND_SMS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(MineralDetailsActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }


    }
}
