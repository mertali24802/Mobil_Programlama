package com.example.ders_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class Bluetooth extends AppCompatActivity {

    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;
    Button b1, b2, b3, b4;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        lv = findViewById(R.id.listView);
        BA = BluetoothAdapter.getDefaultAdapter();

        if (BA == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth cihazda desteklenmiyor", Toast.LENGTH_LONG).show();
            finish();
        } else {
            checkAndRequestPermissions();
        }
    }

    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.BLUETOOTH_CONNECT
            }, REQUEST_BLUETOOTH_PERMISSION);
        } else {
        }
    }

    public void on(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            if (!BA.isEnabled()) {
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn, 0);
                Toast.makeText(getApplicationContext(), "Bluetooth Açıldı", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Bluetooth Zaten Açık", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Bluetooth açma izni verilmedi.", Toast.LENGTH_SHORT).show();
            checkAndRequestPermissions();
        }
    }

    public void off(View v) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            if (BA.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Kapatmak için ayarlar açılıyor...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Bluetooth Zaten Kapalı", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Bluetooth kapatma izni verilmedi.", Toast.LENGTH_SHORT).show();
            checkAndRequestPermissions();
        }
    }

    public void list(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            pairedDevices = BA.getBondedDevices();
            ArrayList<String> list = new ArrayList<>();
            for (BluetoothDevice bt : pairedDevices) {
                list.add(bt.getName() + "\n" + bt.getAddress());
            }
            if (list.size() > 0) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                lv.setAdapter(adapter);
            } else {
                Toast.makeText(getApplicationContext(), "Eşleştirilmiş cihaz yok", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Bluetooth listesini görüntülemek için izin verilmedi.", Toast.LENGTH_SHORT).show();
            checkAndRequestPermissions();
        }
    }

    public void makeVisible(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED) {
            Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivityForResult(getVisible, 0);
            Toast.makeText(getApplicationContext(), "Cihaz görünür hale getirildi", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Cihazı görünür yapmak için izin verilmedi.", Toast.LENGTH_SHORT).show();
            checkAndRequestPermissions();
        }
    }
}