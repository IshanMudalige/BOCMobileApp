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

public class OwnAccount extends AppCompatActivity {

    Button btnpaynowOA,btnpayonOA,btnpayCancelOA,btnperioOA;
    AutoCompleteTextView cbtoAccount,cbfromaccount;
    EditText OAmount,OADescription;

    String[] TOACCOUNT = new String[] {"0123489080", "0123489080", "0123489811", "0123090807"};
    String[] FROMACCOUNT = new String[] {"0123489080", "0123489080", "0123489811", "0123090807"};
    String ToAccount,FromAccount,amount,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_account);

        OAmount = findViewById(R.id.ownaccountamount);
        OADescription = findViewById(R.id.ownaccountdescription);
        btnpaynowOA = findViewById(R.id.btnOApaynow);
        btnpayonOA = findViewById(R.id.btnOApayon);
        btnpayCancelOA = findViewById(R.id.btnOAcancel);
        btnperioOA = findViewById(R.id.btnOAperiodically);
        cbtoAccount = findViewById(R.id.dropdowntoaccountown);
        cbfromaccount = findViewById(R.id.Fromaccountown);

        ArrayAdapter<String> adapterToAccount = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, TOACCOUNT);
        cbtoAccount.setAdapter(adapterToAccount);

        ArrayAdapter<String> adapterFromaccount = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, FROMACCOUNT);
        cbfromaccount.setAdapter(adapterFromaccount);

        btnpaynowOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToAccount = cbtoAccount.getText().toString();
                FromAccount = cbfromaccount.getText().toString();
                amount = OAmount.getText().toString();
                desc = OADescription.getText().toString();
                showSuccessMsg();

            }
        });

        btnpayCancelOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OwnAccount.this, MainActivity.class));
            }
        });

        btnpayonOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OwnAccount.this, "Not available right now", Toast.LENGTH_SHORT).show();
            }
        });

        btnperioOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OwnAccount.this, "Not available right now", Toast.LENGTH_SHORT).show();
            }
        });
    }

        private void showSuccessMsg() {

            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(this).inflate(R.layout.alertdialog_ownaccount, viewGroup, false);

            TextView tvToAccount= dialogView.findViewById(R.id.tvtOAtoaccount);
            TextView tvFromAccount = dialogView.findViewById(R.id.tvTfromaccount);
            TextView tvAmount = dialogView.findViewById(R.id.tvTOAamountt);
            TextView tvDesc = dialogView.findViewById(R.id.tvTOWNDesc);
            Button btnCancel = dialogView.findViewById(R.id.btnOWNCancel) ;
            Button btnVerify = dialogView.findViewById(R.id.btnOWNVerify) ;

            tvToAccount.setText(ToAccount);
            tvFromAccount.setText(FromAccount);
            tvAmount.setText(amount);
            tvDesc.setText(desc);

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
                    startActivity(new Intent(OwnAccount.this,TransactionSuccessActivity.class));
                }
            });

        }
}