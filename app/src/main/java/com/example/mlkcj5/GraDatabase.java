package com.example.mlkcj5;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Gra.class},version = 1, exportSchema = false)
public abstract class GraDatabase extends RoomDatabase {
    public abstract GraDAO zwrocGryDao();
}
