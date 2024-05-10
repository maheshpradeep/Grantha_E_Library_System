package com.example.grantha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.grantha.databinding.ActivityLoginBinding;

public class login extends AppCompatActivity {

    ActivityLoginBinding binding;
    com.example.grantha.Database databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new Database(this);
        binding.loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                if(email.equals("") || password.equals(""))
                    Toast.makeText(login.this, "All Field Required.", Toast.LENGTH_SHORT).show();

                else {
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(email,password);

                    if(checkCredentials == true){
                        Toast.makeText(login.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(login.this, "Invalid User Name or Password", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });
    }
}