package com.example.mlkcj5;

import androidx.room3.Database;
import androidx.room3.RoomDatabase;

@Database(entities = {Gra.class},version = 1)
public abstract class GraDatabase extends RoomDatabase {
    public abstract GraDAO zwrocGryDao();
}
