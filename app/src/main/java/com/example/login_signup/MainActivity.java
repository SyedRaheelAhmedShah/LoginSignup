package com.example.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView singupPage, loginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//
//        FirebaseDatabase database=FirebaseDatabase.getInstance();
//        DatabaseReference myRef=database.getReference("massage");
//
//        myRef.setValue("hello world");
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        singupPage = findViewById(R.id.signuppage);
        loginPage = findViewById(R.id.loginpage);

        singupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
            }
        });

    }
}