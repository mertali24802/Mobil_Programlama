package com.example.ornek_hesapmakinesi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    EditText sonucEkrani;
    double ilkSayi = 0;
    String islemTuru = "";
    boolean yeniIslem = true;

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    Button btnArti, btnEksi, btnCarpi, btnBolu, btnEsit, btnC;

    Button btnYuzde, btn1Bolux, btnUs, btnKok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sonucEkrani = findViewById(R.id.main);
        sonucEkrani.setShowSoftInputOnFocus(false);

        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.buttondort);
        btn5 = findViewById(R.id.buttonbes);
        btn6 = findViewById(R.id.buttonalti);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);

        btnArti = findViewById(R.id.buttonarti);
        btnEksi = findViewById(R.id.buttoneksi);
        btnCarpi = findViewById(R.id.buttoncarpi);
        btnBolu = findViewById(R.id.buttonbolu);
        btnEsit = findViewById(R.id.buttonesit);
        btnC = findViewById(R.id.buttonc);

        btnYuzde = findViewById(R.id.buttonyuzde);
        btn1Bolux = findViewById(R.id.buttonx);
        btnUs = findViewById(R.id.buttonus);
        btnKok = findViewById(R.id.buttonkok);



        View.OnClickListener rakamDinleyici = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yeniIslem) {
                    sonucEkrani.setText("");
                    yeniIslem = false;
                }
                Button tiklanan = (Button) v;
                sonucEkrani.append(tiklanan.getText().toString());
            }
        };

        btn0.setOnClickListener(rakamDinleyici);
        btn1.setOnClickListener(rakamDinleyici);
        btn2.setOnClickListener(rakamDinleyici);
        btn3.setOnClickListener(rakamDinleyici);
        btn4.setOnClickListener(rakamDinleyici);
        btn5.setOnClickListener(rakamDinleyici);
        btn6.setOnClickListener(rakamDinleyici);
        btn7.setOnClickListener(rakamDinleyici);
        btn8.setOnClickListener(rakamDinleyici);
        btn9.setOnClickListener(rakamDinleyici);

        View.OnClickListener islemDinleyici = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button tiklanan = (Button) v;
                islemTuru = tiklanan.getText().toString();

                if (!sonucEkrani.getText().toString().isEmpty()) {
                    ilkSayi = Double.parseDouble(sonucEkrani.getText().toString());
                }
                yeniIslem = true;
            }
        };

        btnArti.setOnClickListener(islemDinleyici);
        btnEksi.setOnClickListener(islemDinleyici);
        btnCarpi.setOnClickListener(islemDinleyici);
        btnBolu.setOnClickListener(islemDinleyici);
        btnUs.setOnClickListener(islemDinleyici);

        btnEsit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sonucEkrani.getText().toString().isEmpty()) return;

                double ikinciSayi = Double.parseDouble(sonucEkrani.getText().toString());
                double sonuc = 0;

                switch (islemTuru) {
                    case "+": sonuc = ilkSayi + ikinciSayi; break;
                    case "-": sonuc = ilkSayi - ikinciSayi; break;
                    case "x": sonuc = ilkSayi * ikinciSayi; break;
                    case "/":
                        if(ikinciSayi != 0) sonuc = ilkSayi / ikinciSayi;
                        else { sonucEkrani.setText("Hata"); yeniIslem = true; return; }
                        break;
                    case "Us": sonuc = Math.pow(ilkSayi, ikinciSayi); break;
                }

                ekranaSonucYazdir(sonuc);
                ilkSayi = sonuc;
                yeniIslem = true;
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonucEkrani.setText("");
                ilkSayi = 0;
                islemTuru = "";
                yeniIslem = true;
            }
        });

        View.OnClickListener ozelIslemDinleyici = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button tiklanan = (Button) v;
                String islem = tiklanan.getText().toString();

                if (sonucEkrani.getText().toString().isEmpty()) return;
                double sayi = Double.parseDouble(sonucEkrani.getText().toString());
                double sonuc = 0;

                switch (islem) {
                    case "√x": sonuc = Math.sqrt(sayi); break;
                    case "1/x": sonuc = 1 / sayi; break;
                    case "%": sonuc = sayi / 100; break;
                }

                ekranaSonucYazdir(sonuc);
                yeniIslem = true;
            }
        };

        btnKok.setOnClickListener(ozelIslemDinleyici);
        btn1Bolux.setOnClickListener(ozelIslemDinleyici);
        btnYuzde.setOnClickListener(ozelIslemDinleyici);
    }


    private void ekranaSonucYazdir(double sonuc) {
        String sonucMetni = String.valueOf(sonuc);
        if (sonucMetni.endsWith(".0")) {
            sonucMetni = sonucMetni.substring(0, sonucMetni.length() - 2);
        }
        sonucEkrani.setText(sonucMetni);
    }
}