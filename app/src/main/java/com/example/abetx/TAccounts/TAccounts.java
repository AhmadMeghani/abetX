package com.example.abetx.TAccounts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abetx.Models.TAccount;
import com.example.abetx.R;
import com.example.abetx.Utilities.RecyclerAdapterTAccounts;

import java.util.ArrayList;

public class TAccounts extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_taccounts, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.taccounts_recycler);
        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(5);
        list.add(3);
        TAccount account[] = {new TAccount("Cash", list, list, 5, 6, 1, -1),
                new TAccount("Cash", list, list, 5, 6, 1, -1),
                new TAccount("Cash", list, list, 5, 6, 1, -1)};
        recyclerView.setAdapter(new RecyclerAdapterTAccounts(account, getActivity()));
    }
}
