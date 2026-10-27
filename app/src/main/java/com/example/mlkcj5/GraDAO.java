package com.example.mlkcj5;

import androidx.room3.Delete;
import androidx.room3.Insert;
import androidx.room3.Query;
import androidx.room3.Update;

import java.util.List;

public interface GraDAO {
    @Insert
    public void wstawGreDoBazy(Gra gra);

    @Insert
    public void wstawGryDoBazy(Gra ... gry);

    @Delete
    public void usunZBazy(Gra gra);

    @Update
    public void aktualizujGre(Gra gra);

    @Query("SELECT * from gry")
    public List<Gra> zwrocWszystkieGry();

    @Query("SELECT nazwa from gry where minLiczba = :ile")
    public List<String> zwrocNazwyGierDlaLiczbyOsob(int ile);

}
