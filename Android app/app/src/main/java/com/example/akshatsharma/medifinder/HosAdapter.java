package com.example.akshatsharma.medifinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 02-12-2015.
 */
public class HosAdapter extends ArrayAdapter {
    List list=new ArrayList();
    public HosAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Hos object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row=convertView;
        HosHolder hosHolder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.hos_row_layout,parent,false);
            hosHolder=new HosHolder();
            hosHolder.tx_name=(TextView)row.findViewById(R.id.tx_name);
            hosHolder.tx_city=(TextView)row.findViewById(R.id.tx_city);
            hosHolder.tx_address=(TextView)row.findViewById(R.id.tx_address);
            hosHolder.tx_contact=(TextView)row.findViewById(R.id.tx_contact);
            row.setTag(hosHolder);
        }
        else{
            hosHolder=(HosHolder)row.getTag();
        }
        Hos hos=(Hos)this.getItem(position);
        hosHolder.tx_name.setText(hos.getName());
        hosHolder.tx_city.setText(hos.getCity());
        hosHolder.tx_address.setText(hos.getAddress());
        hosHolder.tx_contact.setText(hos.getContact());
        return row;

    }
    static  class HosHolder{
        TextView tx_name,tx_city,tx_address,tx_contact;
    }
}
