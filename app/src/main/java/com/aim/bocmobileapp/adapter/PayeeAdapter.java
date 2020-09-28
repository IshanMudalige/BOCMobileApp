package com.aim.bocmobileapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aim.bocmobileapp.R;
import com.aim.bocmobileapp.model.Payee;

import java.util.List;

public class PayeeAdapter extends ArrayAdapter {
    List<Payee> list;
    Activity context;
    int resource;

    public PayeeAdapter(Activity context, int resource, List<Payee> list) {
        super(context, resource,list);
        this.context = context;
        this.list = list;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Payee py = (Payee) getItem(position);

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_payee, null, true);

        TextView tvname = rowView.findViewById(R.id.tvName);
        TextView tvacc = rowView.findViewById(R.id.tvAcc);
        TextView tvemail = rowView.findViewById(R.id.tvEmail);

        tvname.setText(py.getName()+"("+py.getNick()+")");
        tvacc.setText("Acc No. "+py.getAcc());
        tvemail.setText(py.getEmail());

        return rowView;
    }
}
