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
 * Created by Dell on 25-11-2015.
 */
public class MedAdapter extends ArrayAdapter {
    List list=new ArrayList();
    public MedAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Object object) {
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
        MedHolder medHolder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.med_row_layout,parent,false);
            medHolder=new MedHolder();
            medHolder.tx_name=(TextView)row.findViewById(R.id.tx_name);
            medHolder.tx_telno=(TextView)row.findViewById(R.id.tx_telno);
            medHolder.tx_street=(TextView)row.findViewById(R.id.tx_street);
            medHolder.tx_locality=(TextView)row.findViewById(R.id.tx_locality);
            medHolder.tx_region=(TextView)row.findViewById(R.id.tx_region);
            medHolder.tx_postalcode=(TextView)row.findViewById(R.id.tx_postalcode);
            row.setTag(medHolder);
        }
        else{
            medHolder=(MedHolder)row.getTag();

        }
        med m=(med)this.getItem(position);
        medHolder.tx_name.setText(m.getName());
        medHolder.tx_telno.setText(m.getTelno());
        medHolder.tx_street.setText(m.getStreet());
        medHolder.tx_locality.setText(m.getLocality());
        medHolder.tx_region.setText(m.getRegion());
        medHolder.tx_postalcode.setText(m.getPostalcode());

        return row;
    }

    public static class MedHolder{
        TextView tx_name,tx_telno,tx_street,tx_locality,tx_region,tx_postalcode;
    }
}
