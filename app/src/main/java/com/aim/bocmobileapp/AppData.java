package com.aim.bocmobileapp;

import android.app.Application;
import com.aim.bocmobileapp.model.Payee;

import java.util.ArrayList;
import java.util.List;

public class AppData {

    public List<Payee> payeeList;

    public AppData(){
        payeeList = new ArrayList<Payee>();
        Payee p1 = new Payee("Nuwan Perera","NuwaP","1203 0453 0321","nuwn@gmail.com");
        Payee p2 = new Payee("Nuwan Perera","NuwaP","1203 0453 0321","nuwn@gmail.com");
        Payee p3 = new Payee("Nuwan Perera","NuwaP","1203 0453 0321","nuwn@gmail.com");

        payeeList.add(p1);
        payeeList.add(p2);
        payeeList.add(p3);
    }


    public void addPayee(Payee payee){
        payeeList.add(payee);
    }

    public List<Payee> getPayee(){
        return payeeList;
    }
}
