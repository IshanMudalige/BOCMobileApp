package com.aim.bocmobileapp.model;

import java.util.ArrayList;
import java.util.List;

public class PayeeHandler{

    public List<Payee> payeeList = new ArrayList<>();

    public PayeeHandler(){ }

    public void addPayee(Payee payee){

        payeeList.add(payee);
    }

    public List<Payee> getPayee(){
        return payeeList;
    }

    public void deletePayee(int position){
        payeeList.remove(position);
    }

    public void updatePayee(Payee payee,int position){
        payeeList.remove(position);
        payeeList.add(payee);
    }
}
