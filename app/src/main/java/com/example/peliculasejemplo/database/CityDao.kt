package com.example.peliculasejemplo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {

    @Query("SELECT * FROM city")
    suspend fun getCity(): List<City>

    @Query("SELECT * FROM city where codigo = :id")
    suspend fun getCityById(id:Int): City

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: City)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cities: List<City>)

    @Query("DELETE FROM city WHERE titulo = :titulo")
    suspend fun delete(titulo: String)

    @Query("UPDATE city SET titulo = :tituloN Where titulo = :tituloO")
    suspend fun update(tituloO: String, tituloN: String): Int?
}