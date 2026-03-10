package com.example.listeleme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewPlakalar, listViewIller;
    Button btnKaristir;

    String[][] sehirVerileri = {
            {"1", "Adana"}, {"2", "Adıyaman"}, {"3", "Afyonkarahisar"}, {"4", "Ağrı"}, {"5", "Amasya"},
            {"6", "Ankara"}, {"7", "Antalya"}, {"8", "Artvin"}, {"9", "Aydın"}, {"10", "Balıkesir"},
            {"11", "Bilecik"}, {"12", "Bingöl"}, {"13", "Bitlis"}, {"14", "Bolu"}, {"15", "Burdur"},
            {"16", "Bursa"}, {"17", "Çanakkale"}, {"18", "Çankırı"}, {"19", "Çorum"}, {"20", "Denizli"},
            {"21", "Diyarbakır"}, {"22", "Edirne"}, {"23", "Elazığ"}, {"24", "Erzincan"}, {"25", "Erzurum"},
            {"26", "Eskişehir"}, {"27", "Gaziantep"}, {"28", "Giresun"}, {"29", "Gümüşhane"}, {"30", "Hakkari"},
            {"31", "Hatay"}, {"32", "Isparta"}, {"33", "Mersin"}, {"34", "İstanbul"}, {"35", "İzmir"},
            {"36", "Kars"}, {"37", "Kastamonu"}, {"38", "Kayseri"}, {"39", "Kırklareli"}, {"40", "Kırşehir"},
            {"41", "Kocaeli"}, {"42", "Konya"}, {"43", "Kütahya"}, {"44", "Malatya"}, {"45", "Manisa"},
            {"46", "Kahramanmaraş"}, {"47", "Mardin"}, {"48", "Muğla"}, {"49", "Muş"}, {"50", "Nevşehir"},
            {"51", "Niğde"}, {"52", "Ordu"}, {"53", "Rize"}, {"54", "Sakarya"}, {"55", "Samsun"},
            {"56", "Siirt"}, {"57", "Sinop"}, {"58", "Sivas"}, {"59", "Tekirdağ"}, {"60", "Tokat"},
            {"61", "Trabzon"}, {"62", "Tunceli"}, {"63", "Şanlıurfa"}, {"64", "Uşak"}, {"65", "Van"},
            {"66", "Yozgat"}, {"67", "Zonguldak"}, {"68", "Aksaray"}, {"69", "Bayburt"}, {"70", "Karaman"},
            {"71", "Kırıkkale"}, {"72", "Batman"}, {"73", "Şırnak"}, {"74", "Bartın"}, {"75", "Ardahan"},
            {"76", "Iğdır"}, {"77", "Yalova"}, {"78", "Karabük"}, {"79", "Kilis"}, {"80", "Osmaniye"},
            {"81", "Düzce"}
    };

    List<String> plakaListesi = new ArrayList<>();
    List<String> ilListesi = new ArrayList<>();
    ArrayAdapter<String> plakaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPlakalar = findViewById(R.id.listView1);
        listViewIller = findViewById(R.id.listView2);
        btnKaristir = findViewById(R.id.buttonKaristir);

        for (int i = 0; i < sehirVerileri.length; i++) {
            ilListesi.add(sehirVerileri[i][1]);
            plakaListesi.add(sehirVerileri[i][0]);
        }

        Collections.shuffle(plakaListesi);

        plakaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, plakaListesi);
        listViewPlakalar.setAdapter(plakaAdapter);

        ArrayAdapter<String> ilAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ilListesi);
        listViewIller.setAdapter(ilAdapter);

        btnKaristir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(plakaListesi);
                plakaAdapter.notifyDataSetChanged();
            }
        });

        listViewIller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tiklananIl = ilListesi.get(position);
                String karsisindakiPlaka = plakaListesi.get(position);

                boolean eslestiMi = false;

                for (int i = 0; i < sehirVerileri.length; i++) {
                    if (sehirVerileri[i][1].equals(tiklananIl) && sehirVerileri[i][0].equals(karsisindakiPlaka)) {
                        eslestiMi = true;
                        break;
                    }
                }

                Intent intent = new Intent(MainActivity.this, ekran2.class);
                intent.putExtra("ESLESME_DURUMU", eslestiMi); // İkinci ekrana sonucu yolluyoruz
                startActivity(intent);
            }
        });
    }
}