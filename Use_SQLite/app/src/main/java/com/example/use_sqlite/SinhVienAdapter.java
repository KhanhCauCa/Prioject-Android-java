package com.example.use_sqlite;

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
    public SinhVienAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List<SinhVien> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v ==null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item, null);

        }
        SinhVien p = getItem(position);
        if (p !=null){
            TextView v1 = (TextView) v.findViewById(R.id.txt_ID);
            TextView v2 = (TextView) v.findViewById(R.id.txt_HoTen);
            TextView v3 = (TextView) v.findViewById(R.id.txt_NamSinh);

            v1.setText(String.valueOf(p.ID));
            v2.setText(p.hoTen);
            v3.setText(String.valueOf(p.namSinh));
        }
        return v;
    }
}
