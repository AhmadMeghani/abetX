package com.example.abetx.Entry;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.abetx.R;

import java.util.ArrayList;

public class AddEntry extends Fragment {
    private Spinner debitSpinner;
    private Spinner creditSpinner;
    private Button addHead;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_add_entry, container, false);
        debitSpinner = (Spinner) view.findViewById(R.id.debitHead);
        creditSpinner = (Spinner) view.findViewById(R.id.creditHead);
        addHead = (Button) view.findViewById(R.id.addHead);
        addHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entry.setmViewPager(1);
            }
        });
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
        debitSpinner.setAdapter(adapter);
        creditSpinner.setAdapter(adapter);
    }
}
