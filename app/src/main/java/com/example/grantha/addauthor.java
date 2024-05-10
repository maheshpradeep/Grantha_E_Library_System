package com.example.grantha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class addauthor extends AppCompatActivity {

    EditText author_name_input, book_id_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addauthor);

        author_name_input = findViewById(R.id.author_name_input);
        book_id_input = findViewById(R.id.book_id_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseLibrary myDB = new DatabaseLibrary(addauthor.this);
                myDB.addAuthor(author_name_input.getText().toString().trim(),
                        book_id_input.getText().toString().trim());

                // Clear input fields after adding data
                clearInputFields();
            }
        });
    }

    // Method to clear input fields
    private void clearInputFields() {
        author_name_input.setText("");
        book_id_input.setText("");
    }
}
