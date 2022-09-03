package com.ppe.ppedeveloper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AnalyticsFragment extends Fragment {
    View parent;


    public AnalyticsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parent=  inflater.inflate(R.layout.fragment_analytics, container, false);

        return parent;
    }
}