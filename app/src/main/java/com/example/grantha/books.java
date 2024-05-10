package com.example.grantha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class books extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DatabaseLibrary myDB;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    CustomerAdapter customerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        recyclerView = findViewById(R.id.recyclerview);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(books.this,addbooks.class);
                startActivity(intent);
            }
        });

        myDB = new DatabaseLibrary(books.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        // Initialize RecyclerView and its adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customerAdapter = new CustomerAdapter(books.this, book_id, book_title, book_author, book_pages);
        recyclerView.setAdapter(customerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when activity resumes
        refreshData();
    }

    private void refreshData() {
        // Clear previous data
        book_id.clear();
        book_title.clear();
        book_author.clear();
        book_pages.clear();

        // Retrieve new data from the database
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
        }

        // Notify the adapter of the changes
        customerAdapter.notifyDataSetChanged();
    }

    // Method to clear input fields
    private void clearInputFields() {
        EditText titleInput = findViewById(R.id.title_input);
        EditText authorInput = findViewById(R.id.author_input);
        EditText pagesInput = findViewById(R.id.publisher_input);

        titleInput.setText("");
        authorInput.setText("");
        pagesInput.setText("");
    }

    // Override onActivityResult to clear input fields after adding data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Refresh data
            refreshData();
            // Clear input fields
            clearInputFields();
        }
    }
}
