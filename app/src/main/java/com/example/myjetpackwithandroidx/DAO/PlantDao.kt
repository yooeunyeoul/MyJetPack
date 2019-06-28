package com.example.myjetpackwithandroidx.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.myjetpackwithandroidx.data.Plant

@Dao
interface PlantDao {

    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants(): LiveData<List<Plant>>
}