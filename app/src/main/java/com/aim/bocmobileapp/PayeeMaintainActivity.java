package com.aim.bocmobileapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.aim.bocmobileapp.adapter.PayeeAdapter;
import com.aim.bocmobileapp.model.Payee;
import com.aim.bocmobileapp.model.PayeeHandler;
import com.google.gson.Gson;

import java.util.List;


public class PayeeMaintainActivity extends AppCompatActivity {

    public static String PAYEE = "com.aim.bocmobileapp.PAYEE";
    public static String POS = "com.aim.bocmobileapp.POS";


    ListView listView;
    Button btnAdd;
    EditText etName,etMail,etNick,etAcc;

    PayeeAdapter adapter;
    List<Payee> list;

    PayeeHandler payeeHandler;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payee_maintain);

        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        listView = findViewById(R.id.listViewPayee);
        etName = findViewById(R.id.etName);
        etAcc = findViewById(R.id.etAcc);
        etMail = findViewById(R.id.etEmail);
        etNick = findViewById(R.id.etNick);
        btnAdd = findViewById(R.id.btnLoginUser);

        populateList();
        if(payeeHandler ==null)
            payeeHandler = new PayeeHandler();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Payee payee = new Payee(etName.getText().toString(),etNick.getText().toString(),etAcc.getText().toString(),etMail.getText().toString());
                payeeHandler.addPayee(payee);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(payeeHandler);
                editor.putString("PAYEE_HANDLER",json);
                editor.apply();

                etName.setText("");etAcc.setText("");etMail.setText("");etNick.setText("");
                displayAlert("Success","Beneficiary successfully added.");

                populateList();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Payee pp = (Payee)adapter.getItem(position);

                Intent intent = new Intent(PayeeMaintainActivity.this,PayeeUpdateActivity.class);
                intent.putExtra(PAYEE,pp);
                intent.putExtra(POS,position);
                startActivity(intent);

            }
        });

    }

    public void populateList(){

        Gson gson = new Gson();
        String json = sharedpreferences.getString("PAYEE_HANDLER", "");
        payeeHandler = gson.fromJson(json, PayeeHandler.class);

        try {
            list = payeeHandler.getPayee();
            adapter = new PayeeAdapter(PayeeMaintainActivity.this,R.layout.item_payee,list);
            listView.setAdapter(adapter);
        }catch(NullPointerException e){
            e.printStackTrace();
        }

    }

    public void displayAlert(String title,String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this,R.style.AlertDialog);
        builder1.setMessage(msg);
        builder1.setTitle(title);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

//        builder1.setNegativeButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}