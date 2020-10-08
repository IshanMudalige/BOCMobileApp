package com.aim.bocmobileapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.aim.bocmobileapp.adapter.BillerAdapter;
import com.aim.bocmobileapp.model.Biller;
import com.aim.bocmobileapp.model.BillerHandler;
import com.google.gson.Gson;

import java.util.List;

public class BillerMaintainActivity extends AppCompatActivity {

    public static String BILLER = "com.aim.bocmobileapp.BILLER";
    public static String BOS = "com.aim.bocmobileapp.BOS";

    ListView listViewBiller;
    Button btnBAdd;
    EditText etBNo, etBNick, etBName, etBCustomer;

    BillerAdapter adapter;
    List<Biller> list;

    BillerHandler billerHandler;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biller_maintain);

        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        listViewBiller = findViewById(R.id.listViewBiller);
        btnBAdd = findViewById(R.id.btnBAdd);
        etBCustomer = findViewById(R.id.etBCustomer);
        etBName = findViewById(R.id.etBName);
        etBNick = findViewById(R.id.etBNick);
        etBNo = findViewById(R.id.etBNo);

        populateList();

        if(billerHandler == null)
            billerHandler = new BillerHandler();

        btnBAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etBCustomer.getText())) {
                    etBCustomer.setError("Customer Name is Required");
                }else if(TextUtils.isEmpty(etBName.getText())){
                    etBName.setError("Biller Name is Required");
                }else if(TextUtils.isEmpty(etBNick.getText())){
                    etBNick.setError("Biller Nickname is Required");
                }else if(TextUtils.isEmpty(etBNo.getText())){
                    etBNo.setError("Biller Number is Required");
                }else {
                    Biller biller = new Biller(etBCustomer.getText().toString(),etBName.getText().toString(),etBNick.getText().toString(),etBNo.getText().toString());
                    billerHandler.addBiller(biller);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(billerHandler);
                    editor.putString("BILLER_HANDLER",json);
                    editor.apply();

                    etBCustomer.setText("");
                    etBName.setText("");
                    etBNick.setText("");
                    etBNo.setText("");
                    displayAlert("Success","Biller successfully added.");

                    populateList();
                }

            }
        });

        listViewBiller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Biller bb = (Biller) adapter.getItem(position);

                Intent intent = new Intent(BillerMaintainActivity.this,BillerUpdateActivity.class);
                intent.putExtra(BILLER,bb);
                intent.putExtra(BOS,position);
                startActivity(intent);

            }
        });
    }

    public void populateList(){

        Gson gson = new Gson();
        String json = sharedpreferences.getString("BILLER_HANDLER", "");
        billerHandler = gson.fromJson(json, BillerHandler.class);

        try {
            list = billerHandler.getBiller();
            adapter = new BillerAdapter(BillerMaintainActivity.this,R.layout.item_biller,list);
            listViewBiller.setAdapter(adapter);
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