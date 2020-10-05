package com.aim.bocmobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LogIn extends AppCompatActivity {

    private Button btnLoginPage, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        btnLoginPage = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityLogInButton();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityRegisterButton();
            }
        });

    }

    public void openActivityLogInButton(){
        Intent intent = new Intent(this, LogInPage.class);
        startActivity(intent);
    }

    public void openActivityRegisterButton(){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

}