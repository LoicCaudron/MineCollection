package com.example.einore.minecollection.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.einore.minecollection.model.Chemical_DAO;
import com.example.einore.minecollection.model.Location_DAO;
import com.example.einore.minecollection.model.Mineral;
import com.example.einore.minecollection.model.Mineral_DAO;
import com.example.einore.minecollection.R;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ListMineralsActivity extends AppCompatActivity {

    ListView listMinerals;
    SearchView sv;

    int id_test = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_minerals);

        Intent homeIntent = getIntent();
        final String fk_user = homeIntent.getStringExtra("idUser");
        int fk_userInt = Integer.parseInt(fk_user);

        final Location_DAO location_dao = new Location_DAO(ListMineralsActivity.this);
        final Chemical_DAO chemical_dao = new Chemical_DAO(ListMineralsActivity.this);

        listMinerals = (ListView)findViewById(R.id.mineralsList);
        sv = (SearchView) findViewById(R.id.searchView);


        Context context = getApplicationContext();
        final Mineral_DAO mineral_dao = new Mineral_DAO(context);
        mineral_dao.openForRead();
        final ArrayList<Mineral> minerals = mineral_dao.getAllObject(fk_userInt);
        mineral_dao.close();

        //Creating an array with id an name to display in the listView
        ArrayList<String> basicsMinerals = new ArrayList<>();
        for (int i = 0; i<minerals.size(); i++){
            String text = " ID : " + minerals.get(i).getMineral_id() + "    Name : " + minerals.get(i).getMineral_name()
                    + "    Color : " + minerals.get(i).getMineral_color();
            basicsMinerals.add(text);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, basicsMinerals);
        listMinerals.setAdapter(adapter);

        registerForContextMenu(listMinerals);

        //function to research
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });


        //function to retrieve the id of a selected element in the listview
        listMinerals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Mineral mineralToDetail = minerals.get(position);
                int id_mineral = mineralToDetail.getMineral_id();
                String id_mineralString = Integer.toString(id_mineral);*/

                String item = (String) parent.getItemAtPosition(position);
                String dish = item.split(": ")[0];
                String id_mineralString0 = item.split(": ")[1];
                String id_mineralString = id_mineralString0.split("    N")[0];

                Intent mineralDetailIntent = new Intent(ListMineralsActivity.this, MineralDetailsActivity.class);
                mineralDetailIntent.putExtra("idMineral", id_mineralString);
                mineralDetailIntent.putExtra("idUser", fk_user);
                startActivity(mineralDetailIntent);


            }
        });

        listMinerals.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String item = (String) parent.getItemAtPosition(position);
                String dish = item.split(": ")[0];
                String id_mineralString0 = item.split(": ")[1];
                String id_mineralString = id_mineralString0.split("    N")[0];


                //char id_mineral = item.charAt(6);  //récupération du 6ième char du string
                //String id_mineralString = Character.toString(id_mineral); //conversion du char en string

                Toast.makeText(ListMineralsActivity.this, id_mineralString.toString(),Toast.LENGTH_LONG).show();


                return true;
            }

        });

        /*listMinerals.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                menu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        //What should happen on selecting context menu item 1
                        //Toast.makeText(ListMineralsActivity.this, "You did it",Toast.LENGTH_LONG).show();

                        if(id_test == -1){
                            Toast.makeText(ListMineralsActivity.this, "Ca pue du cul !",Toast.LENGTH_LONG).show();
                        }
                        else {
                            mineral_dao.remove(id_test);
                            location_dao.remove(id_test);
                            chemical_dao.remove(id_test);
                        }
                        return true;
                    }
                });

                menu.add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        //What should happent on selecting context menu item 1
                        //Toast.makeText(ListMineralsActivity.this, "You did it",Toast.LENGTH_LONG).show();

                        if(id_test == -1){
                            Toast.makeText(ListMineralsActivity.this, "Ca pue du cul !",Toast.LENGTH_LONG).show();
                        }
                        else {
                            mineral_dao.remove(id_test);
                            location_dao.remove(id_test);
                            chemical_dao.remove(id_test);
                        }
                        return true;
                    }
                });
            }
        });*/


    }

    //en dehors du onCreate

    /*@Override   //fonction permettant de créer le menu contextuel
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add("Modifier");
        menu.add("Supprimer");
    }

    @Override //fonction permettant de faire différentes actions suivant la sélection
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        Mineral_DAO mineral_dao = new Mineral_DAO(ListMineralsActivity.this);
        Location_DAO location_dao = new Location_DAO(ListMineralsActivity.this);
        Chemical_DAO chemical_dao = new Chemical_DAO(ListMineralsActivity.this);

        if(item.getTitle() == "Modifier"){

            Toast.makeText(ListMineralsActivity.this, "Modifier réussi",Toast.LENGTH_LONG).show();
        }
        if(item.getTitle() == "Supprimer"){

            //Toast.makeText(ListMineralsActivity.this, "Supprimer réussi",Toast.LENGTH_LONG).show();
            //remove();
            if(id_test == -1){
                Toast.makeText(ListMineralsActivity.this, "Ca pue du cul !",Toast.LENGTH_LONG).show();
            }
            else {
                mineral_dao.remove(id_test);
                location_dao.remove(id_test);
                chemical_dao.remove(id_test);
            }

        }
        return true;
    }*/


    /*public void remove() {

        try {

        }
        catch(Exception e){
            Toast.makeText(ListMineralsActivity.this, e.toString(),Toast.LENGTH_LONG).show();
        }
    }*/
}
