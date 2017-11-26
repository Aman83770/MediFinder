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
public class docContactAdapter extends ArrayAdapter {

    List list=new ArrayList();

    public docContactAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(doc object) {
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
        DocHolder docHolder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.doc_row_layout,parent,false);
            docHolder=new DocHolder();
            docHolder.tx_name=(TextView)row.findViewById(R.id.tx_name);
            docHolder.tx_qualification=(TextView)row.findViewById(R.id.tx_qualification);
            docHolder.tx_specialization=(TextView)row.findViewById(R.id.tx_specialization);
            docHolder.tx_locality=(TextView)row.findViewById(R.id.tx_locality);
            docHolder.tx_city=(TextView)row.findViewById(R.id.tx_city);
            docHolder.tx_fees=(TextView)row.findViewById(R.id.tx_fees);
            docHolder.tx_timings=(TextView)row.findViewById(R.id.tx_timings);
            docHolder.tx_no_of_days=(TextView)row.findViewById(R.id.tx_no_of_days);
            docHolder.tx_email_id=(TextView)row.findViewById(R.id.tx_email_id);
            row.setTag(docHolder);
        }
        else {
            docHolder=(DocHolder)row.getTag();
        }
        doc ob=(doc)this.getItem(position);
        docHolder.tx_name.setText(ob.getName());
        docHolder.tx_qualification.setText(ob.getQualification());
        docHolder.tx_specialization.setText(ob.getSpecialization());
        docHolder.tx_locality.setText(ob.getLocality());
        docHolder.tx_city.setText(ob.getCity());
        docHolder.tx_fees.setText(ob.getFees());
        docHolder.tx_timings.setText(ob.getTimings());
        docHolder.tx_no_of_days.setText(ob.getNo_of_days());
        docHolder.tx_email_id.setText(ob.getEmail_id());

        return row;
    }

    public static class DocHolder{
        TextView tx_name,tx_qualification,tx_specialization,tx_locality,tx_city,tx_fees,tx_timings,tx_no_of_days,tx_email_id;

    }
}
