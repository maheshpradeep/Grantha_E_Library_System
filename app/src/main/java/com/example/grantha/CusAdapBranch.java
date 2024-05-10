package com.example.grantha;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CusAdapBranch extends RecyclerView.Adapter<CusAdapBranch.MyViewHolder> {
    private Context context;
    private ArrayList<String> branch_id, branch_name, branch_address;

    CusAdapBranch(Context context, ArrayList<String> branch_id, ArrayList<String> branch_name, ArrayList<String> branch_address) {
        this.context = context;
        this.branch_id = branch_id;
        this.branch_name = branch_name;
        this.branch_address = branch_address;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rowbranch, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.branch_id_txt.setText(branch_id.get(position));
        holder.branch_name_txt.setText(branch_name.get(position));
        holder.branch_address_txt.setText(branch_address.get(position));

        // Adding click listener to edit the branch
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,updatebrabch.class);
                intent.putExtra("branch_id", branch_id.get(position));
                intent.putExtra("branch_name", branch_name.get(position));
                intent.putExtra("branch_address", branch_address.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return branch_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView branch_id_txt, branch_name_txt, branch_address_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            branch_id_txt = itemView.findViewById(R.id.branch_id_text);
            branch_name_txt = itemView.findViewById(R.id.branch_name);
            branch_address_txt = itemView.findViewById(R.id.branch_address);
        }
    }

    // Method to delete a branch
    void deleteBranch(int position) {
        DatabaseLibrary myDB = new DatabaseLibrary(context);
        myDB.deleteBranch(branch_id.get(position));
        branch_id.remove(position);
        branch_name.remove(position);
        branch_address.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, branch_id.size());
    }
}
