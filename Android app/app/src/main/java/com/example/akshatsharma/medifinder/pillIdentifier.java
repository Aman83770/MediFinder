package com.example.akshatsharma.medifinder;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class pillIdentifier extends Fragment implements AdapterView.OnItemSelectedListener{

    Spinner shapeSpinner,colorSpinner;
    View view;

    Button displayButton;
    public pillIdentifier() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pill_identifier, container, false);
        displayButton=(Button)view.findViewById(R.id.displayButton);
        shapeSpinner=(Spinner)view.findViewById(R.id.shapeSpinner);
        colorSpinner=(Spinner)view.findViewById(R.id.colorSpinner);

        ArrayAdapter shapeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.Shape, android.R.layout.simple_spinner_item);
        ArrayAdapter colorAdapter = ArrayAdapter.createFromResource(getContext(),R.array.medColor,android.R.layout.simple_spinner_item);
        shapeSpinner.setAdapter(shapeAdapter);
        colorSpinner.setAdapter(colorAdapter);

        shapeSpinner.setOnItemSelectedListener(this);
        colorSpinner.setOnItemSelectedListener(this);


        return view;
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        TextView myText=(TextView)view;
        //Toast.makeText(getContext(),myText.getText()+" is selected",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
