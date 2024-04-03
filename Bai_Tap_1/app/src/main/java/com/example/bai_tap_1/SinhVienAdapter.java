package com.example.bai_tap_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {


    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List<SinhVien> objects) {
        super(context, resource, objects);
    }

    public SinhVienAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item, null);
        }
        SinhVien  sv = getItem(position);
        if(sv != null){
            TextView v1 = (TextView) v.findViewById(R.id.txt_Name);
            TextView v2 = (TextView) v.findViewById(R.id.txt_Age);
            v1.setText(sv.Name);
            v2.setText(String.valueOf(sv.Age));
        }
        return v;
    }
}
