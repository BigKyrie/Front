package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.model.entity.Cinema;
import com.example.myapplication.model.entity.Ticket;

import java.util.List;

public class MyTicketAdapter extends BaseAdapter {
    private List<Ticket> mData;
    private Context mContext;

    public MyTicketAdapter(List<Ticket> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.ticket_item,parent,false);

        TextView CinemaName = (TextView) convertView.findViewById(R.id.CinemaName);
        TextView screen_no = (TextView) convertView.findViewById(R.id.screen_no);
        TextView line = (TextView) convertView.findViewById(R.id.line);
        TextView row = (TextView) convertView.findViewById(R.id.row);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        Button qr = (Button) convertView.findViewById(R.id.qr);

        CinemaName.setText(mData.get(position).getScreening().getMovie().getTitle());
        screen_no.setText("Screen number: "+mData.get(position).getScreening().getScreen().getNum());
        line.setText("row: "+mData.get(position).getSeat().getRow());
        row.setText("column: "+mData.get(position).getSeat().getCol());
        price.setText("price: "+mData.get(position).getPrice());

        return convertView;
    }
}
