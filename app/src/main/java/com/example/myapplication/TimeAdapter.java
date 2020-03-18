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

import com.example.myapplication.model.entity.Time;

import java.util.List;


public class TimeAdapter extends BaseAdapter {

    private List<Time> mData;
    private Context mContext;
    public TimeAdapter(List<Time> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.cinema_item,parent,false);

        TextView price = (TextView) convertView.findViewById(R.id.Price);
        TextView screen = (TextView) convertView.findViewById(R.id.screen);
        TextView start = (TextView) convertView.findViewById(R.id.start);
        TextView end = (TextView) convertView.findViewById(R.id.end);
        Button buy = (Button) convertView.findViewById(R.id.buy_ticket);


        return convertView;
    }
}
