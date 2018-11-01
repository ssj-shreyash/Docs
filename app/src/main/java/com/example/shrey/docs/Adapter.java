package com.example.shrey.docs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    Context c;
    ArrayList<AddCenter> addCenters ;
    LayoutInflater inflater;

    public Adapter(Context c, ArrayList<AddCenter> addCenters, LayoutInflater inflater) {
        this.c = c;
        this.addCenters = addCenters;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        addCenters.size();
        return getCount();
    }

    @Override
    public Object getItem(int position) {
        addCenters.get(position);
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater ==null){
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }if (convertView == null){
            convertView = inflater.inflate(R.layout.support_simple_spinner_dropdown_item,parent,false);
        }


        return convertView;
    }
}
