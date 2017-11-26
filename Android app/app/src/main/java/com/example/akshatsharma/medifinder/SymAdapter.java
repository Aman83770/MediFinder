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
 * Created by Dell on 03-12-2015.
 */
public class SymAdapter extends ArrayAdapter {
    List list=new ArrayList();
    public SymAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(SymCheck object) {
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
        SymHolder symHolder;
        View row;
        row=convertView;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.sym_check_layout,parent,false);
            symHolder=new SymHolder();
            symHolder.tx_disease=(TextView)row.findViewById(R.id.tx_disease);
            row.setTag(symHolder);
        }
        else{
            symHolder=(SymHolder)row.getTag();
        }
        SymCheck symCheck=(SymCheck)this.getItem(position);
        symHolder.tx_disease.setText(symCheck.getDisease());


        return row;
    }
    static class SymHolder{
       TextView tx_disease;
    }
}
