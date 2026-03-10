package com.example.listeleme;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ekran2 extends AppCompatActivity {

    TextView tvBasari;
    Button btnGeriDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran2);

        tvBasari = findViewById(R.id.tvBasari);
        btnGeriDon = findViewById(R.id.btnGeriDon);

        boolean eslestiMi = getIntent().getBooleanExtra("ESLESME_DURUMU", false);

        if (eslestiMi) {
            tvBasari.setText("Plaka Eşleşiyor!");
        } else {
            tvBasari.setText("Plaka Eşleşmedi!");
        }

        btnGeriDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}