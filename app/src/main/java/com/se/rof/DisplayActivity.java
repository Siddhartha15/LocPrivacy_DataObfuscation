package com.se.rof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static com.se.rof.util.obfucated_distance;
import static com.se.rof.util.obfuscated_locations;
import static com.se.rof.util.original_distance;
import static com.se.rof.util.original_locations;

public class DisplayActivity extends AppCompatActivity {

    MyListAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        adapter = new MyListAdapter2(this, original_locations, obfuscated_locations);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        //Toast.makeText(this, "The obfuscated distance: " + obfucated_distance, Toast.LENGTH_LONG ).show();
        TextView textView1= findViewById(R.id.original);
        TextView textView2= findViewById(R.id.obfuscated);


        textView1.setText("The original distance: " + original_distance);
        textView2.setText("The obfuscated distance: " + obfucated_distance);


    }
}
