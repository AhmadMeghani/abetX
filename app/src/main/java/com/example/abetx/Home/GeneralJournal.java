package com.example.abetx.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abetx.Models.Transactions;
import com.example.abetx.R;
import com.example.abetx.Utilities.RecyclerAdapter;

public class GeneralJournal extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_general_journal, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.gj_recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        Transactions[] stg = {new Transactions("27/01/01", "Cash", "Unearned Revenue", 10000, 20),
                new Transactions("28/01/20", "Land", "N/P", 200, 20000),
                new Transactions("27/02/22", "Cash", "Electric Expense", 1000, 1000),
                new Transactions("27/03/20", "Service Revenue", "A/P", 10, 1000),
                new Transactions("27/04/11", "R/V", "A/P", 1000, 1000),
                new Transactions("27/05/12", "OC", "A/P", 1000, 1000),
                new Transactions("27/06/12", "Unearned Revenue", "Electric Expense", 1000, 1000),
                new Transactions("01/07/12", "Cash", "A/P", 1000, 1000),
                new Transactions("27/08/12", "Cash", "A/P", 1000, 1000),
                new Transactions("17/09/12", "Electric Expense", "A/P", 1000, 1000),
                new Transactions("27/10/12", "Cash", "A/P", 1000, 1000),
                new Transactions("37/11/12", "Cash", "A/P", 1000, 1000),
                new Transactions("27/12/12", "Cash", "A/P", 1000, 1000),
                new Transactions("07/12/12", "Cash", "A/P", 1000, 1000)};
        recyclerView.setAdapter(new RecyclerAdapter(stg));
        return view;
    }
}
