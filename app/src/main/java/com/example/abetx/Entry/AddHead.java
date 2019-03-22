package com.example.abetx.Entry;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.abetx.R;

import java.util.ArrayList;

public class AddHead extends Fragment {
    private Spinner entitySpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_add_head, container, false);
        entitySpinner = (Spinner) view.findViewById(R.id.entitySpinner);
        setupSpinners();
        return view;
    }

    private void setupSpinners() {
        ArrayList<String> heads = new ArrayList<>();
        heads.add("Assets");
        heads.add("Liabilities");
        heads.add("Owner Equity");
        heads.add("Expense");
        heads.add("Revenue");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, heads);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        entitySpinner.setAdapter(adapter);
    }
}
