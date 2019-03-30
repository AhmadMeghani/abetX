package com.example.abetx.Entry;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abetx.Models.Transactions;
import com.example.abetx.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AddEntry extends Fragment {
    private FirebaseFirestore db;

    private Spinner debitSpinner, creditSpinner, debitSubSpinner, creditSubSpinner;
    private EditText amount;
    private Button addEntry, addHead;
    private String ID = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_add_entry, container, false);
        db = FirebaseFirestore.getInstance();
        debitSpinner = view.findViewById(R.id.debitHead);
        creditSpinner = view.findViewById(R.id.creditHead);
        debitSubSpinner = view.findViewById(R.id.debitSubHead);
        creditSubSpinner = view.findViewById(R.id.creditSubHead);
        amount = view.findViewById(R.id.amount);
        addHead = view.findViewById(R.id.addHead);
        addEntry = view.findViewById(R.id.addEntry);
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
        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTransactionId();
            }
        });
        return view;
    }

    private void addEntry() {
        Log.i("Tag1", "ID" + ID);
        if (!amount.getText().equals("")) {
            if (creditSubSpinner.getSelectedItem().toString() != "") {
                if (debitSubSpinner.getSelectedItem().toString() != "") {
                    Transactions transactions = new Transactions("1", debitSubSpinner.getSelectedItem().toString(),
                            creditSubSpinner.getSelectedItem().toString(),
                            Integer.parseInt(amount.getText().toString()),
                            Integer.parseInt(amount.getText().toString()));
                    CollectionReference colRef = db.collection("users")
                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .collection("Transactions");
                    colRef.document(ID + "").set(transactions);
                    colRef = db.collection("users")
                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .collection("Heads")
                            .document(creditSpinner.getSelectedItem().toString())
                            .collection(creditSubSpinner.getSelectedItem().toString());
                } else {
                    Toast.makeText(getActivity(), "Add Debit Sub Head", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getActivity(), "Add Credit Sub Head", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Add Amount!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getTransactionId() {
        ID = "";
        Task<QuerySnapshot> query = db.collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Transactions")
                .get();
        query.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.i("Tag", "I came here" + ID);
                if (task.isSuccessful()) {
                    Log.i("Tag", "here too");
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot.size() > 0) {
                        ID += "t" + (querySnapshot.size() + 1);
                        addEntry();
                        Log.i("Tag", "& here " + ID);
                    } else {
                        ID += "t1";
                        addEntry();
                    }
                } else {
                    Log.i("Tag", "& here too");
                    ID = "";
                }
            }
        });
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
