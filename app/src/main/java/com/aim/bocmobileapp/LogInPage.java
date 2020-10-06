package com.aim.bocmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LogInPage extends AppCompatActivity {

    Button login;
    EditText username,pwd;
    TextView register,forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        getSupportActionBar().setElevation(0);

        login = (Button)findViewById(R.id.btnLoginUser);
        username = (EditText)findViewById(R.id.etUserNameLogin);
        pwd = (EditText)findViewById(R.id.etPasswordLogin);
        register = findViewById(R.id.twNewUser);
        forget = findViewById(R.id.twForgotPassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInPage.this,RegisterPage.class));
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInPage.this,ForgotPassword.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityLogInUser();
            }
        });
    }

    private void openActivityLogInUser(){
        String user = username.getText().toString().trim();
        String password = pwd.getText().toString().trim();

        if(user.equals("Kamal") && password.equals("Kamal123")){
            Toast.makeText(this,"LOG IN SUCCESSFUL",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,"LOG IN FAILED",Toast.LENGTH_SHORT).show();
        }
    }

}