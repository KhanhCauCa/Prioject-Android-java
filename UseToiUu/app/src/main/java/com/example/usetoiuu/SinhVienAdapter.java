package com.example.usetoiuu;

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
            TextView v1 = (TextView) v.findViewById(R.id.textViewHoTen);
            TextView v2 = (TextView) v.findViewById(R.id.textViewNamSinh);
            v1.setText(p.hoTen);
            v2.setText(String.valueOf(p.namSinh));
        }
        return v;
    }
}
