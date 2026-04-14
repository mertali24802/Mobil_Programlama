package com.example.ders_7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Wifi extends AppCompatActivity {

    private WifiManager modem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        modem = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        final ToggleButton btn = findViewById(R.id.toggleButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Intent panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                    startActivityForResult(panelIntent, 1);
                    Toast.makeText(Wifi.this, "Wi-Fi paneli açılıyor...", Toast.LENGTH_SHORT).show();
                } else {
                    if (btn.isChecked()) {
                        modem.setWifiEnabled(true);
                        Toast.makeText(Wifi.this, "Wifi Açık", Toast.LENGTH_SHORT).show();
                    } else {
                        modem.setWifiEnabled(false);
                        Toast.makeText(Wifi.this, "Wifi Kapalı", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}