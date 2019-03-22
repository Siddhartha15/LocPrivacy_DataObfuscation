package com.se.rof;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;


import static com.se.rof.util.obfuscated_locations;
import static com.se.rof.util.original_distance;
import static com.se.rof.util.obfucated_distance;
import static com.se.rof.util.original_locations;
import com.se.rof.Location;

public class EROF extends AppCompatActivity {

    int size;
    MyListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erof_mrof);

        util.obfuscated_locations.clear();
        util.original_locations.clear();
        adapter = new MyListAdapter(this, original_locations);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);


    }

    public void add(View view) {
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_loyout);
        dialog.setTitle("Enter the Location...");

        // set the custom dialog components - text, image and button
        final EditText text =  dialog.findViewById(R.id.pt1);
        final EditText text2 =  dialog.findViewById(R.id.pt2);

        Button cancelButton = (Button) dialog.findViewById(R.id.cancel);
        // if button is clicked, close the custom dialog
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button okButton = (Button) dialog.findViewById(R.id.ok);
        // if button is clicked, close the custom dialog
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                original_locations.add(new Location(Double.parseDouble(text.getText().toString()),Double.parseDouble(text2.getText().toString()) ));
                size++;
                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });

        dialog.show();
    }

    public void submit(View view) {

//        original_locations.add(new Location(Double.parseDouble(tx1.getText().toString()), Double.parseDouble(tx2.getText().toString())));
//        original_locations.add(new Location(Double.parseDouble(tx3.getText().toString()), Double.parseDouble(tx4.getText().toString())));


        int ang = 60;
        double[][] orthogonal = new double[2][2];
        orthogonal[0][0] = cos(ang);
        orthogonal[0][1] = sin(ang);
        orthogonal[1][0] = -sin(ang);
        orthogonal[1][1] = cos(ang);

        int i = 0;
        while( i < size - 1){
            original_distance += original_locations.get(i).getDistance(original_locations.get(i+1)); ////to find the distance of two cartesian points
            // original_distance += distance(original_locations[i].lat, original_locations[i].lon, original_locations[i+1].lat, original_locations[i+1].lon);
            i++;
        }
//        Toast.makeText(this, "The original_distance: " + original_distance, Toast.LENGTH_LONG ).show();

        //ArrayList<Location> obfuscated_locations = new ArrayList<>();
        for (i = 0; i < size; ++i)
        {
            Location location = new Location();
            location.lat  = original_locations.get(i).lat * orthogonal[0][0] + original_locations.get(i).lon * orthogonal[1][0];
            location.lon  =original_locations.get(i).lat * orthogonal[0][1] + original_locations.get(i).lon * orthogonal[1][1];
            obfuscated_locations.add(location);
        }

        //output
//        for ( i = 0; i < n_points; ++i)
//        {
//            printf("The obfuscated points are: %f and %f \n", obfuscated_locations[i].lat,obfuscated_locations[i].lon );
//        }

        i = 0;
        while( i < size-1){
            obfucated_distance += obfuscated_locations.get(i).getDistance(obfuscated_locations.get(i+1)); //to find the distance of two cartesian points
            //obfucated_distance += distance(obfuscated_locations[i].lat, obfuscated_locations[i].lon, obfuscated_locations[i+1].lat, obfuscated_locations[i+1].lon);
            i++;
        }

        //Toast.makeText(this, "The obfuscated distance: " + obfucated_distance, Toast.LENGTH_LONG ).show();

        startActivity(new Intent(this, DisplayActivity.class));

    }


    double toDegrees(double rad){
        return rad*180/PI;
    }


    double toRadians(double deg){
        return deg*PI/180;
    }


}
