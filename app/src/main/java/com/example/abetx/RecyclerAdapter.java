package com.example.abetx;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abetx.Models.Transactions;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private Transactions[] groups;

    public RecyclerAdapter(Transactions[] data) {
        this.groups = data;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_recyclerview_generaljournal, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        Transactions transactions = groups[position];
        holder.date.setText(transactions.getDate());
        holder.credit.setText(transactions.getCredit() + "");
        holder.creditHead.setText(transactions.getCredit_head());
        holder.debit.setText(transactions.getDebit() + "");
        holder.debitHead.setText(transactions.getDebit_head());
    }

    @Override
    public int getItemCount() {
        return this.groups.length;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView credit;
        TextView creditHead;
        TextView debit;
        TextView debitHead;

        public RecyclerHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.gj_date);
            credit = itemView.findViewById(R.id.gj_credit);
            creditHead = itemView.findViewById(R.id.gj_credit_head);
            debit = itemView.findViewById(R.id.gj_debit);
            debitHead = itemView.findViewById(R.id.gj_debit_head);

        }
    }
}
