package com.example.mutrasf;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText phoneNumberEditText, nameEditText, passwordEditText;
    Button signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        phoneNumberEditText = (EditText) findViewById(R.id.editTextText4);
        nameEditText = (EditText) findViewById(R.id.editTextText5);
        passwordEditText = (EditText) findViewById(R.id.editTextText6);
        signup = (Button) findViewById(R.id.button6);
        DB = new DBHelper(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input values
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();

                // Check if any of the fields are empty
                if (phoneNumber.isEmpty() || password.isEmpty() || name.isEmpty()) {
                    Toast.makeText(signup.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Attempt to insert user data into the database
                    DBHelper dbHelper = new DBHelper(signup.this);
                    boolean success = dbHelper.insertData(phoneNumber, password, name);

                    if (success) {
                        // Redirect to dashboard activity
                        Intent intent = new Intent(signup.this, dashboard.class);
                        startActivity(intent);
                        finish(); // Optional: Close the sign-up activity to prevent going back to it
                    } else {
                        Toast.makeText(signup.this, "Sign-up failed. Phone number already exists.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
