package com.aim.bocmobileapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aim.bocmobileapp.R;
import com.aim.bocmobileapp.model.Biller;
import com.aim.bocmobileapp.model.Payee;

import java.util.List;

public class BillerAdapter extends ArrayAdapter {
    List<Biller> list;
    Activity context;
    int resource;

    public BillerAdapter(Activity context, int resource, List<Biller> list) {
        super(context, resource,list);
        this.context = context;
        this.list = list;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Biller biller = (Biller) getItem(position);

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_biller, null, true);

        TextView tvcustomer = rowView.findViewById(R.id.tvCustomer);
        TextView tvbiller = rowView.findViewById(R.id.tvBiller);
        TextView tvBNum = rowView.findViewById(R.id.tvBNum);

        tvbiller.setText(biller.getB_name()+"("+biller.getB_Nick()+")");
        tvcustomer.setText(biller.getCustomer());
        tvBNum.setText("Biller No. "+biller.getB_No());

        return rowView;
    }

}
