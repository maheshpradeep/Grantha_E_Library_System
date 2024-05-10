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

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> book_id, book_title, book_author, book_pages;

    CustomerAdapter(Context context, ArrayList<String> book_id, ArrayList<String> book_title, ArrayList<String> book_author, ArrayList<String> book_pages) {
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.book_id_txt.setText(book_id.get(position));
        holder.book_title_txt.setText(book_title.get(position));
        holder.book_author_txt.setText(book_author.get(position));
        holder.book_pages_txt.setText(book_pages.get(position));

        // Adding click listener to edit the book
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, updatebooks.class);
                intent.putExtra("id", book_id.get(position));
                intent.putExtra("title", book_title.get(position));
                intent.putExtra("author", book_author.get(position));
                intent.putExtra("pages", book_pages.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_text);
            book_title_txt = itemView.findViewById(R.id.book_title);
            book_author_txt = itemView.findViewById(R.id.book_author);
            book_pages_txt = itemView.findViewById(R.id.book_pages);
        }
    }

    // Method to delete a book
    void deleteBook(int position) {
        DatabaseLibrary myDB = new DatabaseLibrary(context);
        myDB.deleteData(book_id.get(position));
        book_id.remove(position);
        book_title.remove(position);
        book_author.remove(position);
        book_pages.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, book_id.size());
    }
}
