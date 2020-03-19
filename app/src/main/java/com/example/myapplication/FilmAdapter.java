package com.example.myapplication;

import android.annotation.SuppressLint;
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

import com.example.myapplication.model.entity.Movie;

import java.util.LinkedList;
import java.util.List;

public class FilmAdapter extends BaseAdapter {

    private List<Movie> mData;
    private Context mContext;
    public FilmAdapter(List<Movie> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
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

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.iv);
        TextView lead_actor = (TextView) convertView.findViewById(R.id.lead_actors);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView duration = (TextView) convertView.findViewById(R.id.duration);
        TextView director = (TextView) convertView.findViewById(R.id.director);
        Button buy_ticket = (Button) convertView.findViewById(R.id.buy_ticket);

        title.setText("Title:《"+mData.get(position).getTitle()+"》");
        lead_actor.setText("Lead actor: "+mData.get(position).getActors());
        duration.setText("Duration: "+mData.get(position).getDuration().toString());
        director.setText("Director: "+mData.get(position).getDirector());

        buy_ticket.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext,CinemaListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",mData.get(position).getId().toString());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

}
