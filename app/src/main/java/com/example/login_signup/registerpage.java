package com.example.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registerpage extends AppCompatActivity {

    EditText firstName, lastName, eMail, phone, password, confirmPassword;
    TextView loginButton;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        eMail = findViewById(R.id.email);
        phone = findViewById(R.id.phonenumber);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmpassword);
        signupButton = findViewById(R.id.signupbutton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(registerpage.this, login_page.class);
                    startActivity(intent);
            }
        });
        loginButton=findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(registerpage.this, login_page.class);
                startActivity(intent);
            }
        });


    }
}