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

import com.aim.bocmobileapp.model.Biller;
import com.aim.bocmobileapp.model.BillerHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PayBills extends AppCompatActivity {

    Button btnBPayNow, btnBPayOn, btnBPayPer, btnBCancel, icoBtnAddBiller;
    AutoCompleteTextView dropdown_BPayee, dropdown_BCustomer, etBPayeeNo, etBFrmAcc;
    EditText etBAmount;

    String[] CUSTOMER = new String[] {"R.M.Rajapakse", "Moveena Rajapakse", "R.M.Jayawardena"};
    String[] FRMACC = new String[] {"0123489080", "0123489080", "0123489811"};

    String cus,bill,b_no,frmACC,amount;

    SharedPreferences sharedpreferences;
    BillerHandler billerHandler;

    List<Biller> list;
    List<String> BILLER = new ArrayList<>();
    List<String> BILLERNO = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        getRecord();
        getBillNo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bills);

        btnBPayNow = findViewById(R.id.btnBPayNow);
        btnBPayOn = findViewById(R.id.btnBPayOn);
        btnBPayPer = findViewById(R.id.btnBPayPer);
        btnBCancel = findViewById(R.id.btnBCancel);
        icoBtnAddBiller = findViewById(R.id.icoBtnAddBiller);
        dropdown_BCustomer = findViewById(R.id.dropdown_BCustomer);
        dropdown_BPayee = findViewById(R.id.dropdown_BPayee);
        etBFrmAcc = findViewById(R.id.etBFrmAcc);
        etBAmount = findViewById(R.id.etBAmount);
        etBPayeeNo = findViewById(R.id.etBPayeeNo);

        ArrayAdapter<String> adapterCustomer = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, CUSTOMER);
        dropdown_BCustomer.setAdapter(adapterCustomer);

        ArrayAdapter<String> adapterFrmAccount = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, FRMACC);
        etBFrmAcc.setAdapter(adapterFrmAccount);

        ArrayAdapter<String> adapterBiller = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, BILLER);
        dropdown_BPayee.setAdapter(adapterBiller);

        ArrayAdapter<String> adapterBillerNo = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, BILLERNO);
        etBPayeeNo.setAdapter(adapterBillerNo);

        btnBPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etBPayeeNo.getText())){
                    etBPayeeNo.setError("Biller No is required");
                }else if(TextUtils.isEmpty(etBFrmAcc.getText())){
                    etBFrmAcc.setError("Account is required");
                }else if(TextUtils.isEmpty(etBAmount.getText())){
                    etBAmount.setError("Amount required");
                }else {
                    cus = dropdown_BCustomer.getText().toString();
                    bill = dropdown_BPayee.getText().toString();
                    b_no = etBPayeeNo.getText().toString();
                    frmACC = etBFrmAcc.getText().toString();
                    amount = etBAmount.getText().toString();


                    showSuccessMsg();
                }
            }
        });

        btnBCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayBills.this,MainActivity.class));
            }
        });

        btnBPayOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PayBills.this, "Not available right now", Toast.LENGTH_SHORT).show();
            }
        });

        btnBPayPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PayBills.this, "Not available right now", Toast.LENGTH_SHORT).show();
            }
        });

        icoBtnAddBiller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayBills.this,BillerMaintainActivity.class));
            }
        });

    }

    private void showSuccessMsg() {

        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_paybills, viewGroup, false);

        TextView tvBPBiller= dialogView.findViewById(R.id.tvBPBiller);
        TextView tvBPCus = dialogView.findViewById(R.id.tvBPCus);
        TextView tvBPNo = dialogView.findViewById(R.id.tvBPNo);
        TextView tvBPFACC = dialogView.findViewById(R.id.tvBPFACC);
        TextView tvBPAmt = dialogView.findViewById(R.id.tvBPAmt);
        Button btnBPCancel = dialogView.findViewById(R.id.btnBPCancel) ;
        Button btnBPVerify = dialogView.findViewById(R.id.btnBPVerify) ;

        tvBPCus.setText(cus);
        tvBPBiller.setText(bill);
        tvBPNo.setText(b_no);
        tvBPFACC.setText(frmACC);
        tvBPAmt.setText(amount);

        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialog);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        //alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.background_header);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        btnBPCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
            }
        });

        btnBPVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayBills.this,TransactionSuccessActivity.class));
            }
        });


    }

    public void getRecord(){
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedpreferences.getString("BILLER_HANDLER", "");
        billerHandler = gson.fromJson(json, BillerHandler.class);

        try {
            list = billerHandler.getBiller();
            BILLER.clear();
            for (Biller x : list) {

                System.out.println(x.getB_name());
                BILLER.add(x.getB_name());
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public void getBillNo() {
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedpreferences.getString("BILLER_HANDLER", "");
        billerHandler = gson.fromJson(json, BillerHandler.class);

        //String cName = dropdown_BCustomer.getText().toString();
        list = billerHandler.getBiller();
        BILLERNO.clear();

        for(Biller x:list){

            System.out.println(x.getB_No());
            BILLERNO.add(x.getB_No());
        }

    }

}