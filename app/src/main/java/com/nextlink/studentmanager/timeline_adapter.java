package com.nextlink.studentmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class timeline_adapter extends RecyclerView.Adapter<timeline_adapter.ProductViewHolder> {

    private Context mCtx;

    private List<timeline_model> detailList;

    public timeline_adapter(Context mCtx, List<timeline_model> detailList) {
        this.mCtx = mCtx;
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public timeline_adapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_timeline, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        timeline_model time_mod = detailList.get(position);
        holder.t_subject_name.setText(time_mod.getSub_name());
        holder.t_timestamp.setText(time_mod.getTimestamp());
        holder.t_action.setText(time_mod.getAction());
    }


    @Override
    public int getItemCount() {
        return detailList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView t_subject_name, t_timestamp, t_action;

        public ProductViewHolder(View itemView) {
            super(itemView);
            t_subject_name = itemView.findViewById(R.id.timeline_sub_name);
            t_timestamp = itemView.findViewById(R.id.timeline_time);
            t_action = itemView.findViewById(R.id.timeline_action);
        }
    }
}
