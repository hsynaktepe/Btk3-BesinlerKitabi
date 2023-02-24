package com.example.btk3_besinlerkitabi.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.btk3_besinlerkitabi.model.Besin

@Dao
interface BesinDAO {
    //Data access object

    @Insert
    fun insertAll(vararg besin: Besin) : List<Long>

    @TypeConverters
    @Query("SELECT * FROM besin")
    fun getAllBesin() : List<Besin>

    @TypeConverters
    @Query("SELECT * FROM besin WHERE uuid =  :besinId")
    fun getBesin(besinId:Int) : Besin

    @TypeConverters
    @Query("DELETE FROM besin")
    fun deleteAllBesin()
}