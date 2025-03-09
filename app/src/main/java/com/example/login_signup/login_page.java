package com.example.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login_page extends AppCompatActivity {

    EditText userName, userPassword;
    TextView signupButton;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        userName = findViewById(R.id.username);
        userPassword = findViewById(R.id.userpassword);
        loginButton = findViewById(R.id.loginbutton);
        signupButton=findViewById(R.id.signupbuttonn);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login_page.this, registerpage.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.length() > 0 || userPassword.length() > 8) {
                    if (userPassword.length() < 8) {
                        Toast.makeText(login_page.this, " Password al least 8 character ", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(login_page.this, " Fill form ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}