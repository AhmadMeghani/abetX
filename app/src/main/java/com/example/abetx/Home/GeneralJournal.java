package com.example.abetx.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.abetx.Models.Transactions;
import com.example.abetx.R;
import com.example.abetx.Utilities.RecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GeneralJournal extends Fragment {
    private FirebaseFirestore db;

    private RecyclerView recyclerView;
    private ArrayList<Transactions> transactions = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_general_journal, container, false);
        db = FirebaseFirestore.getInstance();
        recyclerView = (RecyclerView) view.findViewById(R.id.gj_recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        final RecyclerAdapter adapter = new RecyclerAdapter(transactions);
        recyclerView.setAdapter(adapter);
        CollectionReference colRef = db.collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Transactions");
        colRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<Transactions> transaction = queryDocumentSnapshots.toObjects(Transactions.class);
                    clear(adapter);
                    transactions.addAll(transaction);
                    adapter.notifyDataSetChanged();
                    Log.i("Tran", transactions.toString());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void clear(RecyclerAdapter adapter) {
        final int size = transactions.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                transactions.remove(0);
            }
            adapter.notifyItemRangeRemoved(0, size);
        }
    }
}
