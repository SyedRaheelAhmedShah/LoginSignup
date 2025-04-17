package com.example.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText signUpName, signUpUserName, signUpEmail, signUpPassword;
    TextView loginButton;
    Button signupButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpName = findViewById(R.id.Name);
        signUpUserName = findViewById(R.id.UserName);
        signUpEmail = findViewById(R.id.email);
        signUpPassword = findViewById(R.id.password);
        signupButton = findViewById(R.id.signupbutton);
        loginButton = findViewById(R.id.loginbutton);

        signupButton.setOnClickListener(v -> {
            firebaseDatabase = FirebaseDatabase.getInstance();
            dbReference = firebaseDatabase.getReference("users");

            String name = signUpName.getText().toString();
            String userName = signUpUserName.getText().toString();
            String email = signUpEmail.getText().toString();
            String password = signUpPassword.getText().toString();

            DataManagerclass dataManager = new DataManagerclass(name, userName, email, password);
            dbReference.child(userName).setValue(dataManager)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, LoginPage.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUp.this, "Failed to store data", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, LoginPage.class);
            startActivity(intent);
        });
    }
}
