package com.example.abetx.Entry;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.abetx.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AddEntry extends Fragment {
    private FirebaseFirestore db;

    private Spinner debitSpinner;
    private Spinner creditSpinner;
    private Spinner debitSubSpinner;
    private Spinner creditSubSpinner;
    private Button addHead;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_add_entry, container, false);
        db = FirebaseFirestore.getInstance();
        debitSpinner = view.findViewById(R.id.debitHead);
        creditSpinner = view.findViewById(R.id.creditHead);
        debitSubSpinner = view.findViewById(R.id.debitSubHead);
        creditSubSpinner = view.findViewById(R.id.creditSubHead);
        addHead = view.findViewById(R.id.addHead);
        addHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entry.setmViewPager(1);
            }
        });
        setupSpinners();
        setupSubSpinners(debitSpinner.getSelectedItem().toString(), debitSubSpinner);
        setupSubSpinners(creditSpinner.getSelectedItem().toString(), creditSubSpinner);
        debitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setupSubSpinners(debitSpinner.getSelectedItem().toString(), debitSubSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        creditSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setupSubSpinners(creditSpinner.getSelectedItem().toString(), creditSubSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private void setupSubSpinners(String currentHead, final Spinner spinner) {
        DocumentReference docRef;
        docRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Heads").document(currentHead);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    HashMap<String, Object> map;
                    map = (HashMap) documentSnapshot.getData();
                    ArrayList<String> heads = new ArrayList<>();
                    if (map != null) {
                        Iterator myVeryOwnIterator = map.keySet().iterator();
                        while (myVeryOwnIterator.hasNext()) {
                            String key = (String) myVeryOwnIterator.next();
                            String value = (String) map.get(key);
                            heads.add(value);
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, heads);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
            }
        });
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
