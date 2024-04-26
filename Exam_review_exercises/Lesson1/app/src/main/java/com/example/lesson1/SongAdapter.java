package com.example.lesson1;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.sql.SQLData;

public class SongAdapter extends BaseAdapter {
    private Context mContext;
    private Cursor mCursor;

    public SongAdapter(Context mContext, Cursor mCursor) {
        this.mContext = mContext;
        this.mCursor = mCursor;
    }


    @Override
    public int getCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    @Override
    public Object getItem(int position) {
        if (mCursor == null || !mCursor.moveToPosition(position)) {
            return null;
        }
        return mCursor;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_singer, parent, false);
        }

        TextView txtNameSong = convertView.findViewById(R.id.txt_nameSong);
        TextView txtTime = convertView.findViewById(R.id.txt_time);
        TextView txtSinger = convertView.findViewById(R.id.txt_singer);

        Cursor cursor = (Cursor) getItem(position);
        if (cursor != null) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(MySQL.getNAME()));
            String singer = cursor.getString(cursor.getColumnIndexOrThrow(MySQL.getSINGER()));
            float timeInSeconds = mCursor.getFloat(mCursor.getColumnIndexOrThrow(MySQL.getTIME()));

            String formattedTime = formatTime(timeInSeconds);

            txtNameSong.setText(name);
            txtTime.setText(String.valueOf(formattedTime));
            txtSinger.setText(singer);
        }

        return convertView;
    }
    public void closeCursor() {
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
    }

    private String formatTime(float minutesDecimal) {
        int totalSeconds = (int) (minutesDecimal * 60); // Tổng số giây từ số phút

        int hours = totalSeconds / 3600; // Số giờ (1 giờ = 3600 giây)
        int remainingSeconds = totalSeconds % 3600; // Số giây còn lại sau khi lấy số giờ

        int minutes = remainingSeconds / 60; // Số phút từ số giây còn lại
        int seconds = remainingSeconds % 60; // Số giây còn lại sau khi lấy số phút

        String formattedTime;
        if (hours > 0) {
            formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            formattedTime = String.format("%02d:%02d", minutes, seconds);
        }

        return formattedTime;
    }

}
