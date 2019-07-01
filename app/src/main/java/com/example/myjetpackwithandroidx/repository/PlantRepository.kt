package com.example.myjetpackwithandroidx.repository

import androidx.lifecycle.LiveData
import com.example.myjetpackwithandroidx.DAO.PlantDao
import com.example.myjetpackwithandroidx.data.Plant

class PlantRepository private constructor(private val plantDao: PlantDao) {

    fun getPlants() = plantDao.getPlants()
    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): LiveData<List<Plant>> =
        plantDao.getPlantsWithGrowZoneNumber(growZoneNumber)

    companion object {

        //TODO Volatile 이 뭐지??
        @Volatile
        private var instance: PlantRepository? = null

        fun getInstance(plantDao: PlantDao) =
            instance ?: synchronized(this) {
                //TODO :: also 에서 apply 로 바꿔봄
                PlantRepository(plantDao).apply {
                    instance = this
                }
            }

    }


}