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
import android.widget.EditText;
import android.widget.Spinner;

import com.example.abetx.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class AddHead extends Fragment {
    private FirebaseFirestore db;

    private Spinner entitySpinner;
    private Button addHead;
    private EditText headName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_add_head, container, false);
        db = FirebaseFirestore.getInstance();
        entitySpinner = view.findViewById(R.id.entitySpinner);
        addHead = view.findViewById(R.id.addHead);
        headName = view.findViewById(R.id.headName);
        addHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HashMap<String, Object> map = new HashMap<>();
                DocumentReference docRef = db.collection("users")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Heads")
                        .document(entitySpinner.getSelectedItem().toString());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                map.put(headName.getText().toString(), headName.getText().toString());
                                CollectionReference collectionReference = db.collection("users")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Heads");
                                collectionReference.document(entitySpinner.getSelectedItem().toString()).update(map);
                            } else {
                                map.put(headName.getText().toString(), headName.getText().toString());
                                CollectionReference collectionReference = db.collection("users")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Heads");
                                collectionReference.document(entitySpinner.getSelectedItem().toString()).set(map);
                            }
                        }
                    }
                });
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
        entitySpinner.setAdapter(adapter);
    }
}
