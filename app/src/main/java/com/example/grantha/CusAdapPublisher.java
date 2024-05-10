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

public class CusAdapPublisher extends RecyclerView.Adapter<CusAdapPublisher.PublisherViewHolder> {
    private Context context;
    private ArrayList<String> PubName, PubAddress, PubNumber;

    CusAdapPublisher(Context context, ArrayList<String> PubName, ArrayList<String> PubAddress, ArrayList<String> PubNumber) {
        this.context = context;
        this.PubName = PubName;
        this.PubAddress = PubAddress;
        this.PubNumber = PubNumber;
    }

    @NonNull
    @Override
    public PublisherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rowpublisher, parent, false);
        return new PublisherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublisherViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.PubNameTxt.setText(PubName.get(position));
        holder.PubAddressTxt.setText(PubAddress.get(position));
        holder.PubNumberTxt.setText(PubNumber.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, updatepublishers.class);
                intent.putExtra("Name", PubName.get(position));
                intent.putExtra("Address", PubAddress.get(position));
                intent.putExtra("Number", PubNumber.get(position));
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() { return PubName.size();}

    public class PublisherViewHolder extends RecyclerView.ViewHolder {
        TextView PubNameTxt, PubAddressTxt, PubNumberTxt;

        public PublisherViewHolder(@NonNull View itemView) {
            super(itemView);
            PubNameTxt = itemView.findViewById(R.id.pubname_input);
            PubAddressTxt = itemView.findViewById(R.id.pubaddress_input);
            PubNumberTxt = itemView.findViewById(R.id.pubmobile_input);

            // Set click listener
        }


    }

    void deleteBook(int position) {
        DatabaseLibrary myDB = new DatabaseLibrary(context);
        myDB.deletePublisher(PubName.get(position));
        PubName.remove(position);
        PubAddress.remove(position);
        PubNumber.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, PubName.size());
    }



}