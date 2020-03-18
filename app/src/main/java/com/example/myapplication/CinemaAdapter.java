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

import java.util.List;

public class CinemaAdapter extends BaseAdapter {

    private List<Cinema> mData;
    private Context mContext;
    public CinemaAdapter(List<Cinema> mData, Context mContext) {
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

        TextView cinema_name = (TextView) convertView.findViewById(R.id.CinemaName);
        TextView wifi = (TextView) convertView.findViewById(R.id.wifi);
        TextView glass = (TextView) convertView.findViewById(R.id.glass);
        TextView location = (TextView) convertView.findViewById(R.id.location);
        Button select = (Button) convertView.findViewById(R.id.SelectTime);

//        cinema_name.setText("Title:《"+mData.get(position).getTitle()+"》");
//        wifi.setText("Lead actor: "+mData.get(position).getLead_actors());
//        glass.setText("Duration: "+mData.get(position).getDuration().toString());
//        location.setText("Director: "+mData.get(position).getDirector());

        select.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext,TimeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",mData.get(position).getId().toString());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
