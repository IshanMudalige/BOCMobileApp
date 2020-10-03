package com.aim.bocmobileapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aim.bocmobileapp.model.Biller;
import com.aim.bocmobileapp.model.BillerHandler;
import com.google.gson.Gson;

import static com.aim.bocmobileapp.BillerMaintainActivity.BILLER;
import static com.aim.bocmobileapp.BillerMaintainActivity.BOS;

public class BillerUpdateActivity extends AppCompatActivity {

    Button btnBillerDelete, btnBillerUpdate;
    EditText etUpBNo,etUpBNick,etUpBName,etUpBCustomer;
    int position;
    SharedPreferences sharedpreferences;
    BillerHandler billerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biller_update);

        Intent intent = getIntent();
        Biller biller = (Biller) intent.getSerializableExtra(BILLER);
        position = intent.getIntExtra(BOS,0);

        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedpreferences.getString("BILLER_HANDLER", "");
        billerHandler = gson.fromJson(json, BillerHandler.class);

        etUpBCustomer = findViewById(R.id.etUpBCustomer);
        etUpBName = findViewById(R.id.etUpBName);
        etUpBNick = findViewById(R.id.etUpBNick);
        etUpBNo = findViewById(R.id.etUpBNo);
        btnBillerDelete = findViewById(R.id.btnBillerDelete);
        btnBillerUpdate = findViewById(R.id.btnBillerUpdate);

        etUpBCustomer.setText(biller.getCustomer());
        etUpBName.setText(biller.getB_name());
        etUpBNick.setText(biller.getB_Nick());
        etUpBNo.setText(biller.getB_No());

        btnBillerDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billerHandler.deleteBiller(position);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(billerHandler);
                editor.putString("BILLER_HANDLER",json);
                editor.apply();
                etUpBCustomer.setText("");etUpBName.setText("");etUpBNick.setText("");etUpBNo.setText("");
                displayAlert("Success","Biller deleted successfully.");

            }
        });

        btnBillerUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Biller billerUpdated = new Biller(etUpBCustomer.getText().toString(),etUpBName.getText().toString(),etUpBNick.getText().toString(),etUpBNo.getText().toString());
                billerHandler.updateBiller(billerUpdated,position);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(billerHandler);
                editor.putString("BILLER_HANDLER",json);
                editor.apply();
                etUpBCustomer.setText("");etUpBName.setText("");etUpBNick.setText("");etUpBNo.setText("");
                displayAlert("Success","Biller updated successfully.");
            }
        });

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
                        startActivity(new Intent(BillerUpdateActivity.this,BillerMaintainActivity.class));
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