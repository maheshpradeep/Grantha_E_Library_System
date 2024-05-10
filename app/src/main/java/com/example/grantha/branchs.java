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

public class branchs extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DatabaseLibrary myDB;
    ArrayList<String> branch_id, branch_name, branch_address;
    CusAdapBranch branchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branchs);
        recyclerView = findViewById(R.id.recyclerview);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(branchs.this, addbranch.class);
                startActivity(intent);
            }
        });

        myDB = new DatabaseLibrary(branchs.this);
        branch_id = new ArrayList<>();
        branch_name = new ArrayList<>();
        branch_address = new ArrayList<>();

        // Initialize RecyclerView and its adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        branchAdapter = new CusAdapBranch(branchs.this, branch_id, branch_name, branch_address);
        recyclerView.setAdapter(branchAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when activity resumes
        refreshData();
    }

    private void refreshData() {
        // Clear previous data
        branch_id.clear();
        branch_name.clear();
        branch_address.clear();

        // Retrieve new data from the database
        Cursor cursor = myDB.readAllBranches();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                branch_id.add(cursor.getString(0));
                branch_name.add(cursor.getString(1));
                branch_address.add(cursor.getString(2));
            }
        }

        // Notify the adapter of the changes
        branchAdapter.notifyDataSetChanged();
    }

    // Method to clear input fields
    private void clearInputFields() {
        EditText branchNameInput = findViewById(R.id.branch_name_input);
        EditText branchAddressInput = findViewById(R.id.branch_address_input);

        branchNameInput.setText("");
        branchAddressInput.setText("");
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
