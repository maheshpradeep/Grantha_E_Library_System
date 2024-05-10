package com.example.grantha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class updatepublishers extends AppCompatActivity {

    EditText pubNameInput, pubAddressInput, pubNumberInput;
    Button updateButton, deleteButton;

    String name, address, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepublishers);

        pubNameInput = findViewById(R.id.pubname_input);
        pubAddressInput = findViewById(R.id.pubaddress_input);
        pubNumberInput = findViewById(R.id.pubmobile_input);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseLibrary myDB = new DatabaseLibrary(updatepublishers.this);
                name = pubNameInput.getText().toString().trim();
                address = pubAddressInput.getText().toString().trim();
                number = pubNumberInput.getText().toString().trim();
                myDB.updatePublisher(name, address, number);

                // Set result to indicate data was updated
                setResult(RESULT_OK);
                finish(); // Close the activity after updating the data
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseLibrary myDB = new DatabaseLibrary(updatepublishers.this);
                myDB.deletePublisher(name);

                // Set result to indicate data was deleted
                setResult(RESULT_OK);
                finish(); // Close the activity after deleting the data
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("address") &&
                getIntent().hasExtra("number")) {
            // Getting data from intent
            name = getIntent().getStringExtra("name");
            address = getIntent().getStringExtra("address");
            number = getIntent().getStringExtra("number");

            // Setting intent data
            pubNameInput.setText(name);
            pubAddressInput.setText(address);
            pubNumberInput.setText(number);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}
