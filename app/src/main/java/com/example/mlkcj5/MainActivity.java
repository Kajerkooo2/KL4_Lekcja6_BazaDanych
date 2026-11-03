package com.example.mlkcj5;

import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnDodaj,btnAktualizuj;
    EditText etMin,etMax,etWiek,etNazwa;
    Spinner spNazwaKategori;
    ListView listView;

    List<Gra> gryList;
    ArrayAdapter<Gra> arrayAdapter;

    Gra kliknietaGra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GraDatabase graDatabase = Room.databaseBuilder(
                getApplicationContext(), GraDatabase.class, "gra_db")
                .allowMainThreadQueries().build();


        btnDodaj = findViewById(R.id.btnSend);
        btnAktualizuj = findViewById(R.id.btnUpdate);
        etMax = findViewById(R.id.etMax);
        etMin = findViewById(R.id.etMin);
        etWiek = findViewById(R.id.etAge);
        spNazwaKategori = findViewById(R.id.spCategoryName);
        etNazwa = findViewById(R.id.etName);

        listView = findViewById(R.id.listView);

        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gra gra = new Gra(
                        etNazwa.getText().toString(),
                        Integer.parseInt(etMin.getText().toString()),
                        Integer.parseInt(etMax.getText().toString()),
                        Integer.parseInt(etWiek.getText().toString()),
                        spNazwaKategori.getSelectedItem().toString()
                );
                graDatabase.zwrocGryDao().wstawGryDoBazy(gra);
            }
        });

        gryList = graDatabase.zwrocGryDao().zwrocWszystkieGry();
        arrayAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                gryList
        );
        listView.setAdapter(arrayAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                graDatabase.zwrocGryDao().usunZBazy(gryList.get(i));
                gryList.remove(i);
                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        kliknietaGra = gryList.get(i);

                        etNazwa.setText(kliknietaGra.nazwa);
                        etMin.setText(""+kliknietaGra.minLiczbaOsob);
                        etMax.setText(""+kliknietaGra.maksLiczbaOsob);
                        etWiek.setText(""+kliknietaGra.kategoriaWiekowa);
                        //bez spinnera narazie

                        btnAktualizuj.setVisibility(VISIBLE);
                    }
                }
        );
        btnAktualizuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kliknietaGra.nazwa = etNazwa.getText().toString();
                //dokonczyc
            }
        });
    }
}