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

public class CusAdapAuthor extends RecyclerView.Adapter<CusAdapAuthor.MyViewHolder> {
    private Context context;
    private ArrayList<String> author_name, book_id;

    CusAdapAuthor(Context context, ArrayList<String> author_name, ArrayList<String> book_id) {
        this.context = context;
        this.author_name = author_name;
        this.book_id = book_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rowauthor, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.author_name_txt.setText(author_name.get(position));
        holder.book_id_txt.setText(book_id.get(position));

        // Adding click listener to edit the author
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // You can add functionality here to handle clicks on authors if needed
            }
        });
    }

    @Override
    public int getItemCount() {
        return author_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView author_id_txt, author_name_txt, book_id_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            author_name_txt = itemView.findViewById(R.id.author_name);
            book_id_txt = itemView.findViewById(R.id.book_id);
        }
    }

  /*  // Method to delete an author
    void deleteAuthor(int position) {
        DatabaseLibrary myDB = new DatabaseLibrary(context);
        myDB.deleteAuthor(author_name.get(position));
        author_name.remove(position);
        book_id.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, author_name.size());
    }*/
}
