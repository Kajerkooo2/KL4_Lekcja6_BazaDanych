package com.example.mlkcj5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    Button btnDodaj;
    EditText etMin,etMax,etWiek,etNazwa;
    Spinner spNazwaKategori;

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
        etMax = findViewById(R.id.etMax);
        etMin = findViewById(R.id.etMin);
        etWiek = findViewById(R.id.etAge);
        spNazwaKategori = findViewById(R.id.spCategoryName);
        etNazwa = findViewById(R.id.etName);

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
    }
}