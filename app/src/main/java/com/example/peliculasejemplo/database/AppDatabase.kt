package com.example.peliculasejemplo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [City::class, Usuario::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CityDao(): CityDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        private const val DATABASE_NAME = "ciudades"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}