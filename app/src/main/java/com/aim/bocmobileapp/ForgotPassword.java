package com.aim.bocmobileapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        Button alertOTP = (Button)findViewById(R.id.btnOTPSend);
        alertOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ForgotPassword.this);
                View mView = getLayoutInflater().inflate(R.layout.alert_dialog_otp, null);

                EditText D1 = (EditText)mView.findViewById(R.id.etD1);
                EditText D2 = (EditText)mView.findViewById(R.id.etD2);
                EditText D3 = (EditText)mView.findViewById(R.id.etD3);
                EditText D4 = (EditText)mView.findViewById(R.id.etD4);

                Button OTPsubmit = (Button)mView.findViewById(R.id.btnOTPSubmit);

                OTPsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ForgotPassword.this,"Correct OTP",Toast.LENGTH_SHORT).show();
                        openActivityLoadLogin();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    private void openActivityLoadLogin(){
        Intent intent = new Intent(this, ForgotPasswordNew.class);
        startActivity(intent);
    }
}