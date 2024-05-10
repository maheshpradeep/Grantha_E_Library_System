package com.example.grantha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class addbranch extends AppCompatActivity {

    EditText branchNameInput, branchAddressInput;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbranch);

        branchNameInput = findViewById(R.id.branch_name_input);
        branchAddressInput = findViewById(R.id.branch_address_input);
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseLibrary myDB = new DatabaseLibrary(addbranch.this);
                myDB.addBranch(branchNameInput.getText().toString().trim(),
                        branchAddressInput.getText().toString().trim());

                // Clear input fields after adding data
                clearInputFields();
            }
        });
    }

    // Method to clear input fields
    private void clearInputFields() {
        branchNameInput.setText("");
        branchAddressInput.setText("");
    }
}
