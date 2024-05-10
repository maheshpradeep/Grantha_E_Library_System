package com.example.grantha;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class library extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        Button libraryButton = findViewById(R.id.books);
        Button publisherButton = findViewById(R.id.publisher);
        Button branchButton = findViewById(R.id.branch);
        Button authorButton = findViewById(R.id.author);


        libraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(library.this, books.class);
                startActivity(intent);
            }
        });

        publisherButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(library.this, publishers.class);
                startActivity(intent);

            }
        });

        branchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(library.this, branchs.class);
                startActivity(intent);
            }
        });

        authorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(library.this, author.class);
                startActivity(intent);
            }

        });
    }
}