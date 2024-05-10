package com.example.grantha;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.util.Patterns;


import com.example.grantha.databinding.SignupBinding;

public class signup extends AppCompatActivity {

    SignupBinding binding;
    com.example.grantha.Database databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new com.example.grantha.Database(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirm = binding.signupConfirm.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(signup.this, "All fields required.", Toast.LENGTH_SHORT).show();
                } else {
                    // Validate email format using Patterns.EMAIL_ADDRESS
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(signup.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password.equals(confirm)) {
                            boolean checkUserEmail = databaseHelper.checkEmail(email);
                            if (!checkUserEmail) {
                                Boolean insert = databaseHelper.insertData(email, password);
                                if (insert) {
                                    Toast.makeText(signup.this, "SignUp Successfully!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), com.example.grantha.login.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(signup.this, "SignUp Failed.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(signup.this, "User Already Exists.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(signup.this, "Password does not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.grantha.login.class);
                startActivity(intent);

            }
        });

    }
}