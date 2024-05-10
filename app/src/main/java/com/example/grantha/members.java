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

public class members extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DatabaseLibrary myDB;
    ArrayList<String> memberCardNo, memberFirstName, memberLastName, memberMobileNumber, memberAddress, memberUnpaidDues;
    CusAdapMember cusAdapMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        recyclerView = findViewById(R.id.recyclerview);
        add_button = findViewById(R.id.addmember_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(members.this, addmembers.class);
                startActivity(intent);
            }
        });

        myDB = new DatabaseLibrary(members.this);
        memberCardNo = new ArrayList<>();
        memberFirstName = new ArrayList<>();
        memberLastName = new ArrayList<>();
        memberMobileNumber = new ArrayList<>();
        memberAddress = new ArrayList<>();
        memberUnpaidDues = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cusAdapMember = new CusAdapMember(members.this, memberCardNo, memberFirstName, memberLastName, memberMobileNumber, memberAddress, memberUnpaidDues);
        recyclerView.setAdapter(cusAdapMember);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData() {
        memberCardNo.clear();
        memberFirstName.clear();
        memberLastName.clear();
        memberMobileNumber.clear();
        memberAddress.clear();
        memberUnpaidDues.clear();

        Cursor cursor = myDB.readAllMembers();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                memberCardNo.add(cursor.getString(0));
                memberFirstName.add(cursor.getString(1));
                memberLastName.add(cursor.getString(2));
                memberMobileNumber.add(cursor.getString(3));
                memberAddress.add(cursor.getString(4));
                memberUnpaidDues.add(cursor.getString(5));
            }
        }

        cusAdapMember.notifyDataSetChanged();
    }

    private void clearInputFields() {
        EditText firstNameInput = findViewById(R.id.first_name_input);
        EditText lastNameInput = findViewById(R.id.last_name_input);
        EditText mobileInput = findViewById(R.id.mobile_input);
        EditText addressInput = findViewById(R.id.address_input);
        EditText duesInput = findViewById(R.id.dues_input);

        firstNameInput.setText("");
        lastNameInput.setText("");
        mobileInput.setText("");
        addressInput.setText("");
        duesInput.setText("");
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