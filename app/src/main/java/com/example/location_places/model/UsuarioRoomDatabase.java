package com.example.location_places.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Usuario.class}, version = 1, exportSchema = false)
public abstract class UsuarioRoomDatabase extends RoomDatabase {
    public abstract UsuarioDao usuarioDao();
    private static volatile UsuarioRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static UsuarioRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UsuarioRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UsuarioRoomDatabase.class, "usuario_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
