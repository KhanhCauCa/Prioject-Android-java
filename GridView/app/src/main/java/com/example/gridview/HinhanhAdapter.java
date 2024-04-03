package com.example.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class HinhanhAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Hinhanh> hinhanhList;

    public HinhanhAdapter(Context context, int layout, List<Hinhanh> hinhanhList) {
        this.context = context;
        this.layout = layout;
        this.hinhanhList = hinhanhList;
    }

    public HinhanhAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return hinhanhList != null ? hinhanhList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        ImageView imgHinh;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            // gắn layout
            convertView = inflater.inflate(layout,null);
            // ánh xạ
            holder.imgHinh = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        Hinhanh hinhanh = hinhanhList.get(position);
        holder.imgHinh.setImageResource(hinhanh.getHinh());
        return convertView;
    }
}
