package com.se.rof;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<Location> {

    private final Context context;
    private ArrayList<Location> orig;

    private static class ViewHolder {
        TextView lat;
        TextView lon;

    }


    public MyListAdapter(Context context, ArrayList<Location> orig) {
        super(context, R.layout.layout_listview, orig);
        this.orig = orig;
        this.context=context;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.layout_listview, parent, false);
            viewHolder.lat = (TextView) view.findViewById(R.id.lat);
            viewHolder.lon = (TextView) view.findViewById(R.id.lon);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.lat.setText( "lat: " + (float)orig.get(position).lat + "");
        viewHolder.lon.setText("lon: "+ (float)orig.get(position).lon + "");
        return view;

    }
}
