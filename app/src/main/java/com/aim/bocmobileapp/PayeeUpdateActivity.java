package com.aim.bocmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aim.bocmobileapp.model.Payee;
import com.aim.bocmobileapp.model.PayeeHandler;
import com.google.gson.Gson;

import static com.aim.bocmobileapp.PayeeMaintainActivity.PAYEE;
import static com.aim.bocmobileapp.PayeeMaintainActivity.POS;


public class PayeeUpdateActivity extends AppCompatActivity {

    Button btnUpdate,btnDel;
    EditText etName,etMail,etNick,etAcc;
    int position;
    SharedPreferences sharedpreferences;
    PayeeHandler payeeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payee_update);

        Intent intent = getIntent();
        Payee payee = (Payee) intent.getSerializableExtra(PAYEE);
        position = intent.getIntExtra(POS,0);

        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedpreferences.getString("PAYEE_HANDLER", "");
        payeeHandler = gson.fromJson(json, PayeeHandler.class);

        etName = findViewById(R.id.etUName);
        etAcc = findViewById(R.id.etUAcc);
        etMail = findViewById(R.id.etUEmail);
        etNick = findViewById(R.id.etUNick);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDel = findViewById(R.id.btnDelete);

        etName.setText(payee.getName());
        etNick.setText(payee.getNick());
        etMail.setText(payee.getEmail());
        etAcc.setText(payee.getAcc());

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payeeHandler.deletePayee(position);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(payeeHandler);
                editor.putString("PAYEE_HANDLER",json);
                editor.apply();
                startActivity(new Intent(PayeeUpdateActivity.this,PayeeMaintainActivity.class));
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payee payeeUpdated = new Payee(etName.getText().toString(),etNick.getText().toString(),etAcc.getText().toString(),etMail.getText().toString());
                payeeHandler.updatePayee(payeeUpdated,position);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(payeeHandler);
                editor.putString("PAYEE_HANDLER",json);
                editor.apply();
            }
        });

    }
}