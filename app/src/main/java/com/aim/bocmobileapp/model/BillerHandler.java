package com.aim.bocmobileapp.model;

import java.util.ArrayList;
import java.util.List;

public class BillerHandler {

    public List<Biller> billerList = new ArrayList<>();

    public BillerHandler() {}

    public void addBiller(Biller biller){

        billerList.add(biller);
    }

    public List<Biller> getBiller(){
        return billerList;
    }

    public void deleteBiller(int position){
        billerList.remove(position);
    }

    public void updateBiller(Biller biller,int position){
        billerList.remove(position);
        billerList.add(biller);
    }

}
