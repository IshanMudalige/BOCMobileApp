package com.aim.bocmobileapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PayBills extends AppCompatActivity {

    Button btnBPayNow, btnBPayOn, btnBPayPer, btnBCancel, icoBtnAddBiller;
    AutoCompleteTextView dropdown_BPayee, dropdown_BCustomer;
    EditText etBFrmAcc, etBAmount, etBPayeeNo;

    String[] CUSTOMER = new String[] {"R.M.Rajapakse", "Moveena Rajapakse", "R.M.Jayawardena"};

    String cus,bill,b_no,frmACC,amount;

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

        btnBPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cus = dropdown_BCustomer.getText().toString();
                bill = dropdown_BPayee.getText().toString();
                b_no = etBPayeeNo.getText().toString();
                frmACC = etBFrmAcc.getText().toString();
                amount = etBAmount.getText().toString();

                showSuccessMsg();
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

}