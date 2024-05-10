package com.example.grantha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class updatemembers extends AppCompatActivity {

    EditText firstNameInput, lastNameInput, mobileInput, addressInput, duesInput;
    Button updateButton, deleteButton;

    String cardNo, firstName, lastName, mobileNumber, address, unpaidDues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatemembers);

        firstNameInput = findViewById(R.id.first_name_input);
        lastNameInput = findViewById(R.id.last_name_input);
        mobileInput = findViewById(R.id.mobile_input);
        addressInput = findViewById(R.id.address_input);
        duesInput = findViewById(R.id.dues_input);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseLibrary myDB = new DatabaseLibrary(updatemembers.this);
                firstName = firstNameInput.getText().toString().trim();
                lastName = lastNameInput.getText().toString().trim();
                mobileNumber = mobileInput.getText().toString().trim();
                address = addressInput.getText().toString().trim();
                unpaidDues = duesInput.getText().toString().trim();
                myDB.updateMember(cardNo, firstName, lastName, mobileNumber, address, unpaidDues);

                // Set result to indicate data was updated
                setResult(RESULT_OK);
                finish(); // Close the activity after updating the data
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseLibrary myDB = new DatabaseLibrary(updatemembers.this);
                myDB.deleteMember(cardNo);

                // Set result to indicate data was deleted
                setResult(RESULT_OK);
                finish(); // Close the activity after deleting the data
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("cardNo") && getIntent().hasExtra("firstName") &&
                getIntent().hasExtra("lastName") && getIntent().hasExtra("mobileNumber") &&
                getIntent().hasExtra("address") && getIntent().hasExtra("unpaidDues")) {
            // Getting data from intent
            cardNo = getIntent().getStringExtra("cardNo");
            firstName = getIntent().getStringExtra("firstName");
            lastName = getIntent().getStringExtra("lastName");
            mobileNumber = getIntent().getStringExtra("mobileNumber");
            address = getIntent().getStringExtra("address");
            unpaidDues = getIntent().getStringExtra("unpaidDues");

            // Setting intent data
            firstNameInput.setText(firstName);
            lastNameInput.setText(lastName);
            mobileInput.setText(mobileNumber);
            addressInput.setText(address);
            duesInput.setText(unpaidDues);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}
