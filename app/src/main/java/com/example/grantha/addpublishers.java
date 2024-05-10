package com.example.grantha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class addpublishers extends AppCompatActivity {

    EditText pubNameInput, pubAddressInput, pubNumberInput;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpublishers);

        pubNameInput = findViewById(R.id.pubname_input);
        pubAddressInput = findViewById(R.id.pubaddress_input);
        pubNumberInput = findViewById(R.id.pubmobile_input);
        addButton = findViewById(R.id.publisher_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseLibrary myDB = new DatabaseLibrary(addpublishers.this);
                myDB.addPublisher(pubNameInput.getText().toString().trim(),
                        pubAddressInput.getText().toString().trim());

                clearInputFields();
            }
        });
    }

    private void clearInputFields() {
        pubNameInput.setText("");
        pubAddressInput.setText("");
        pubNumberInput.setText("");
    }
}
