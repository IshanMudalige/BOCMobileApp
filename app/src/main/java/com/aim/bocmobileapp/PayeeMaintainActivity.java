package com.aim.bocmobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.aim.bocmobileapp.adapter.PayeeAdapter;
import com.aim.bocmobileapp.model.Payee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PayeeMaintainActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdd;
    EditText etName,etMail,etNick,etAcc;

    PayeeAdapter adapter;
    List<Payee> lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payee_maintain);

        listView = findViewById(R.id.listViewPayee);
        etName = findViewById(R.id.etName);
        etAcc = findViewById(R.id.etAcc);
        etMail = findViewById(R.id.etEmail);
        etNick = findViewById(R.id.etNick);
        btnAdd = findViewById(R.id.btnPAdd);

        final AppData appData = new AppData();
        lst = appData.getPayee();
        adapter = new PayeeAdapter(PayeeMaintainActivity.this,R.layout.item_payee,lst);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Payee payee = new Payee(etName.getText().toString(),etNick.getText().toString(),etAcc.getText().toString(),etMail.getText().toString());
                appData.addPayee(payee);

                lst = appData.getPayee();
                adapter = new PayeeAdapter(PayeeMaintainActivity.this,R.layout.item_payee,lst);
                listView.setAdapter(adapter);

            }
        });

    }
}