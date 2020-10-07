package com.aim.bocmobileapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aim.bocmobileapp.model.Payee;
import com.aim.bocmobileapp.model.PayeeHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ThirdpartyTransferActivity extends AppCompatActivity {

    Button btnPayNow,btnPayOn,btnPayPer,btnCancel,btnAdd;
    AutoCompleteTextView cbCurrency,cbPayee,cbSource;
    EditText etAmount,etDescription;

    String[] CURRENCY = new String[] {"LKR", "USD", "AUS", "IND","JAP"};
    String[] SOURCE = new String[] {"Acc Kandy", "Acc Malabe"};

    String desc,amount,payee,source,currency;

    SharedPreferences sharedpreferences;
    PayeeHandler payeeHandler;

    List<Payee> list;
    List<String> PAYEE = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        getRecord();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdparty_transfer);

        etAmount = findViewById(R.id.etAmount);
        etDescription = findViewById(R.id.etDesc);
        btnPayNow = findViewById(R.id.btnPayNow);
        btnPayOn = findViewById(R.id.btnAccount);
        btnPayPer = findViewById(R.id.btnPayPer);
        btnAdd = findViewById(R.id.icoBtnAdd);
        btnCancel= findViewById(R.id.btnCancel);
        cbPayee = findViewById(R.id.dropdown_payee);
        cbCurrency = findViewById(R.id.dropdown_currency);
        cbSource = findViewById(R.id.dropdown_source);

        ArrayAdapter<String> adapterPayee = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, PAYEE);
        cbPayee.setAdapter(adapterPayee);

        ArrayAdapter<String> adapterCurrency = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, CURRENCY);
        cbCurrency.setAdapter(adapterCurrency);

        ArrayAdapter<String> adapterSource = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, SOURCE);
        cbSource.setAdapter(adapterSource);

        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etAmount.getText())){
                    etAmount.setError("Amount required");
                }else if(TextUtils.isEmpty(etDescription.getText())){
                    etDescription.setError("A description required");
//                }else if(TextUtils.isEmpty(cbCurrency.getText())){
//                    cbCurrency.setError("Currency required");
//                }else if(TextUtils.isEmpty(cbSource.getText())){
//                    cbSource.setError("Please select a source");
//                }else if(TextUtils.isEmpty(cbPayee.getText())) {
//                    cbPayee.setError("Please select a payee");
                }else {
                    amount = etAmount.getText().toString();
                    desc = etDescription.getText().toString();
                    currency = cbCurrency.getText().toString();
                    source = cbSource.getText().toString();
                    payee = cbPayee.getText().toString();

                    showSuccessMsg();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdpartyTransferActivity.this,MainActivity.class));
            }
        });

        btnPayOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdpartyTransferActivity.this, "Not available right now", Toast.LENGTH_SHORT).show();
            }
        });

        btnPayPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdpartyTransferActivity.this, "Not available right now", Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdpartyTransferActivity.this,PayeeMaintainActivity.class));
            }
        });


    }

    private void showSuccessMsg() {

        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog, viewGroup, false);

        TextView tvPayee= dialogView.findViewById(R.id.tvTPayee);
        TextView tvSrc = dialogView.findViewById(R.id.tvTSrc);
        TextView tvAmnt = dialogView.findViewById(R.id.tvTAmnt);
        TextView tvCurrency = dialogView.findViewById(R.id.tvTCurr);
        TextView tvDesc = dialogView.findViewById(R.id.tvTDesc);
        Button btnCancel = dialogView.findViewById(R.id.btnTCancel) ;
        Button btnVerify = dialogView.findViewById(R.id.btnVerify) ;

        tvAmnt.setText(amount);
        tvCurrency.setText(currency);
        tvDesc.setText(desc);
        tvPayee.setText(payee);
        tvSrc.setText(source);

        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialog);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        //alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.background_header);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdpartyTransferActivity.this,TransactionSuccessActivity.class));
            }
        });


    }

    public void getRecord(){
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedpreferences.getString("PAYEE_HANDLER", "");
        payeeHandler = gson.fromJson(json, PayeeHandler.class);

        list = payeeHandler.getPayee();
        PAYEE.clear();
        for(Payee x:list){

            System.out.println(x.getName());
            PAYEE.add(x.getName());
        }
    }




}