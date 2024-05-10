package com.example.grantha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updatebrabch extends AppCompatActivity {

    EditText branchNameInput, branchAddressInput;
    Button updateButton, deleteButton;

    String branchId, branchName, branchAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatebranch);

        branchNameInput = findViewById(R.id.branch_name_input);
        branchAddressInput = findViewById(R.id.branch_address_input);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseLibrary myDB = new DatabaseLibrary(updatebrabch.this);
                branchName = branchNameInput.getText().toString().trim();
                branchAddress = branchAddressInput.getText().toString().trim();
                myDB.updateBranch(branchId, branchName, branchAddress);

                // Set result to indicate data was updated
                setResult(RESULT_OK);
                finish(); // Close the activity after updating the data
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseLibrary myDB = new DatabaseLibrary(updatebrabch.this);
                myDB.deleteBranch(branchId);

                // Set result to indicate data was deleted
                setResult(RESULT_OK);
                finish(); // Close the activity after deleting the data
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("branch_id") && getIntent().hasExtra("branch_name") &&
                getIntent().hasExtra("branch_address")) {
            // Getting data from intent
            branchId = getIntent().getStringExtra("branch_id");
            branchName = getIntent().getStringExtra("branch_name");
            branchAddress = getIntent().getStringExtra("branch_address");

            // Setting intent data
            branchNameInput.setText(branchName);
            branchAddressInput.setText(branchAddress);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}
