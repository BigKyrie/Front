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

import com.example.myapplication.model.entity.Screening;

import java.util.List;


public class ScreeningAdapter extends BaseAdapter {

    private List<Screening> mData;
    private Context mContext;
    private String Movie_id;
    public ScreeningAdapter(List<Screening> mData, Context mContext,String Screening_id) {
        this.mData = mData;
        this.mContext = mContext;
        this.Movie_id = Screening_id;
    }

    @Override
    public int getCount() {
        return mData.size();
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.screening_item,parent,false);

        TextView price = (TextView) convertView.findViewById(R.id.Price);
        TextView screen = (TextView) convertView.findViewById(R.id.screen);
        TextView start = (TextView) convertView.findViewById(R.id.start);
        TextView end = (TextView) convertView.findViewById(R.id.end);
        Button buy = (Button) convertView.findViewById(R.id.buy_ticket);

        //price.setText("Title:"+mData.get(position).getTitle());
        price.setText("Price: " + mData.get(position).getPrice().toString());
        screen.setText("Screen: "+mData.get(position).getScreen().getNum());
        start.setText("Start time: "+mData.get(position).getStart_time());
        end.setText("End time: "+mData.get(position).getEnd_time());

        return convertView;
    }
}
