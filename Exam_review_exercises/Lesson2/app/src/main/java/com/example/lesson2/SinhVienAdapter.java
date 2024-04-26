package com.example.lesson2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVien>
{
    public SinhVienAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v ==null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_student, null);
        }

        SinhVien p = (SinhVien) getItem(position);
        if (p !=null){
            TextView v1 = (TextView) v.findViewById(R.id.txt_item_identification_number);
            TextView v2 = (TextView) v.findViewById(R.id.txt_item_name);
            TextView v3 = (TextView) v.findViewById(R.id.txt_item_totalScore);
            v1.setText(p.identification_number);
            v2.setText(p.name);
            v3.setText(String.valueOf(p.tongdiem()));
        }
        return v;
    }
}
