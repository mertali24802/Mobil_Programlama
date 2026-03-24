package com.example.proje_3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Tasarımdaki butonu Java'ya tanıtıyoruz
        Button butonSehirSec = findViewById(R.id.btnSehirSec);

        // 2. Rastgele ikon seçmek için elimizdeki 9 resmin ID'lerini bir listeye (diziye) koyuyoruz
        int[] resimHavuzu = {
                R.drawable.ist1, R.drawable.ist2, R.drawable.ist3,
                R.drawable.ank1, R.drawable.ank2, R.drawable.ank3,
                R.drawable.izm1, R.drawable.izm2, R.drawable.izm3
        };

        // 3. Butona tıklanma olayını dinliyoruz
        butonSehirSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 3.1 Rastgele bir resim seçiyoruz
                Random rastgeleMotoru = new Random();
                int rastgeleSayi = rastgeleMotoru.nextInt(resimHavuzu.length); // 0 ile 8 arası sayı üretir
                int secilenIkon = resimHavuzu[rastgeleSayi];

                // 3.2 Diyalog (Mesaj) penceresini oluşturuyoruz
                AlertDialog.Builder diyalog = new AlertDialog.Builder(MainActivity.this);
                diyalog.setTitle("Şehirler");
                diyalog.setIcon(secilenIkon); // Rastgele seçilen resmi ikon yapıyoruz

                // 3.3 Listede görünecek şehir isimleri
                String[] sehirSecenekleri = {"İstanbul", "Ankara", "İzmir"};

                // 3.4 Listeye tıklanma olayını ayarlıyoruz
                diyalog.setItems(sehirSecenekleri, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int secilenSira) {
                        // secilenSira: 0 ise İstanbul, 1 ise Ankara, 2 ise İzmir'e tıklanmıştır.

                        if (secilenSira == 0) {
                            // İstanbul'a tıklandıysa IstanbulActivity'e geç (Intent kullanıyoruz)
                            Intent intent = new Intent(MainActivity.this, IstanbulActivity.class);
                            startActivity(intent);
                        }
                        else if (secilenSira == 1) {
                            // Ankara'ya tıklandıysa AnkaraActivity'e geç
                            Intent intent = new Intent(MainActivity.this, AnkaraActivity.class);
                            startActivity(intent);
                        }
                        else if (secilenSira == 2) {
                            // İzmir'e tıklandıysa IzmirActivity'e geç
                            Intent intent = new Intent(MainActivity.this, IzmirActivity.class);
                            startActivity(intent);
                        }
                    }
                });

                // 3.5 Hazırladığımız diyalogu ekranda gösteriyoruz
                diyalog.show();
            }
        });
    }
}