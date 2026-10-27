package com.example.mlkcj5;

import androidx.room3.Entity;
import androidx.room3.PrimaryKey;

@Entity (tableName = "gry")
public class Gra {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nazwa;
    public int minLiczbaOsob;
    public int maksLiczbaOsob;
    public int kategoriaWiekowa;
    public String kategoriaNazwa;

    public Gra(String nazwa, int minLiczbaOsob, int maksLiczbaOsob, int kategoriaWiekowa, String kategoriaNazwa) {
        this.nazwa = nazwa;
        this.minLiczbaOsob = minLiczbaOsob;
        this.maksLiczbaOsob = maksLiczbaOsob;
        this.kategoriaWiekowa = kategoriaWiekowa;
        this.kategoriaNazwa = kategoriaNazwa;
    }
}