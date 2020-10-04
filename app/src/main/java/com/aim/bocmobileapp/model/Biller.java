package com.aim.bocmobileapp.model;

import java.io.Serializable;

public class Biller implements Serializable {
    private String customer;
    private String b_name;
    private String b_Nick;
    private String b_No;

    public Biller(String customer, String b_name, String b_Nick, String b_No) {
        this.customer = customer;
        this.b_name = b_name;
        this.b_Nick = b_Nick;
        this.b_No = b_No;
    }

    public  Biller() {

    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_Nick() {
        return b_Nick;
    }

    public void setB_Nick(String b_Nick) {
        this.b_Nick = b_Nick;
    }

    public String getB_No() {
        return b_No;
    }

    public void setB_No(String b_No) {
        this.b_No = b_No;
    }

}
