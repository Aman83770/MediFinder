package com.example.akshatsharma.medifinder;


import android.app.Activity;
import android.os.Bundle;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Disclaimer extends Fragment {


    public Disclaimer() {
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
        return inflater.inflate(R.layout.fragment_disclaimer, container, false);
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