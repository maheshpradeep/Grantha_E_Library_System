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

public class publishers extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    DatabaseLibrary myDB;
    ArrayList<String> PubName, pubAddresses, pubNumbers;
    CusAdapPublisher cusAdapPublisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishers);
        recyclerView = findViewById(R.id.recyclerview);
        addButton = findViewById(R.id.addpublisher_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(publishers.this, addpublishers.class);
                startActivity(intent);
            }
        });

        myDB = new DatabaseLibrary(publishers.this);
        PubName = new ArrayList<>();
        pubAddresses = new ArrayList<>();
        pubNumbers = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cusAdapPublisher = new CusAdapPublisher(publishers.this, PubName, pubAddresses, pubNumbers);
        recyclerView.setAdapter(cusAdapPublisher);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }


    private void refreshData() {
        PubName.clear();
        pubAddresses.clear();
        pubNumbers.clear();

        Cursor cursor = myDB.readAllPublishers();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                PubName.add(cursor.getString(0));
                pubAddresses.add(cursor.getString(1));
                pubNumbers.add(cursor.getString(2));
            }
        }

        cusAdapPublisher.notifyDataSetChanged();

    }

    private void clearInputFields() {
        EditText pubNameInput = findViewById(R.id.pubname_input);
        EditText pubAddressInput = findViewById(R.id.pubaddress_input);
        EditText pubNumberInput = findViewById(R.id.pubmobile_input);

        pubNameInput.setText("");
        pubAddressInput.setText("");
        pubNumberInput.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            refreshData();
            clearInputFields();
        }
    }
}
