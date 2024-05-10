package com.example.grantha;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find the button by its ID
        Button libraryButton = findViewById(R.id.library);
        Button memberButton =findViewById(R.id.Members);

        // Set OnClickListener to the button
        libraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to another activity
                Intent intent = new Intent(home.this, library.class);
                startActivity(intent);
            }
        });

        memberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,members.class);
                startActivity(intent);
            }
        });
    }

}
