package com.example.grantha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class author extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DatabaseLibrary myDB;
    ArrayList<String> author_id, author_name, book_id;
    CusAdapAuthor authorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        recyclerView = findViewById(R.id.recyclerview);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(author.this, addauthor.class);
                startActivity(intent);
            }
        });

        myDB = new DatabaseLibrary(author.this);
        author_id = new ArrayList<>();
        author_name = new ArrayList<>();
        book_id = new ArrayList<>();

        // Initialize RecyclerView and its adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        authorAdapter = new CusAdapAuthor(author.this, author_name, book_id);
        recyclerView.setAdapter(authorAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when activity resumes
        refreshData();
    }

    private void refreshData() {
        // Clear previous data
        author_id.clear();
        author_name.clear();
        book_id.clear();

        // Retrieve new data from the database
        Cursor cursor = myDB.readAllAuthors();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                author_id.add(cursor.getString(0));
                author_name.add(cursor.getString(1));
                book_id.add(cursor.getString(2));
            }
        }

        // Notify the adapter of the changes
        authorAdapter.notifyDataSetChanged();
    }
}
