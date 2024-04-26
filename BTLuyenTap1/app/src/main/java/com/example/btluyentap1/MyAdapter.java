package com.example.btluyentap1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter {

    public MyAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public MyAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(convertView == null){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_singer, parent, false);

            TextView tv_name, tv_singer, tv_timer;
            tv_name = v.findViewById(R.id.tv_name);
            tv_singer = v.findViewById(R.id.tv_singer);
            tv_timer = v.findViewById(R.id.tv_timer);

            Song song = (Song) getItem(position);
            if(song != null){
                tv_name.setText(song.getName());
                tv_singer.setText(song.getSinger());
                tv_timer.setText(String.valueOf(song.getTimer().toString()));
            }
        }
        return v;
    }
}
