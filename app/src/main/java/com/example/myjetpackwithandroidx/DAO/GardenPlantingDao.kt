package com.example.myjetpackwithandroidx.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myjetpackwithandroidx.data.GardenPlanting
import com.example.myjetpackwithandroidx.data.Plant

@Dao
interface GardenPlantingDao {

    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): LiveData<List<GardenPlanting>>

    @Insert
    fun insertGardenPlanting(gardenPlanting: GardenPlanting): Long

    @Query("SELECT * FROM garden_plantings WHERE plant_id =:plantId ")
    fun getGardenPlantingForPlant(plantId: String): LiveData<GardenPlanting>


}