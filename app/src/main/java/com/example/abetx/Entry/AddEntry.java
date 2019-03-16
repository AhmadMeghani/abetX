package com.example.abetx.Entry;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.abetx.R;

import java.util.ArrayList;

public class AddEntry extends AppCompatActivity {
    private Spinner debitSpinner;
    private Spinner creditSpinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_entry);
        debitSpinner = (Spinner) findViewById(R.id.debitHead);
        creditSpinner = (Spinner) findViewById(R.id.creditHead);
        setupSpinners();
    }

    private void setupSpinners() {
        ArrayList<String> heads = new ArrayList<>();
        heads.add("Assets");
        heads.add("Liabilities");
        heads.add("Owner Equity");
        heads.add("Expense");
        heads.add("Revenue");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, heads);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        debitSpinner.setAdapter(adapter);
        creditSpinner.setAdapter(adapter);
    }
}
