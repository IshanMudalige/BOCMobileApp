package com.aim.bocmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OwnAccount extends AppCompatActivity {

    Button btnpaynowOA,btnpayonOA,btnpayCancelOA,btnperioOA;
    AutoCompleteTextView cbtoAccount,cbfromaccount;
    EditText OAmount,OADescription;

    String[] TOACCOUNT = new String[] {"012348908074899", "012348908074844", "012348908074811", "0123489080748565"};
    String[] FROMACCOUNT = new String[] {"012348908074899", "012348908074844", "012348908074811", "0123489080748565"};
    String ToAccount,FromAccount,amount,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_account);

        OAmount=findViewById(R.id.ownaccountamount);
        OADescription=findViewById(R.id.ownaccountdescription);
        btnpaynowOA=findViewById(R.id.btnOApaynow);
        btnpayonOA=findViewById(R.id.btnOApayon);
        btnpayCancelOA=findViewById(R.id.btnOAcancel);
        btnperioOA=findViewById(R.id.btnOAperiodically);
        cbtoAccount=findViewById(R.id.dropdowntoaccountown);
        cbfromaccount=findViewById(R.id.Fromaccountown);

        ArrayAdapter<String> adapterToAccount = new ArrayAdapter<>(this,R.layout.dropdown_menu_popup_item,TOACCOUNT);
        cbtoAccount.setAdapter(adapterToAccount);

        ArrayAdapter<String> adapterFromaccount = new ArrayAdapter<>(this,R.layout.dropdown_menu_popup_item,FROMACCOUNT);
        cbfromaccount.setAdapter(adapterFromaccount);

        btnpaynowOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToAccount=cbtoAccount.getText().toString();
                FromAccount=cbfromaccount.getText().toString();
                amount=OAmount.getText().toString();
                desc=OADescription.getText().toString();

            }
        });

        btnpayCancelOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OwnAccount.this,MainActivity.class));
            }
        });

        btnpayonOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OwnAccount.this,"Not available right now", Toast.LENGTH_SHORT).show();
            }
        });

        btnperioOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OwnAccount.this,"Not available right now", Toast.LENGTH_SHORT).show();
            }
        });


    }
}