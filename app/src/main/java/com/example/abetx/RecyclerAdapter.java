package com.example.abetx;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private String[] groups;

    public RecyclerAdapter(String[] data) {
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
        String stg = groups[position];
        holder.text.setText(stg);
    }

    @Override
    public int getItemCount() {
        return this.groups.length;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView text;

        public RecyclerHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.gj_date);

        }
    }
}
