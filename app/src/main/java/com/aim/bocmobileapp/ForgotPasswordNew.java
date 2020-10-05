package com.aim.bocmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ForgotPasswordNew extends AppCompatActivity {

    private TextView tvp1,tvp2;
    private Button btnSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_new);

        tvp1 = (TextView) findViewById(R.id.etNewPassword);
        tvp1 = (TextView) findViewById(R.id.etNewPassword2);

        btnSub = (Button)findViewById(R.id.btnChangePassword);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityPwdSub();
            }
        });

    }

    private void openActivityPwdSub(){
        Intent intent = new Intent(this, LogInPage.class);
        startActivity(intent);
    }




}