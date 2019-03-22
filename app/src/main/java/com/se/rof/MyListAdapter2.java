package com.se.rof;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import static com.se.rof.util.obfuscated_locations;
import static com.se.rof.util.original_distance;
import static com.se.rof.util.obfucated_distance;
import static com.se.rof.util.original_locations;

public class MyListAdapter2 extends ArrayAdapter<Location> {

    private final Context context;
    private ArrayList<Location> orig;
    private ArrayList<Location> obfu;

    private static class ViewHolder {
        TextView lat1;
        TextView lon1;
        TextView lat2;
        TextView lon2;

    }


    public MyListAdapter2(Context context, ArrayList<Location> orig,ArrayList<Location> obfu) {
        super(context, R.layout.display_listview_layout, orig);
        this.orig = orig;
        this.obfu = obfu;
        this.context=context;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.display_listview_layout, parent, false);
            viewHolder.lat1 = (TextView) view.findViewById(R.id.lat1);
            viewHolder.lon1 = (TextView) view.findViewById(R.id.lon1);
            viewHolder.lat2 = (TextView) view.findViewById(R.id.lat2);
            viewHolder.lon2 = (TextView) view.findViewById(R.id.lon2);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.lat1.setText( "ori lat: " + orig.get(position).lat);
        viewHolder.lon1.setText("ori lon: "+ orig.get(position).lon );
        viewHolder.lat2.setText( "obu lat: " + (float)obfu.get(position).lat );
        viewHolder.lon2.setText("obu lon: "+ (float)obfu.get(position).lon );
        return view;

    }
}
