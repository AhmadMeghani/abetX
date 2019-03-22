package com.example.abetx.Utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.abetx.Models.TAccount;
import com.example.abetx.R;

public class RecyclerAdapterTAccounts extends RecyclerView.Adapter<RecyclerAdapterTAccounts.RecyclerHolder> {
    private TAccount[] groups;
    private Context context;

    public RecyclerAdapterTAccounts(TAccount[] data, Context context) {
        this.groups = data;
        this.context = context;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_recyclerview_taccounts, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        TAccount account = groups[position];
        holder.name.setText(account.getAccountName());
        ListAdapter adapter = new ArrayAdapter<>(context, R.layout.layout_center, account.debitValues);
        holder.debitValues.setAdapter(adapter);
        ListAdapter adapter1 = new ArrayAdapter<>(context, R.layout.layout_center, account.creditValues);
        holder.creditValues.setAdapter(adapter1);
        holder.totalDebit.setText(account.getDebitTotal() + "");
        holder.totalCredit.setText(account.getCreditTotal() + "");
        if (account.getDebitLeft() != -1) {
            holder.debitLeft.setText(account.getDebitLeft() + "");
        } else {
            holder.debitLeft.setVisibility(View.INVISIBLE);
        }
        if (account.getCreditLeft() != -1) {
            holder.creditLeft.setText(account.getCreditLeft() + "");
        } else {
            holder.creditLeft.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return this.groups.length;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView name;
        ListView debitValues;
        ListView creditValues;
        TextView totalDebit;
        TextView totalCredit;
        TextView debitLeft;
        TextView creditLeft;

        public RecyclerHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.account_name);
            debitValues = itemView.findViewById(R.id.debit_list);
            creditValues = itemView.findViewById(R.id.credit_list);
            totalDebit = itemView.findViewById(R.id.total_debit);
            totalCredit = itemView.findViewById(R.id.total_credit);
            debitLeft = itemView.findViewById(R.id.debit_left);
            creditLeft = itemView.findViewById(R.id.credit_left);
        }
    }
}
