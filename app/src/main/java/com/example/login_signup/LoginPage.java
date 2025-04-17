package com.example.login_signup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;

public class LoginPage extends AppCompatActivity {

    EditText loginUserName, loginUserPassword;
    TextView signupButton;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginUserName = findViewById(R.id.username);
        loginUserPassword = findViewById(R.id.userpassword);
        loginButton = findViewById(R.id.loginbutton);
        signupButton = findViewById(R.id.signupbuttonn);

        loginButton.setOnClickListener(v -> {
            if (validateUserName() && validateUserPassword()) {
                checkUserCredentials();
            }
        });

        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPage.this, SignUp.class);
            startActivity(intent);
        });
    }

    public boolean validateUserName() {
        String username = loginUserName.getText().toString().trim();
        if (username.isEmpty()) {
            loginUserName.setError("Enter a valid username");
            return false;
        } else {
            loginUserName.setError(null);
            return true;
        }
    }

    public boolean validateUserPassword() {
        String password = loginUserPassword.getText().toString().trim();
        if (password.isEmpty() || password.length() < 8) {
            loginUserPassword.setError("Password must be at least 8 characters");
            return false;
        } else {
            loginUserPassword.setError(null);
            return true;
        }
    }

    public void checkUserCredentials() {
        String enteredUsername = loginUserName.getText().toString().trim();
        String enteredPassword = loginUserPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("userName").equalTo(enteredUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    loginUserName.setError(null);
                    String passwordFromDB = snapshot.child(enteredUsername).child("password").getValue(String.class);

                    if (Objects.equals(passwordFromDB, enteredPassword)) {
                        loginUserPassword.setError(null);
                        Intent intent = new Intent(LoginPage.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        loginUserPassword.setError("Invalid password");
                        loginUserPassword.requestFocus();
                    }
                } else {
                    loginUserName.setError("Invalid username");
                    loginUserName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginPage.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
