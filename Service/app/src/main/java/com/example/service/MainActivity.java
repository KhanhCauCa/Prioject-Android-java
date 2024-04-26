package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    Button btn_stop, btn_play,btn_playService,btn_stopService;
    Button btn_login;
    EditText edt_password, edt_username;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();



        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = MediaPlayer.create(MainActivity.this, Settings.System.DEFAULT_RINGTONE_URI);
                player.setLooping(true);
                player.start();
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
            }
        });

        btn_playService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MyService.class));
            }
        });

        btn_stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService (new Intent(MainActivity.this, MyService.class));
            }
        });

    }
    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getActiveNetworkInfo() !=null){
                Toast.makeText(MainActivity.this, "Turn on wifi", Toast.LENGTH_SHORT).show();
                btn_login.setEnabled(true);
            }
            else{
                Toast.makeText(MainActivity.this, "Turn off wifi", Toast.LENGTH_SHORT).show();
                btn_login.setEnabled(false);
            }

        }
    };
    @Override

    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(wifiReceiver != null){
            unregisterReceiver(wifiReceiver);
        }
    }

    private void InitWidget() {
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_stopService = (Button) findViewById(R.id.btn_stopService);
        btn_playService = (Button) findViewById(R.id.btn_playService);
        btn_login = (Button) findViewById(R.id.btn_login);
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);

    }
}