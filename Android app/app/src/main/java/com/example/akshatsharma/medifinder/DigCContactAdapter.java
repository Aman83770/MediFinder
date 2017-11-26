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
 * Created by Dell on 24-11-2015.
 */
public class DigCContactAdapter extends ArrayAdapter {

    List list=new ArrayList();

    public DigCContactAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(digC object) {
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
        ListViewHolder listViewHolder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.dig_row_layout,parent,false);
            listViewHolder=new ListViewHolder();
            listViewHolder.tx_name=(TextView)row.findViewById(R.id.tx_name);
            listViewHolder.tx_street=(TextView)row.findViewById(R.id.tx_street);
            listViewHolder.tx_locality=(TextView)row.findViewById(R.id.tx_locality);
            listViewHolder.tx_region=(TextView)row.findViewById(R.id.tx_region);
            listViewHolder.tx_postalcode=(TextView)row.findViewById(R.id.tx_postalcode);
            listViewHolder.tx_timings=(TextView)row.findViewById(R.id.tx_timings);
            listViewHolder.tx_telno=(TextView)row.findViewById(R.id.tx_telno);
            listViewHolder.tx_emailid=(TextView)row.findViewById(R.id.tx_emailid);
            listViewHolder.tx_website=(TextView)row.findViewById(R.id.tx_website);
            row.setTag(listViewHolder);
        }
        else {
            listViewHolder=(ListViewHolder)row.getTag();
        }
        digC ob=(digC)this.getItem(position);
        listViewHolder.tx_name.setText(ob.getName());
        listViewHolder.tx_street.setText(ob.getStreet());
        listViewHolder.tx_locality.setText(ob.getLocality());
        listViewHolder.tx_region.setText(ob.getRegion());
        listViewHolder.tx_postalcode.setText(ob.getPostalcode());
        listViewHolder.tx_timings.setText(ob.getTimings());
        listViewHolder.tx_telno.setText(ob.getTelno());
        listViewHolder.tx_emailid.setText(ob.getEmailid());
        listViewHolder.tx_website.setText(ob.getWebsite());

        return row;
    }

    static class ListViewHolder{
        TextView tx_name,tx_street,tx_locality,tx_region,tx_postalcode,tx_timings,tx_telno,tx_emailid,tx_website;

    }

}
