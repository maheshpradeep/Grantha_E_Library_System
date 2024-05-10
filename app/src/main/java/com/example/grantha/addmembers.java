package com.example.grantha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class addmembers extends AppCompatActivity {

    EditText  firstNameInput, lastNameInput, mobileInput, addressInput, duesInput;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmembers);

        firstNameInput = findViewById(R.id.first_name_input);
        lastNameInput = findViewById(R.id.last_name_input);
        mobileInput = findViewById(R.id.mobile_input);
        addressInput = findViewById(R.id.address_input);
        duesInput = findViewById(R.id.dues_input);
        add_button = findViewById(R.id.addmember_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseLibrary myDB = new DatabaseLibrary(addmembers.this);
                myDB.addMember(firstNameInput.getText().toString().trim(),
                        lastNameInput.getText().toString().trim(),
                        mobileInput.getText().toString().trim(),
                        addressInput.getText().toString().trim(),
                        duesInput.getText().toString().trim()); // Change to pass String

                // Clear input fields after adding data
                clearInputFields();
            }
        });
    }

    // Method to clear input fields
    private void clearInputFields() {
        firstNameInput.setText("");
        lastNameInput.setText("");
        mobileInput.setText("");
        addressInput.setText("");
        duesInput.setText("");
    }
}
